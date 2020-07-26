package entities;

import javax.persistence.*;

@Entity
@Table(name = "message_texto")
@PrimaryKeyJoinColumn(name="id")
public class TextoMessageEntity extends MessageEntity<TextoMessageEntity> {

    @Column(name = "texto")
    protected String texto;


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
