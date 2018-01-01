package org.springframework5.websocket.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework5.websocket.services.UserHandler;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @author Parisana
 */
@Slf4j
@Controller
public class ChatMessageController {
    public static final String HOST = "localhost";
    public static final int PORT= 8080;
    private final UserHandler userHandler;

    @Autowired
    public ChatMessageController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routingFunction(){
        log.debug("***********Routing********");
        return nest(path("/users"),
                nest(accept(MediaType.APPLICATION_JSON_UTF8),
                        route(GET("/{id}"), userHandler::getUser)
                        .andRoute(method(HttpMethod.GET), userHandler::allUsers)
                )
                .andRoute(POST("/").and(contentType(MediaType.APPLICATION_JSON_UTF8)), userHandler::saveUser)
        );
    }
    @Bean
    public HttpServer httpServer(RouterFunction<ServerResponse> routerFunction){
//        RouterFunction<ServerResponse> routerFunction = routingFunction();
        HttpHandler httpHandler = toHttpHandler(routerFunction);

        ReactorHttpHandlerAdapter adapter= new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create(HOST, PORT);

        server.newHandler(adapter);
        return server;
    }

}
