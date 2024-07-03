package uz.axonlogic.paymeintegration.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.axonlogic.paymeintegration.entity.CardRepository;
import uz.axonlogic.paymeintegration.domain.command.SetCreateCardResultCommand;
import uz.axonlogic.paymeintegration.domain.event.CardCreatedEvent;
import uz.axonlogic.paymeintegration.domain.event.CardGetVerifyEvent;
import uz.axonlogic.paymeintegration.domain.event.SetCardGetVerifyCommand;
import uz.axonlogic.paymeintegration.entity.CardEntity;
import uz.axonlogic.paymeintegration.service.CardService;

import java.util.Map;

@Component
public class CardSaga {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private CardService cardService;

    private final CardRepository repository;

    public CardSaga(CardRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void handle(CardCreatedEvent event) {
        Map<String, Object> response = cardService.sentToPaymeReq(event);

        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(event.getId());
        cardEntity.setNumber(event.getCardNumber());
        repository.save(cardEntity);
        commandGateway.send(new SetCreateCardResultCommand(event.getId(), response));
    }

    @EventHandler
    public void handle(CardGetVerifyEvent event) {
        Map<String, Object> response = cardService.cardsGetVerifyCode(event);

        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(event.getId());
        repository.save(cardEntity);

        commandGateway.send(new SetCardGetVerifyCommand(event.getId(), response));
    }
}