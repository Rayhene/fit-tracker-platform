package br.com.aps.fittracker.model.api;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Component
public class AccountServiceAPI implements IAccountAPI {
    
    
    private WebClient.Builder webClientBuilder;

    @Value("${gateway_account_url}")
    private String accountServiceUrl;

    @Autowired
    public AccountServiceAPI(WebClient.Builder loadBalancedWebClientBuilder) {
        this.webClientBuilder = loadBalancedWebClientBuilder; // Usando o WebClient.Builder com balanceamento de carga
    }

    //@Autowired
    //public AccountServiceAPI(WebClient.Builder wBuilder) {
    //    System.out.println("\n#########\n" + "wBuilder" + "\n#########\n");
    //    //this.webClient = wBuilder.baseUrl("http://localhost:8084/account").build();
    //    this.webClient = wBuilder.baseUrl("lb://account").build();
    //    
    //}

    @CircuitBreaker(name = "accountService", fallbackMethod = "fallbackExisteUsuario")
    @Override
    public boolean existeUsuario(Long usuarioId) {
        System.out.println("\n########\nAccountServiceAPI.existeUsuario() " + usuarioId);
        System.out.println("accountServiceUrl:  " + accountServiceUrl + "\n########\n");

        return (boolean)
                (webClientBuilder.build()
                        .get()
                        //.uri("lb://account/usuario/existe/{usuarioId}", usuarioId)
                        .uri(accountServiceUrl + "/usuario/existe/{usuarioId}", usuarioId)
                        .retrieve()
                        .bodyToMono(Map.class))
                .block()
                .get("existeUsuario");
    }

    public boolean fallbackExisteUsuario(Long usuarioId, Throwable throwable) {
         // Registra a exceção em algum lugar
        System.out.println("\n#########\n" + "Falha ao verificar se o usuário existe" + throwable.getMessage() + "\n#########\n");
        // Retorna um valor padrão ou lança uma exceção personalizada
        return false; // Retorna false como padrão
    }



        /*
    private ReactiveCircuitBreaker circuitBreaker;
    private WebClient webClient;

    @Value("${account_service_url}")
    private String accountServiceUrl;


    public AccountServiceAPI(WebClient.Builder wBuilder, ReactiveCircuitBreakerFactory cbFactory, DiscoveryClient discoveryClient) {
        //ServiceInstance sInstance = discoveryClient.getInstances("account").iterator().next();
        //String url = String.format("%s://%s:%s", sInstance.getScheme(), sInstance.getHost(), sInstance.getPort());
        //ServiceInstance sInstance = discoveryClient.getInstances("gateway").iterator().next();
        //String url = String.format("%s://%s:%s", sInstance.getScheme(), sInstance.getHost(), sInstance.getPort());
        //System.out.println("\n####################\n" + url + "\n####################\n");
        //this.webClient = wBuilder.baseUrl(url).build();
        this.webClient = wBuilder.baseUrl("lb://account").build();
        //this.webClient = wBuilder.baseUrl("http://localhost:8084/account").build();
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
/*
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
    */
}
