package br.com.aps.fittracker.model.api;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AccountServiceAPI implements IAccountAPI {
    
    private ReactiveCircuitBreaker circuitBreaker;
    private WebClient webClient;

    @Value("${account_service_url}")
    private String accountServiceUrl;

    public AccountServiceAPI(WebClient.Builder wBuilder, ReactiveCircuitBreakerFactory cbFactory) {
        //this.webClient = wBuilder.baseUrl("lb://account").build();
        this.webClient = wBuilder.baseUrl("http://localhost:8080").build();
        this.circuitBreaker = cbFactory.create("account");
    }
    
    @Override
    public boolean existeUsuario(Long usuarioId) {
        System.out.println("AccountServiceAPI.existeUsuario() " + usuarioId);
        
        /*return (boolean)
                webClient.get()
                        .uri("/usuario/existe/{usuarioId}", usuarioId)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block()
                        .get("existeUsuario");*/

        return (boolean)
                circuitBreaker
                        .run(
                                webClient
                                        .get()
                                        .uri("/usuario/existe/{usuarioId}",
                                                usuarioId)
                                        .retrieve()
                                        .bodyToMono(Map.class))
                        .block()
                        .get("existeUsuario");
    }
}
