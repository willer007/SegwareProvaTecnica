import entities.InfoUsuarioEntity;
import entities.MessageEntity;
import entities.SolicitacaoDataHoraEntity;
import enums.FrameEnum;
import factories.MessageFactorySingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.MessageUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private Scanner scanner;
    private static int PORT = 55000;
    private static final Logger logger = LogManager.getLogger("client-logger");
    MessageUtils messageUtils = new MessageUtils();
    MessageFactorySingleton messageFactorySingleton = new MessageFactorySingleton();

    FrameEnum frameEnum;


    private Client(InetAddress serverAddress) throws Exception {
        this.socket = new Socket(serverAddress, PORT);
        this.scanner = new Scanner(System.in);
    }

    private void printMessageIdentifierInfo() {
        System.out.println();
        System.out.println();

        System.out.println("Identificadores de mensagem: ");
        System.out.println("1 - Mensagem de texto");
        System.out.println("2 - Informações de um usuário");
        System.out.println("3 - Solicitacao de data e hora atual");

        System.out.println();
        System.out.println();

    }

    private MessageEntity sendMessage(String input, DataOutputStream out) throws IOException {
        MessageEntity requestMessage;

        //READ MESSAGE TYPE IDENTIFIER
        int messageType = input.trim().charAt(0) - 48;

        //BUILD MESSAGE BY TYPE IDENTIFIER
        switch (messageType) {
            case 1:
                System.out.println("Digite a mensagem: ");
                input = scanner.nextLine();
                requestMessage = messageFactorySingleton.buildTextMessage(input);
                break;

            case 2:
                System.out.println("Digite a idade: ");
                int idade = Integer.valueOf(scanner.nextLine());

                System.out.println("Digite o peso: ");
                int peso = Integer.valueOf(scanner.nextLine());

                System.out.println("Digite a altura: ");
                int altura = Integer.valueOf(scanner.nextLine());

                System.out.println("Digite o nome: ");
                String nome = scanner.nextLine();

                InfoUsuarioEntity infoUsuarioDataEntity = new InfoUsuarioEntity((byte) idade, (byte) peso, (byte) altura, (byte) nome.length(), nome.getBytes());
                requestMessage = messageFactorySingleton.buildInfoUsuarioMessage(infoUsuarioDataEntity);
                break;

            case 3:
                System.out.println("O timezone: ");
                input = scanner.nextLine();
                requestMessage = messageFactorySingleton.buildSolicitacaoDataHoraMessage(input);
                break;

            default:
                return  null;
        }

        //SEND MESSAGE
        out.write(requestMessage.toByteArray());
        out.flush();

        return requestMessage;

    }

    private void readResponse() throws IOException {

        MessageEntity response = new MessageEntity();

        //READ RESPONSE ACK
        if (messageUtils.readMessage(socket, response)) {

            //PARSER FRAM
            frameEnum = FrameEnum.parseByteToFrameEnum(response.getFrame()[0]);
            switch (frameEnum) {

                case SOLICITACAO_DATA_HORA:
                    SolicitacaoDataHoraEntity solicitacaoDataHora =
                            new SolicitacaoDataHoraEntity(response.getData());
                    System.out.println(solicitacaoDataHora.toString());
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

    private void start() throws IOException {
        MessageEntity requestMessage = null;

        printMessageIdentifierInfo();

        String input;
        DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());

        while (true) {

            //READ TYPE OF MESSAGE
            System.out.println("Digite o identificador de mensagem: ");
            input = scanner.nextLine();

            //IF OCCUR ERROR COM TO NEXT ITERATION
            if (input.trim().equals(""))
                continue;

            //READ MESSAGE FROM USER AND SEND
            requestMessage = sendMessage(input, out);

            //IF OCCUR ERROR COM TO NEXT ITERATION
            if(requestMessage == null)
                continue;

            //LOG MESSAGE TO FILE LOGGER
            logger.info("PORT: " + socket.getPort() + requestMessage.toString());

            //READ RESPONSE FROM SERVER
            readResponse();

            System.out.println();
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {

        Client client = new Client(InetAddress.getLocalHost());
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }
}
