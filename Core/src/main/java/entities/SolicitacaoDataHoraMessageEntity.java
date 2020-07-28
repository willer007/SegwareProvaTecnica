package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "message_solicitacao_data_hora")
@PrimaryKeyJoinColumn(name="id")
public class SolicitacaoDataHoraMessageEntity extends MessageEntity<SolicitacaoDataHoraMessageEntity> {


    protected String timezone;


    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimezone(byte [] timezone) {
        this.timezone = new String(timezone);
    }






}
