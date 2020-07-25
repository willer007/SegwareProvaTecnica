
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


    //START SERVER ON LOCALHOST:55000
    public Server() throws Exception {
        this.server = new ServerSocket(PORT, 1, InetAddress.getLocalHost());
    }


    //RECEIVE INCOMING CONNECTIONS
    private void receiveIncomingConnections() throws Exception {

        while (true) {

            Socket client = this.server.accept();

            new Thread(() -> {
                try {
                    listen(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
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
            if(!messageUtils.readMessage(client,message)){
                continue;
            }

            //VERIFY INTEGRITY BY CRC8
            if(!messageUtils.checkMessageIntegrity(calculator,message)){
                continue;
            }


            //SPECIFIC HANDLER FOR EACH MESSAGE TYPE
            frameEnum = FrameEnum.getEnumByBytes(message.getMessageFRAME()[0]);
            switch (frameEnum) {
                case MENSAGEM_TEXTO:
                    messageHandler = new MessageHandler_MENSAGEM_TEXTO();
                    messageHandler.handleMessage(message, client.getOutputStream());
                    break;

                case INFO_USUARIO:
                    messageHandler = new MessageHandler_INFO_USUARIO();
                    messageHandler.handleMessage(message, client.getOutputStream());
                    break;

                case SOLICITACAO_DATA_HORA:
                    messageHandler = new MessageHandler_SOLICITACAO_DATA_HORA();
                    messageHandler.handleMessage(message, client.getOutputStream());
                    break;

                case INVALID:
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
        System.out.println("\r\nRunning Server: " +  "Host=" + server.getSocketAddress().getHostAddress() + " Port=" + server.getPort());

        server.receiveIncomingConnections();
    }


}