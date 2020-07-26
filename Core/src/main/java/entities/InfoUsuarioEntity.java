package entities;

import converters.ByteArrayToHexStringConverter;
import converters.ByteArrayToStringConverter;
import converters.ByteToIntConverter;
import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.util.Arrays;

@Embeddable
public class InfoUsuarioEntity {

    public InfoUsuarioEntity(){}

    @Convert(converter = ByteToIntConverter.class)
    protected byte idade;

    @Convert(converter = ByteToIntConverter.class)
    protected byte peso;

    @Convert(converter = ByteToIntConverter.class)
    protected byte altura;

    @Convert(converter = ByteToIntConverter.class)
    protected byte tamanhoNome;

    @Convert(converter = ByteArrayToStringConverter.class)
    protected byte[] nome;

    public byte getIdade() {
        return idade;
    }

    public void setIdade(byte idade) {
        this.idade = idade;
    }

    public byte getPeso() {
        return peso;
    }

    public void setPeso(byte peso) {
        this.peso = peso;
    }

    public byte getAltura() {
        return altura;
    }

    public void setAltura(byte altura) {
        this.altura = altura;
    }

    public byte getTamanhoNome() {
        return tamanhoNome;
    }

    public void setTamanhoNome(byte tamanhoNome) {
        this.tamanhoNome = tamanhoNome;
    }

    public byte[] getNome() {
        return nome;
    }

    public void setNome(byte[] nome) {
        this.nome = nome;
    }

    public InfoUsuarioEntity(byte idade, byte peso, byte altura, byte tamanhoNome, byte[] nome){
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.tamanhoNome = tamanhoNome;
        this.nome = nome;
    }

    public InfoUsuarioEntity(byte [] data){
        this.idade = data[0] ;
        this.peso = data[1] ;
        this.altura = data[2];
        this.tamanhoNome = data[3];
        this.nome = Arrays.copyOfRange(data,4,4 + tamanhoNome);
    }

    public byte[] toByteArray(){
        byte[] byteArray = new byte[]{this.getIdade(), this.getPeso()};
        byteArray = ArrayUtils.addAll(byteArray, this.getAltura());
        byteArray = ArrayUtils.addAll(byteArray,this.getTamanhoNome());
        byteArray = ArrayUtils.addAll(byteArray,this.getNome());
        return byteArray;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nINFO USUARIO:\n");
        stringBuilder.append("IDADE: " + this.idade + "\n");
        stringBuilder.append("PESO: " + this.peso + "\n");
        stringBuilder.append("ALTURA: " + this.altura + "\n");
        stringBuilder.append("TAMANHO NOME:" + this.tamanhoNome + "\n");
        stringBuilder.append("NOME: " + new String(this.nome));
        return stringBuilder.toString();
    }
}
