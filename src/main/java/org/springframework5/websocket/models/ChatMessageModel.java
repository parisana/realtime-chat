package org.springframework5.websocket.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author Parisana
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatMessageModel {
    @Id
    private String id;

    private String text;

    private Date createDate;

    public ChatMessageModel(String text, Date createDate) {
        this.text = text;
        this.createDate = createDate;
    }
}
