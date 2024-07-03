package uz.axonlogic.paymeintegration.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
public class CardGetVerifySuccededEvent  implements Serializable {

    private String id;
    private Map<String, Object> response;

}