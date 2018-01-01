package org.springframework5.websocket.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework5.websocket.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Parisana
 */
//public interface UserRepo extends ReactiveMongoRepository<User, String>{
public interface UserRepo{
    Mono<User> getUser(String id);
    Flux<User> getAllUser();
    Mono<Void> saveUser(Mono<User> userMono);
}
