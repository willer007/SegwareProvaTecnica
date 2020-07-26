package entities;


public class SolicitacaoDataHoraEntity {
    private byte dataDIA;
    private byte dataMES;
    private byte dataANO;
    private byte dataHORA;
    private byte dataMINUTO;
    private byte dataSEGUNDO;

    public byte getDataDIA() {
        return dataDIA;
    }

    public void setDataDIA(byte dataDIA) {
        this.dataDIA = dataDIA;
    }

    public byte getDataMES() {
        return dataMES;
    }

    public void setDataMES(byte dataMES) {
        this.dataMES = dataMES;
    }

    public byte getDataANO() {
        return dataANO;
    }

    public void setDataANO(byte dataANO) {
        this.dataANO = dataANO;
    }

    public byte getDataHORA() {
        return dataHORA;
    }

    public void setDataHORA(byte dataHORA) {
        this.dataHORA = dataHORA;
    }

    public byte getDataMINUTO() {
        return dataMINUTO;
    }

    public void setDataMINUTO(byte dataMINUTO) {
        this.dataMINUTO = dataMINUTO;
    }

    public byte getDataSEGUNDO() {
        return dataSEGUNDO;
    }

    public void setDataSEGUNDO(byte dataSEGUNDO) {
        this.dataSEGUNDO = dataSEGUNDO;
    }

    public SolicitacaoDataHoraEntity(byte [] data){
        this.dataDIA = data[0] ;
        this.dataMES = data[1] ;
        this.dataANO = data[2];
        this.dataHORA = data[3];
        this.dataMINUTO = data[4];
        this.dataSEGUNDO = data[5];
    }
}
