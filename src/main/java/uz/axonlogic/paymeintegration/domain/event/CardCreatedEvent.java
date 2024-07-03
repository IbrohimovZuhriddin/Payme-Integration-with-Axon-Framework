package uz.axonlogic.paymeintegration.domain.event;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardCreatedEvent {

    private String id;
    private String cardNumber;
    private String expire;

}
