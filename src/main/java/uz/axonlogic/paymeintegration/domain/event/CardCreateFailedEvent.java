package uz.axonlogic.paymeintegration.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;

@Data
@AllArgsConstructor
public class CardCreateFailedEvent {

    private String id;
    private Map<String, Object> response;

}