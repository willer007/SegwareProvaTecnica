import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import entities.InfoUsuarioDataEntity;
import entities.MessageEntity;
import enums.FrameEnum;
import factories.MessageFactory;
import org.apache.commons.lang3.ArrayUtils;
import utils.MessageUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private Scanner scanner;
    private static int PORT = 55000;
    private MessageFactory messageFactory;
    private MessageEntity messageEntity;
    private FrameEnum frameEnum;

    private Client(InetAddress serverAddress) throws Exception {
        this.socket = new Socket(serverAddress, PORT);
        this.scanner = new Scanner(System.in);
    }

    private void start() throws IOException {
        String input;

        messageFactory = new MessageFactory();

//          MENSAGEM DE TEXTO
//        TESTADO E FUNCIONANDO FAZER TESTE UNITARIO DEPOIS
//        while (true) {
//
//            input = scanner.nextLine();
//
//            messageEntity  = messageFactory.buildTextMessage(input);
//
//            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
//            out.write(messageEntity.toByteArray());
//            out.flush();
//        }

        //MENSAGEM DE INFO DE USUARIO
        //        TESTADO E FUNCIONANDO FAZER TESTE UNITARIO DEPOIS

//        InfoUsuarioDataEntity infoUsuarioDataEntity = new InfoUsuarioDataEntity((byte)32,(byte)122,(byte)195,(byte)12,"teste".getBytes());
//        while (true) {
//
//            input = scanner.nextLine();
//
//            messageEntity  = messageFactory.buildInfoUsuarioMessage(infoUsuarioDataEntity);
//
//            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
//            out.write(messageEntity.toByteArray());
//            out.flush();
//        }


        //MENSAGEM DE SOLICITACAO DE DATA
        //        TESTADO E FUNCIONANDO FAZER TESTE UNITARIO DEPOIS

        while (true) {

            input = scanner.nextLine();

            messageEntity = messageFactory.buildSolicitacaoDataHoraMessage("America/Sao_Paulo");

            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());

            out.write(messageEntity.toByteArray());
            out.flush();

            MessageEntity response = new MessageEntity();
            MessageUtils messageUtils = new MessageUtils();


            if (messageUtils.readMessage(socket, response)) {
                frameEnum = FrameEnum.getEnumByBytes(response.getMessageFRAME()[0]);
                switch (frameEnum) {
                    case SOLICITACAO_DATA_HORA:
                        for (byte b : response.getMessageDATA()) {
                            System.out.println(Byte.toUnsignedInt(b));
                        }
                        break;

                    case ACK:
                        System.out.println("Mensagem recebida pelo servidor");
                        break;

                }


            }

        }
    }

    public static void main(String[] args) throws Exception {

        InetAddress ip;
        String hostname;
        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        System.out.println("Your current IP address : " + ip);
        System.out.println("Your current Hostname : " + hostname);

        Client client = new Client(ip);

        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }
}
