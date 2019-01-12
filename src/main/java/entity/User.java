package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users", schema = "public", catalog = "postgres")
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
    private java.sql.Date lastActivity;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @Min(0)
    @Column(name = "cash")
    private int cash;

    @Column(name = "picture")
    private byte[] picture;

    @OneToMany(mappedBy = "steward", fetch = FetchType.LAZY)
    private Collection<Game> stewardGames;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private Collection<PresentsToTribute> presentstotributesByUserid;

    @JsonIgnore
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private Collection<Training> trainings;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<Tribute> tributesByUser;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presents_to_tribute",
            joinColumns = {@JoinColumn(name = "sender_id")},
            inverseJoinColumns = {@JoinColumn(name = "tribute_id")}
    )
    private Collection<Shop> sendings;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presents_to_tribute",
            joinColumns = {@JoinColumn(name = "sender_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Collection<Tribute> recipients;

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


    public User(String nick, String password, String surname, String name, int height, int weight, boolean sex, District district, Calendar birthday, byte[] picture, Status status) {
        this.nick = nick;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.birthday = birthday;
        this.picture = picture;
        this.cash = 1000;
        this.status = status;
        this.district = district;
        this.lastActivity = new java.sql.Date(System.currentTimeMillis());
    }

    public User(String nick, String password, String surname, String name, byte[] picture, Status status) {
        this.nick = nick;
        this.password = password;this.surname = surname;
        this.name = name;
        this.picture = picture;
        this.status = status;
        this.lastActivity = new java.sql.Date(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(userId, that.userId) &&
                height == that.height &&
                weight == that.weight &&
                sex == that.sex &&
                cash == that.cash &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(status, that.status) &&
                Objects.equals(stewardGames, that.stewardGames) &&
                Objects.equals(presentstotributesByUserid, that.presentstotributesByUserid) &&
                Objects.equals(trainings, that.trainings) &&
                Objects.equals(tributesByUser, that.tributesByUser) &&
                Objects.equals(skills, that.skills) &&
                Objects.equals(district, that.district);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, surname, name, height, weight, sex, birthday, status, cash, stewardGames, presentstotributesByUserid, trainings, tributesByUser, skills, district);
    }


}