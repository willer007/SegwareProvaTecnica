import entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import crccalc.Crc8;
import crccalc.CrcCalculator;
import enums.FrameEnum;
import handlers.*;
import repositories.MessageRepository;
import utils.MessageUtils;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private static int PORT = 55000;
    private static final Logger logger = LogManager.getLogger("server-logger");


    //START SERVER ON LOCALHOST:55000
    public Server() throws Exception {
        this.server = new ServerSocket(PORT, 1, InetAddress.getLocalHost());
    }


    //RECEIVE INCOMING CONNECTIONS
    private void receiveIncomingConnections() throws Exception {

        while (true) {

            Socket client = this.server.accept();

            logger.debug("received connection");

            new Thread(() -> {
                logger.debug("started to listen connection");

                try {
                    listen(client);
                } catch (IOException e) {
                    logger.error(e);
                }

                logger.debug("ended to listen connection");

            }).start();

            logger.debug("ended connection");

        }
    }

    //LISTEN INCOMING CONNECTIONS
    public void listen(Socket client) throws IOException {

        MessageUtils messageUtils = new MessageUtils();
        MessageEntity message = new MessageEntity();
        CrcCalculator calculator = new CrcCalculator(Crc8.Crc8);
        MessageRepository messageRepository = new MessageRepository();
        IMessageHandler messageHandler;
        FrameEnum frameEnum = null;

        while (true) {

            //READ MESSAGE
            logger.debug("started to read message");
            if (!messageUtils.readMessage(client, message)) {
                continue;
            }
            logger.debug("ended to read message");


            //VERIFY INTEGRITY BY CRC8
            logger.debug("started to check CRC8");
            if (!messageUtils.checkMessageIntegrity(calculator, message)) {
                continue;
            }
            logger.debug("ended to check CRC8");


            //LOG MESSAGE TO FILE
            logger.info("PORT: " + client.getPort()  + message);

            //SPECIFIC HANDLER FOR EACH MESSAGE TYPE
            frameEnum = FrameEnum.getEnumByBytes(message.getFrame()[0]);
            switch (frameEnum) {
                case MENSAGEM_TEXTO:
                    logger.debug("handling MENSAGEM_TEXTO");
                    messageHandler = new MessageHandler_MENSAGEM_TEXTO();
                    TextoMessageEntity textoMessageEntity =
                            (TextoMessageEntity) messageHandler.handleMessage(message, client.getOutputStream());
                    messageRepository.saveMessage(textoMessageEntity) ;
                    break;

                case INFO_USUARIO:
                    logger.debug("handling INFO_USUARIO");
                    messageHandler = new MessageHandler_INFO_USUARIO();
                    InfoUsuarioMessageEntity infoUsuarioMessageEntity =
                            (InfoUsuarioMessageEntity) messageHandler.handleMessage(message, client.getOutputStream());
                    messageRepository.saveMessage(infoUsuarioMessageEntity) ;
                    break;

                case SOLICITACAO_DATA_HORA:
                    logger.debug("handling SOLICITACAO_DATA_HORA");
                    messageHandler = new MessageHandler_SOLICITACAO_DATA_HORA();
                    SolicitacaoDataHoraMessageEntity solicitacaoDataHoraMessageEntity =
                            (SolicitacaoDataHoraMessageEntity) messageHandler.handleMessage(message, client.getOutputStream());
                    messageRepository.saveMessage(solicitacaoDataHoraMessageEntity) ;
                    break;

                case INVALID:
                    logger.debug("handling INVALID");
                    messageHandler = new MessageHandler_INVALID();
                    InvalidMessageEntity invalidMessageEntity
                            = (InvalidMessageEntity) messageHandler.handleMessage(message, client.getOutputStream());
                    messageRepository.saveMessage(invalidMessageEntity) ;
                    continue;
            }

        }
    }


    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {        return this.server.getLocalPort();

    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        System.out.println("\r\nRunning Server: " + "Host=" + server.getSocketAddress().getHostAddress() + " Port=" + server.getPort());
        server.receiveIncomingConnections();
    }


}