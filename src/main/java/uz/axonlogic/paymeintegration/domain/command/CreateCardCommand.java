package uz.axonlogic.paymeintegration.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateCardCommand {

    @TargetAggregateIdentifier
    private String id ;

    private String cardNumber;
    private String expire;

}