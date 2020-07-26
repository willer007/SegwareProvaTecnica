import entities.InfoUsuarioEntity;
import entities.MessageEntity;
import entities.SolicitacaoDataHoraEntity;
import enums.FrameEnum;
import factories.MessageFactory;
import utils.MessageUtils;

import javax.xml.bind.annotation.XmlType;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private Scanner scanner;
    private static int PORT = 55000;


    private Client(InetAddress serverAddress) throws Exception {
        this.socket = new Socket(serverAddress, PORT);
        this.scanner = new Scanner(System.in);
    }

    private void start() throws IOException {
        MessageFactory messageFactory = new MessageFactory();
        MessageEntity response = new MessageEntity();
        MessageUtils messageUtils = new MessageUtils();
        MessageEntity messageEntity = null;
        FrameEnum frameEnum;

        DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());

        String input;

        while (true) {

            input = scanner.nextLine();


            //PREPARE MESSAGE TO SEND
            int messageType = input.charAt(0) - 48;
            switch (messageType){
                case 1:
                    messageEntity = messageFactory.buildTextMessage(input);
                    break;

                case 2:
                    InfoUsuarioEntity infoUsuarioDataEntity = new InfoUsuarioEntity((byte)32,(byte)122,(byte)195,(byte)12,"willer".getBytes());
                    messageEntity = messageFactory.buildInfoUsuarioMessage(infoUsuarioDataEntity);
                    break;

                case 3:
                    messageEntity = messageFactory.buildSolicitacaoDataHoraMessage("America/Sao_Paulo");
                    break;

                default:
                    messageEntity = new MessageEntity();
            }



            //SEND MESSAGE
            out.write(messageEntity.toByteArray());
            out.flush();



            //READ RESPONSE ACK
            if (messageUtils.readMessage(socket, response)) {

                frameEnum = FrameEnum.getEnumByBytes(response.getMessageFRAME()[0]);
                switch (frameEnum) {

                    case SOLICITACAO_DATA_HORA:
                        SolicitacaoDataHoraEntity solicitacaoDataHora =
                                new SolicitacaoDataHoraEntity(response.getMessageDATA());

                        System.out.println("solicitacaoDataHora.getDataDIA() = " + solicitacaoDataHora.getDataDIA());
                        System.out.println("solicitacaoDataHora.getDataMES() = " + solicitacaoDataHora.getDataMES());
                        System.out.println("solicitacaoDataHora.getDataANO() = " + solicitacaoDataHora.getDataANO());
                        System.out.println("solicitacaoDataHora.getDataHORA() = " + solicitacaoDataHora.getDataHORA());
                        System.out.println("solicitacaoDataHora.getDataMINUTO() = " + solicitacaoDataHora.getDataMINUTO());
                        System.out.println("solicitacaoDataHora.getDataSEGUNDO() = " + solicitacaoDataHora.getDataSEGUNDO());

                        break;

                    case ACK:
                        System.out.println("Mensagem recebida pelo servidor !");
                        break;

                    default:
                        System.out.println("Falha no servidor !");
                        break;


                }


            }

        }
    }

    public static void main(String[] args) throws Exception {

        Client client = new Client( InetAddress.getLocalHost());
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }
}
