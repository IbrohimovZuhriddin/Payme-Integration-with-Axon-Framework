package uz.axonlogic.paymeintegration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.axonlogic.paymeintegration.domain.event.CardCreatedEvent;
import uz.axonlogic.paymeintegration.domain.event.CardGetVerifyEvent;
import java.util.Map;

@Service
public class CardService {

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth", "5e982d7f8a7158e073915273");
        return headers;
    }

    public Map sentToPaymeReq (CardCreatedEvent event ) {
        String url = "https://checkout.test.paycom.uz/api";

        Map <String,Object> payload = Map.of(
                "id",123,
                "method", "cards.create",
                "params", Map.of(
                        "card", Map.of("number", event.getCardNumber(), "expire", event.getExpire()),
                        "save", false) );

        HttpEntity<Map <String,Object > > requestEntity = new HttpEntity<>(payload, getHttpHeaders() );
        Map map = restTemplate.postForObject ( url, requestEntity, Map.class );
        System.out.println ("map: -> " + map);
        return map;
//        Map result = (Map) map.get("result");
//        Map card = (Map) result.get("card");
//        String token = (String) card.get("token");
//        System.out.println ("result: -> " + result);
    }

    public Map cardsGetVerifyCode( CardGetVerifyEvent event ) {
        String url = "https://checkout.test.paycom.uz/api";
        Map<String, Object> payload = Map.of(
                "id", 123,
                "method", "cards.get_verify_code",
                "params", Map.of( "token", event.getToken() ));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>( payload, getHttpHeaders());

        Map map = restTemplate.postForObject(url, requestEntity, Map.class);

//        Map result = (Map) map.get("result");
//        System.out.println(result);
        return map;
    }

}