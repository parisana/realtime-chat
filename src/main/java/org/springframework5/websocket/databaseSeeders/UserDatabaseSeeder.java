package org.springframework5.websocket.databaseSeeders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework5.websocket.models.User;
import org.springframework5.websocket.repositories.UserRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Parisana
 */
@Slf4j
@Component
public class UserDatabaseSeeder implements UserRepo {
    private final Map<String, User> userMap= new HashMap<>();

    public UserDatabaseSeeder(){
        log.info("Loading Bootstrap data");
        this.userMap.put("1", new User(UUID.randomUUID().toString(),"a@gmail.com","1234"));
        this.userMap.put("2", new User(UUID.randomUUID().toString(),"b@gmail.com","1234"));
    }

    @Override
    public Mono<User> getUser(final String id) {
        return Mono.justOrEmpty(this.userMap.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.userMap.values());
    }

    @Override
    public Mono<Void> saveUser(Mono<User> userMono) {
        return userMono.doOnNext(user -> {
            String id= String.valueOf(this.userMap.size()+1);
            this.userMap.put(id, user);
            log.info(String.format("Saved %s with id: %s", user, id));
        }).thenEmpty(Mono.empty());
    }
}
