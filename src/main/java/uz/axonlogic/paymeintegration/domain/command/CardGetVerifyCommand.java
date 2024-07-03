package uz.axonlogic.paymeintegration.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CardGetVerifyCommand   implements Serializable {

    @TargetAggregateIdentifier
    private String id;

    public CardGetVerifyCommand() {  }
}