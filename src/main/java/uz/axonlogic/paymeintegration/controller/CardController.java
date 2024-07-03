package uz.axonlogic.paymeintegration.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.axonlogic.paymeintegration.domain.command.CardGetVerifyCommand;
import uz.axonlogic.paymeintegration.domain.command.CreateCardCommand;

@RestController
@RequestMapping
public class CardController {

    private final CommandGateway commandGateway;

    @Autowired
    public CardController ( CommandGateway commandGateway ) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/cards.create")
    public void createCard ( @RequestBody CreateCardCommand command) {
        commandGateway.send ( command );
    }

    @PostMapping("/cards.get-verify-command")
    public void cardGetVerifiyCommand(@RequestBody CardGetVerifyCommand command){
        commandGateway.send(command);
    }


}