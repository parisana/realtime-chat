package org.springframework5.websocket.services;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author Parisana
 */
public interface UserHandler {
    Mono<ServerResponse> allUsers(ServerRequest request);

    Mono<ServerResponse> saveUser(ServerRequest request);

    Mono<ServerResponse> getUser(ServerRequest request);
}
