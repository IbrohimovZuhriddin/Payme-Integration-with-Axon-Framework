package uz.axonlogic.paymeintegration.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CardGetVerifyEvent implements Serializable {

    private String id;
    private String token;

}
