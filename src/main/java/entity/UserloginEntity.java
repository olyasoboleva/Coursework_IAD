package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userlogin", schema = "public", catalog = "postgres")
public class UserloginEntity {

    private int loginid;
    private String nick;
    private String password;
    private UsersEntity user;

    public UserloginEntity(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public UserloginEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loginid")
    public int getLoginid() {
        return loginid;
    }

    public void setLoginid(int loginid) {
        this.loginid = loginid;
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

    @OneToOne(mappedBy = "userlogin")
    public UsersEntity getUser() {
        return user;
    }
    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
