package uz.axonlogic.paymeintegration.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import uz.axonlogic.paymeintegration.domain.command.CardGetVerifyCommand;
import uz.axonlogic.paymeintegration.domain.command.CreateCardCommand;
import uz.axonlogic.paymeintegration.domain.command.SetCreateCardResultCommand;
import uz.axonlogic.paymeintegration.domain.event.*;

import java.util.Map;

@Aggregate
public class CardAggregate {

    @AggregateIdentifier
    private String id;

    private String expire;
    private boolean save;
    private String cardNumber;
    private String token;

    public CardAggregate() {   }

    @CommandHandler
    public CardAggregate (CreateCardCommand command) {
        AggregateLifecycle.apply(new CardCreatedEvent(command.getId(), command.getCardNumber(), command.getExpire()));
    }

    @EventSourcingHandler
    public void on (CardCreatedEvent event) {
        this.id = event.getId();
        this.expire = event.getExpire();
        this.cardNumber = event.getCardNumber();
    }

    @CommandHandler
    public void handle(SetCreateCardResultCommand command) {
        if (command.getResponse().get("result") != null) {
            AggregateLifecycle.apply(new CardCreateSuccededEvent(id, command.getResponse()));
        } else {
            AggregateLifecycle.apply(new  CardCreateFailedEvent(id, command.getResponse()));
        }
    }

    @EventSourcingHandler
    public void on ( CardCreateSuccededEvent event){
        Map<String, Object> resultMap = (Map<String, Object>) event.getResponse().get("result");
        Map<String, Object> cardMap = (Map<String, Object>) resultMap.get("card");
        this.token= (String) cardMap.get("token");
    }

    @EventSourcingHandler
    public void on  ( CardCreateFailedEvent event) {
    }

    ////////////////////////////////


    @CommandHandler
    public void cardGetVerifyCommand( CardGetVerifyCommand command){
        AggregateLifecycle.apply(new CardGetVerifyEvent ( command.getId(), token ));
    }

    @EventSourcingHandler
    public void on (CardGetVerifyEvent event){
//        this.
    }

    @CommandHandler
    public void handle(SetCardGetVerifyCommand command){
        if (command.getResponse().get("result") != null) {
            AggregateLifecycle.apply(new CardGetVerifySuccededEvent (id, command.getResponse() ));
        }else {
            AggregateLifecycle.apply(new CardGetVerifyFailedEvent (id, command.getResponse()));
        }
    }

    @EventSourcingHandler
    public void  on (CardGetVerifySuccededEvent event){
       Map resultMap = (Map) event.getResponse().get("result");
       Object wait = resultMap.get("wait");
       System.out.println(wait);
    }
}