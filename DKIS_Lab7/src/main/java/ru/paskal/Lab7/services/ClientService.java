package ru.paskal.Lab7.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.paskal.Lab7.models.MusicalInstrument;

@Service
public class ClientService {

  private final WebClient webClient;

  @Autowired
  public ClientService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<List<MusicalInstrument>> getAll() {
    return webClient.get()
        .uri("/instruments/api")
        .retrieve()
        .bodyToFlux(MusicalInstrument.class)
        .collectList();
  }

  public Mono<MusicalInstrument> getById(int id) {
    return webClient.get()
        .uri("/instruments/api/" + id)
        .retrieve().bodyToMono(MusicalInstrument.class);
  }

  public Mono<List<MusicalInstrument>> getFilteredByPrice(Float price) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/instruments/api")
            .queryParam("price", price)
            .build())
        .retrieve()
        .bodyToFlux(MusicalInstrument.class)
        .collectList();
  }

  public void create(MusicalInstrument instrument) {
    webClient.post()
        .uri("/instruments/api")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(instrument), MusicalInstrument.class)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  public void update(int id, MusicalInstrument updatedInstrument) {
    webClient.put()
        .uri("/instruments/api/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(updatedInstrument))
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  public void delete(int id) {
    webClient.delete()
        .uri("/instruments/api/{id}", id)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }
  public void wipeAll() {
    webClient.delete()
        .uri("/instruments/api")
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  public void fillExample() {
    webClient.patch()
        .uri("/instruments/api")
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

}
