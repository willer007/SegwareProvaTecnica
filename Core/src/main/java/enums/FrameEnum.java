package enums;

public enum FrameEnum {
    ACK((byte)0xA0),
    MENSAGEM_TEXTO((byte)0xA1),
    INFO_USUARIO((byte)0xA2),
    SOLICITACAO_DATA_HORA((byte)0xA3),
    INVALID ((byte)0x00);


    public byte byteValue;

    FrameEnum(byte value) {
        byteValue = value;
    }

    public static FrameEnum parseByteToFrameEnum(byte byteValue){
        if(byteValue == (byte)0xA0){
            return FrameEnum.ACK;
        }else if(byteValue == (byte)(byte)0xA1) {
            return FrameEnum.MENSAGEM_TEXTO;

        }else if (byteValue == (byte)0xA2){
            return FrameEnum.INFO_USUARIO;

        }else if (byteValue == (byte)0xA3){
            return FrameEnum.SOLICITACAO_DATA_HORA;

        }
        return INVALID;
    }
}
