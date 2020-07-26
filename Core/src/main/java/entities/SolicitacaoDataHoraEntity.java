package entities;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SolicitacaoDataHoraEntity {

    @Column(name = "dia")
    protected byte dia;

    @Column(name = "mes")
    protected byte mes;

    @Column(name = "ano")
    protected byte ano;

    @Column(name = "hora")
    protected byte hora;

    @Column(name = "minuto")
    protected byte minuto;

    @Column(name = "segundo")
    protected byte segundo;

    public byte getDia() {
        return dia;
    }

    public void setDia(byte dia) {
        this.dia = dia;
    }

    public byte getMes() {
        return mes;
    }

    public void setMes(byte mes) {
        this.mes = mes;
    }

    public byte getAno() {
        return ano;
    }

    public void setAno(byte ano) {
        this.ano = ano;
    }

    public byte getHora() {
        return hora;
    }

    public void setHora(byte hora) {
        this.hora = hora;
    }

    public byte getMinuto() {
        return minuto;
    }

    public void setMinuto(byte minuto) {
        this.minuto = minuto;
    }

    public byte getSegundo() {
        return segundo;
    }

    public void setSegundo(byte segundo) {
        this.segundo = segundo;
    }

    public SolicitacaoDataHoraEntity(byte [] data){
        this.dia = data[0] ;
        this.mes = data[1] ;
        this.ano = data[2];
        this.hora = data[3];
        this.minuto = data[4];
        this.segundo = data[5];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSOLICITACAO DATA HORA:\n");
        stringBuilder.append("DIA: " + this.dia + "\n");
        stringBuilder.append("MES: " +this.mes + "\n");
        stringBuilder.append("ANO: " +this.ano + "\n");
        stringBuilder.append("HORA: " +this.hora + "\n");
        stringBuilder.append("MINUTO: " +this.minuto + "\n") ;
        stringBuilder.append("SEGUNDO: " +this.segundo + "\n") ;
        return stringBuilder.toString();
    }
}
