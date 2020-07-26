package entities;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class InfoUsuarioEntity {

    private byte dataIDADE;
    private byte dataPESO;
    private byte dataALTURA;
    private byte dataTAMANHO_NOME;
    private byte[] dataNOME;

    public byte getDataIDADE() {
        return dataIDADE;
    }

    public void setDataIDADE(byte dataIDADE) {
        this.dataIDADE = dataIDADE;
    }

    public byte getDataPESO() {
        return dataPESO;
    }

    public void setDataPESO(byte dataPESO) {
        this.dataPESO = dataPESO;
    }

    public byte getDataALTURA() {
        return dataALTURA;
    }

    public void setDataALTURA(byte dataALTURA) {
        this.dataALTURA = dataALTURA;
    }

    public byte getDataTAMANHO_NOME() {
        return dataTAMANHO_NOME;
    }

    public void setDataTAMANHO_NOME(byte dataTAMANHO_NOME) {
        this.dataTAMANHO_NOME = dataTAMANHO_NOME;
    }

    public byte[] getDataNOME() {
        return dataNOME;
    }

    public void setDataNOME(byte[] dataNOME) {
        this.dataNOME = dataNOME;
    }

    public InfoUsuarioEntity(byte dataIDADE, byte dataPESO, byte dataALTURA, byte dataTAMANHO_NOME, byte[] dataNOME ){
        this.dataIDADE = dataIDADE ;
        this.dataPESO = dataPESO;
        this.dataALTURA = dataALTURA;
        this.dataTAMANHO_NOME = dataTAMANHO_NOME;
        this.dataNOME = dataNOME;
    }

    public InfoUsuarioEntity(byte [] data){
        this.dataIDADE = data[0] ;
        this.dataPESO = data[1] ;
        this.dataALTURA = data[2];
        this.dataTAMANHO_NOME = data[3];
        this.dataNOME = Arrays.copyOfRange(data,4,4 + dataTAMANHO_NOME);
    }

    public byte[] toByteArray(){
        byte[] byteArray = new byte[]{this.getDataIDADE(), this.getDataPESO()};
        byteArray = ArrayUtils.addAll(byteArray, this.getDataALTURA());
        byteArray = ArrayUtils.addAll(byteArray,this.getDataTAMANHO_NOME());
        byteArray = ArrayUtils.addAll(byteArray,this.getDataNOME());
        return byteArray;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nINFO USUARIO:\n");
        stringBuilder.append("IDADE: " + this.dataIDADE + "\n");
        stringBuilder.append("PESO: " + this.dataPESO + "\n");
        stringBuilder.append("ALTURA: " + this.dataALTURA + "\n");
        stringBuilder.append("TAMANHO NOME:" + this.dataTAMANHO_NOME + "\n");
        stringBuilder.append("NOME: " + new String(this.dataNOME));
        return stringBuilder.toString();
    }
}
