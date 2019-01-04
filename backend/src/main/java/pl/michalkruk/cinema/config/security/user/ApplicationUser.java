package pl.michalkruk.cinema.config.security.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;
}
