package org.springframework5.websocket.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework5.websocket.models.ChatMessageModel;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author Parisana
 */
public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessageModel, String>{
    Flux<ChatMessageModel> findAllByOrderByCreateDateAsc();
}
