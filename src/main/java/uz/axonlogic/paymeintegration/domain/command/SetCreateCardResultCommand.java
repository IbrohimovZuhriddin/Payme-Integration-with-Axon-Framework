package uz.axonlogic.paymeintegration.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

@Data
@AllArgsConstructor
public class SetCreateCardResultCommand {

    @TargetAggregateIdentifier
    private String id;
    private Map<String, Object> response;
}