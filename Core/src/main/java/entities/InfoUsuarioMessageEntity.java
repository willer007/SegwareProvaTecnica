package entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "message_info_usuario")
@PrimaryKeyJoinColumn(name="id")
public class InfoUsuarioMessageEntity extends MessageEntity<InfoUsuarioMessageEntity> {



    protected  InfoUsuarioEntity infoUsuarioEntity;

    public InfoUsuarioEntity getInfoUsuarioEntity() {
        return infoUsuarioEntity;
    }

    public void setInfoUsuarioEntity(InfoUsuarioEntity infoUsuarioEntity) {
        this.infoUsuarioEntity = infoUsuarioEntity;
    }

    public void setInfoUsuarioEntity(byte[] infoUsuarioEntity) {
        this.infoUsuarioEntity = new InfoUsuarioEntity(infoUsuarioEntity);
    }


}
