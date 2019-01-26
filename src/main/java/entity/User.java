package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@ToString(exclude = {"tributesByUser","stewardGames"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotNull
    @Column(name = "nick", length = 30, unique = true)
    private String nick;

    @NotNull
    @JsonIgnore
    @Column(name = "password", length = 128)
    private String password;

    @NotNull
    @Column(name = "surname", length = 30)
    private String surname;

    @NotNull
    @Column(name = "name", length = 30)
    private String name;

    @Min(0)
    @Column(name = "height")
    private int height;

    @Min(0)
    @Column(name = "weight")
    private int weight;

    @NotNull
    @Column(name = "sex")
    private boolean sex;

    @Column(name = "birthday")
    private Calendar birthday;

    @Column(name = "last_activity")
    private Calendar lastActivity;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @Min(0)
    @Column(name = "cash")
    private int cash;

    @Column(name = "picture")
    private byte[] picture;

    @JsonIgnore
    @OneToMany(mappedBy = "steward", fetch = FetchType.LAZY)
    private Collection<Game> stewardGames;

    @JsonIgnore
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private Collection<PresentsToTribute> presentstotributesByUserid;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<Tribute> tributesByUser;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presents_to_tribute",
            joinColumns = {@JoinColumn(name = "sender_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Collection<Tribute> recipients;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Collection<Role> roles;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_skill",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Collection<Skill> skills;

    @ManyToOne
    @JoinColumn(name = "district", referencedColumnName = "district_id")
    private District district;

    public User(String nick, String password, String surname, String name, int height, int weight, boolean sex, District district, Calendar birthday, byte[] picture, Status status, int cash) {
        this.nick = nick;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.birthday = birthday;
        this.picture = picture;
        this.cash = cash;
        this.status = status;
        this.district = district;
        this.lastActivity = Calendar.getInstance();
    }

    public User(String nick, String password, String surname, String name, byte[] picture, Status status, int cash) {
        this.nick = nick;
        this.password = password;this.surname = surname;
        this.name = name;
        this.picture = picture;
        this.status = status;
        this.lastActivity = Calendar.getInstance();
        this.cash = cash;
    }



}