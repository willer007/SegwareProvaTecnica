package entities;


public class SolicitacaoDateDataEntity {
    private byte dataDIA;
    private byte dataMES;
    private byte dataANO;
    private byte dataHORA;
    private byte dataMINUTO;
    private byte dataSEGUNDO;


    public SolicitacaoDateDataEntity(byte [] data){
        this.dataDIA = data[0] ;
        this.dataMES = data[1] ;
        this.dataANO = data[2];
        this.dataHORA = data[4];
        this.dataMINUTO = data[5];
        this.dataSEGUNDO = data[6];
    }
}
