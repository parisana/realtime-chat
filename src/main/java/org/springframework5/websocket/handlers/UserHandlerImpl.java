package org.springframework5.websocket.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework5.websocket.models.User;
import org.springframework5.websocket.repositories.UserRepo;
import org.springframework5.websocket.services.UserHandler;
import reactor.core.publisher.Mono;

/**
 * @author Parisana
 */
@Slf4j
@Component
public class UserHandlerImpl implements UserHandler {
    private final UserRepo userRepo;

    @Autowired
    public UserHandlerImpl(UserRepo userRepo) {
        log.debug("Initializing UserHandler with repo:"+userRepo);
        this.userRepo = userRepo;
    }

    @Override
    public Mono<ServerResponse> allUsers(ServerRequest request){
        log.debug("*******Getting all users**************");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepo.getAllUser(), User.class);
    }

    @Override
    public Mono<ServerResponse> saveUser(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        Mono<Void> result = userRepo.saveUser(userMono);

        return ServerResponse.ok()
                .build(result);
    }

    @Override
    public Mono<ServerResponse> getUser(ServerRequest request){
        Mono<User> resultMono = userRepo.getUser(request.pathVariable("id"));
        log.debug("Getting User with id:"+request.pathVariable("id"));
        Mono<ServerResponse> notFoundMono = ServerResponse.notFound().build();

        return resultMono
                .flatMap(user -> ServerResponse.ok()
                            .syncBody(user))
                .switchIfEmpty(notFoundMono);
    }
}
