package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @Column(name = "userId")
    private Integer userId;

    @NotNull
    @Column(name = "surname", length = 30)
    private String surname;

    @Basic
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
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "statusId")
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
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private Collection<Training> trainings;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<Tribute> tributesByUser;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentstotributes",
            joinColumns = {@JoinColumn(name = "senderid")},
            inverseJoinColumns = {@JoinColumn(name = "tributeid")}
    )
    private Collection<Shop> sendings;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentstotributes",
            joinColumns = {@JoinColumn(name = "senderid")},
            inverseJoinColumns = {@JoinColumn(name = "productid")}
    )
    private Collection<Tribute> recipients;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "userskills",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "skillId")}
    )
    private Collection<Skill> skills;

    @ManyToOne
    @JoinColumn(name = "district", referencedColumnName = "districtId")
    private District district;

    @OneToOne
    @JoinColumn(name = "loginId", referencedColumnName = "loginId")
    private UserLogin userLogin;


    public User(String surname, String name, int height, int weight, boolean sex, District district, Date birthday, byte[] picture, UserLogin userLogin, Status status) {
        this.surname = surname;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.birthday = birthday;
        this.picture = picture;
        this.cash = 1000;
        this.status = status;
        this.userLogin = userLogin;
        this.district = district;
    }

    public User(String surname, String name, byte[] picture, UserLogin userLogin, Status status) {
        this.surname = surname;
        this.name = name;
        this.picture = picture;
        this.userLogin = userLogin;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId == that.userId &&
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
                Objects.equals(district, that.district) &&
                Objects.equals(userLogin, that.userLogin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, surname, name, height, weight, sex, birthday, status, cash, stewardGames, presentstotributesByUserid, trainings, tributesByUser, skills, district, userLogin);
    }


}