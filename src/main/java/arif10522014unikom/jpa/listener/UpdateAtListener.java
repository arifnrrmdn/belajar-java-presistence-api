package arif10522014unikom.jpa.listener;

import arif10522014unikom.jpa.entity.UpdateAtAware;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UpdateAtListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdateAt(UpdateAtAware object){
        object.setUpdateAt(LocalDateTime.now());
    }


}
