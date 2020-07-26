package converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ByteToIntConverter implements AttributeConverter<Byte, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Byte aByte) {
        return Byte.toUnsignedInt(aByte);
    }

    @Override
    public Byte convertToEntityAttribute(Integer integer) {
        return integer.byteValue();
    }
}
