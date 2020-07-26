package entities;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;

public class MessageEntity {


    private byte[] messageINIT = new byte[1];
    private byte[] messageBYTES = new byte[1];
    private byte[] messageFRAME = new byte[1];
    private byte[] messageDATA;
    private byte[] messageCRC = new byte[1];
    private byte[] messageEND = new byte[1];

    public MessageEntity() {
    }

    ;

    public MessageEntity(byte[] messageINIT,
                         byte[] messageBYTES,
                         byte[] messageFRAME,
                         byte[] messageDATA,
                         byte[] messageCRC,
                         byte[] messageEND) {


        this.messageINIT = messageINIT;
        this.messageBYTES = messageBYTES;
        this.messageFRAME = messageFRAME;
        this.messageDATA = messageDATA;
        this.messageCRC = messageCRC;
        this.messageEND = messageEND;
    }

    public MessageEntity(byte messageINIT,
                         byte messageBYTES,
                         byte messageFRAME,
                         byte[] messageDATA,
                         byte messageCRC,
                         byte messageEND) {


        this.messageINIT = new byte[]{messageINIT};
        this.messageBYTES = new byte[]{messageBYTES};
        this.messageFRAME = new byte[]{messageFRAME};
        this.messageDATA = messageDATA;
        this.messageCRC = new byte[]{messageCRC};
        this.messageEND = new byte[]{messageEND};
    }

    public byte[] getMessageINIT() {
        return messageINIT;
    }

    public void setMessageINIT(byte[] messageINIT) {
        this.messageINIT = messageINIT;
    }

    public byte[] getMessageBYTES() {
        return messageBYTES;
    }

    public void setMessageBYTES(byte[] messageBYTES) {
        this.messageBYTES = messageBYTES;
    }

    public byte[] getMessageFRAME() {
        return messageFRAME;
    }

    public void setMessageFRAME(byte[] messageFRAME) {
        this.messageFRAME = messageFRAME;
    }

    public byte[] getMessageDATA() {
        return messageDATA;
    }

    public void setMessageDATA(byte[] messageDATA) {
        this.messageDATA = messageDATA;
    }

    public byte[] getMessageCRC() {
        return messageCRC;
    }

    public void setMessageCRC(byte[] messageCRC) {
        this.messageCRC = messageCRC;
    }

    public byte[] getMessageEND() {
        return messageEND;
    }

    public void setMessageEND(byte[] messageEND) {
        this.messageEND = messageEND;
    }

    public byte[] toByteArray() {
        byte[] byteArray = ArrayUtils.addAll(this.getMessageINIT(), this.getMessageBYTES());
        byteArray = ArrayUtils.addAll(byteArray, this.getMessageFRAME());
        byteArray = ArrayUtils.addAll(byteArray, this.getMessageDATA());
        byteArray = ArrayUtils.addAll(byteArray, this.getMessageCRC());
        byteArray = ArrayUtils.addAll(byteArray, this.getMessageEND());
        return byteArray;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nMESSAGE:\n");
        stringBuilder.append(" INIT: " +  Hex.encodeHexString(messageINIT) + "\n");
        stringBuilder.append(" BYTES: " + Hex.encodeHexString(messageBYTES) + "\n");
        stringBuilder.append(" FRAME: " + Hex.encodeHexString(messageFRAME) + "\n");
        stringBuilder.append(" DATA: " +  Hex.encodeHexString(messageDATA) + "\n");
        stringBuilder.append(" CRC: " +   Hex.encodeHexString(messageCRC) + "\n");
        stringBuilder.append(" END: " +   Hex.encodeHexString(messageEND) );

        return stringBuilder.toString();
    }
}
