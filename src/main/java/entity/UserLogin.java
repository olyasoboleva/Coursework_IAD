package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userlogin", schema = "public", catalog = "postgres")
public class UserLogin {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginId")
    private int loginId;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "nick", length = 30, unique = true)
    private String nick;

    @Getter
    @Setter
    @Basic
    @NotNull
    @Column(name = "password", length = 40)
    private String password;

    @Getter
    @Setter
    @OneToOne(mappedBy = "userLogin")
    private User user;

    public UserLogin(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public UserLogin() {}
}
