package entities;

import javax.persistence.*;

@Entity
@Table(name = "message_invalid")
@PrimaryKeyJoinColumn(name="id")
public class InvalidMessageEntity extends MessageEntity<InvalidMessageEntity> {



    @Column(name = "texto")
    protected String texto;


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
