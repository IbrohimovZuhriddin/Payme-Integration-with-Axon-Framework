package uz.axonlogic.paymeintegration.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CardGetVerifyFailedEvent {

    private String id;
    private Map<String, Object> response;
}
