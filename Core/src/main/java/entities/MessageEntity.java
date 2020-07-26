package entities;

import converters.ByteArrayToHexStringConverter;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.*;



@Entity
@Table(name = "message")
@Inheritance(strategy = InheritanceType.JOINED)
public class MessageEntity <T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "init")
    @Convert(converter = ByteArrayToHexStringConverter.class)
    protected byte[] init = new byte[1];

    @Column(name = "bytes")
    @Convert(converter = ByteArrayToHexStringConverter.class)
    protected byte[] bytes = new byte[1];

    @Column(name = "frame")
    @Convert(converter = ByteArrayToHexStringConverter.class)
    protected byte[] frame = new byte[1];

    @Column(name = "data")
    @Convert(converter = ByteArrayToHexStringConverter.class)
    protected byte[] data;

    @Column(name = "crc")
    @Convert(converter = ByteArrayToHexStringConverter.class)
    protected byte[] crc = new byte[1];

    @Column(name = "end")
    @Convert(converter = ByteArrayToHexStringConverter.class)
    protected byte[] end = new byte[1];

    public MessageEntity() { }


    public MessageEntity(byte[] init,
                         byte[] bytes,
                         byte[] frame,
                         byte[] data,
                         byte[] crc,
                         byte[] end) {


        this.init = init;
        this.bytes = bytes;
        this.frame = frame;
        this.data = data;
        this.crc = crc;
        this.end = end;
    }

    public MessageEntity(byte init,
                         byte bytes,
                         byte frame,
                         byte[] data,
                         byte crc,
                         byte end) {


        this.init = new byte[]{init};
        this.bytes = new byte[]{bytes};
        this.frame = new byte[]{frame};
        this.data = data;
        this.crc = new byte[]{crc};
        this.end = new byte[]{end};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getInit() {
        return init;
    }

    public void setInit(byte[] init) {
        this.init = init;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getFrame() {
        return frame;
    }

    public void setFrame(byte[] frame) {
        this.frame = frame;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getCrc() {
        return crc;
    }

    public void setCrc(byte[] crc) {
        this.crc = crc;
    }

    public byte[] getEnd() {
        return end;
    }

    public void setEnd(byte[] end) {
        this.end = end;
    }

    public byte[] toByteArray() {
        byte[] byteArray = ArrayUtils.addAll(this.getInit(), this.getBytes());
        byteArray = ArrayUtils.addAll(byteArray, this.getFrame());
        byteArray = ArrayUtils.addAll(byteArray, this.getData());
        byteArray = ArrayUtils.addAll(byteArray, this.getCrc());
        byteArray = ArrayUtils.addAll(byteArray, this.getEnd());
        return byteArray;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nMESSAGE:\n");
        stringBuilder.append(" INIT: " +  Hex.encodeHexString(init) + "\n");
        stringBuilder.append(" BYTES: " + Hex.encodeHexString(bytes) + "\n");
        stringBuilder.append(" FRAME: " + Hex.encodeHexString(frame) + "\n");
        stringBuilder.append(" DATA: " +  Hex.encodeHexString(data) + "\n");
        stringBuilder.append(" CRC: " +   Hex.encodeHexString(crc) + "\n");
        stringBuilder.append(" END: " +   Hex.encodeHexString(end) );

        return stringBuilder.toString();
    }

    public T fromMessageEntity(MessageEntity messageEntity){

        this.init = messageEntity.init;
        this.bytes = messageEntity.bytes;
        this.frame = messageEntity.frame;
        this.data = messageEntity.data;
        this.crc = messageEntity.crc;
        this.end = messageEntity.end;

        return (T) this;
    }


}
