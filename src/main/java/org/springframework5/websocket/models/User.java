package org.springframework5.websocket.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author Parisana
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    private String id;
    private String email;
    private String password;

    public User(@JsonProperty("u_id") String id, @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
        this.id=id;
    }
}
