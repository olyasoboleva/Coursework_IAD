package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userlogin", schema = "public", catalog = "postgres")
public class UserLoginEntity {

    private int loginId;
    private String nick;
    private String password;
    private UsersEntity user;

    public UserLoginEntity(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public UserLoginEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginId")
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Basic
    @NotNull
    @Column(name = "nick", length = 30, unique = true)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Basic
    @NotNull
    @Column(name = "password", length = 40)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(mappedBy = "userLogin")
    public UsersEntity getUser() {
        return user;
    }
    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
