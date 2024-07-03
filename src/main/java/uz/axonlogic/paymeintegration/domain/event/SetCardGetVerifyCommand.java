package uz.axonlogic.paymeintegration.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

@Data
@AllArgsConstructor
public class SetCardGetVerifyCommand {

    @TargetAggregateIdentifier
    private String id;
    private Map<String, Object> response;
}
