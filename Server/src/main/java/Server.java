import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import crccalc.Crc8;
import crccalc.CrcCalculator;
import enums.FrameEnum;
import entities.MessageEntity;
import handlers.*;
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


            //SPECIFIC HANDLER FOR EACH MESSAGE TYPE
            frameEnum = FrameEnum.getEnumByBytes(message.getMessageFRAME()[0]);
            switch (frameEnum) {
                case MENSAGEM_TEXTO:
                    logger.debug("handling MENSAGEM_TEXTO");
                    logger.info("PORT: " + client.getPort()  + message);
                    messageHandler = new MessageHandler_MENSAGEM_TEXTO();
                    messageHandler.handleMessage(message, client.getOutputStream());
                    break;

                case INFO_USUARIO:
                    logger.debug("handling INFO_USUARIO");
                    logger.info("PORT: " + client.getPort()  + message);
                    messageHandler = new MessageHandler_INFO_USUARIO();
                    messageHandler.handleMessage(message, client.getOutputStream());
                    break;

                case SOLICITACAO_DATA_HORA:
                    logger.debug("handling SOLICITACAO_DATA_HORA");
                    logger.info("PORT: " + client.getPort()  + message);
                    messageHandler = new MessageHandler_SOLICITACAO_DATA_HORA();
                    messageHandler.handleMessage(message, client.getOutputStream());
                    break;

                case INVALID:
                    logger.debug("handling INVALID");
                    logger.info("PORT: " + client.getPort()  + message);
                    messageHandler = new MessageHandler_INVALID();
                    messageHandler.handleMessage(message, client.getOutputStream());
                    break;

            }

        }
    }


    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

    public static void main(String[] args) throws Exception {

        Server server = new Server();
        System.out.println("\r\nRunning Server: " + "Host=" + server.getSocketAddress().getHostAddress() + " Port=" + server.getPort());
        server.receiveIncomingConnections();
    }


}