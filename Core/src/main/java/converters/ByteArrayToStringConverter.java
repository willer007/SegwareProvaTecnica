package converters;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ByteArrayToStringConverter implements AttributeConverter<byte[] ,String> {



    @Override
    public String convertToDatabaseColumn(byte[] bytes) {
        return new String(bytes);
    }

    @Override
    public byte[] convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData))
            return new byte[0];

        return dbData.getBytes();
    }
}
