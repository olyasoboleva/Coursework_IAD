package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_login", schema = "public", catalog = "postgres")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Integer loginId;

    @NotNull
    @Column(name = "nick", length = 30, unique = true)
    private String nick;

    @NotNull
    @Column(name = "password", length = 40)
    private String password;

    @OneToOne(mappedBy = "userLogin")
    private User user;

    public UserLogin(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserLogin userLogin = (UserLogin) o;
        return Objects.equals(loginId, userLogin.loginId) &&
                Objects.equals(nick, userLogin.nick) &&
                Objects.equals(password, userLogin.password) &&
                Objects.equals(user, userLogin.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), loginId, nick, password, user);
    }
}
