package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {
    private int userId;
    private String surname;
    private String name;
    private int height;
    private int weight;
    private boolean sex;
    private Date birthday;
    private StatusesEntity status;
    private int cash;
    private String picturePath;
    private Collection<GamesEntity> stewardGames;
    private Collection<PresentsToTributesEntity> presentstotributesByUserid;
    private Collection<TrainingsEntity> trainings;
    private Collection<TributesEntity> tributesByUser;
    private Collection<ShopEntity> sendings;
    private Collection<TributesEntity> recipients;

    private Collection<SkillsEntity> skills;
    private DistrictsEntity district;
    private UserLoginEntity userLogin;

    public UsersEntity() {}


    public UsersEntity(String surname, String name, int height, int weight, boolean sex, DistrictsEntity district, Date birthday, String picturePath, UserLoginEntity userLogin, StatusesEntity status) {
        this.surname = surname;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.birthday = birthday;
        this.picturePath = picturePath;
        this.cash = 1000;
        this.status = status;
        this.userLogin = userLogin;
        this.district = district;
    }

    public UsersEntity(String surname, String name, String picturePath, UserLoginEntity userLogin, StatusesEntity status) {
        this.surname = surname;
        this.name = name;
        this.picturePath = picturePath;
        this.userLogin = userLogin;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @NotNull
    @Column(name = "surname", length = 30)
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @NotNull
    @Column(name = "name", length = 30)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Min(0)
    @Column(name = "height")
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    @Basic
    @Min(0)
    @Column(name = "weight")
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @NotNull
    @Column(name = "sex")
    public boolean isSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Min(0)
    @Column(name = "cash")
    public Integer getCash() {
        return cash;
    }
    public void setCash(Integer cash) {
        this.cash = cash;
    }

    @Basic
    @Column(name = "picturePath", nullable = false)
    public String getPicturePath() {
        return picturePath;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return userId == that.userId &&
                height == that.height &&
                weight == that.weight &&
                sex == that.sex &&
                cash == that.cash &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(status, that.status) &&
                Objects.equals(picturePath, that.picturePath) &&
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

        return Objects.hash(userId, surname, name, height, weight, sex, birthday, status, cash, picturePath, stewardGames, presentstotributesByUserid, trainings, tributesByUser, skills, district, userLogin);
    }

    @OneToMany(mappedBy = "steward", fetch = FetchType.LAZY)
    public Collection<GamesEntity> getStewardGames() {
        return stewardGames;
    }
    public void setStewardGames(Collection<GamesEntity> stewardGames) {
        this.stewardGames = stewardGames;
    }

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    public Collection<PresentsToTributesEntity> getPresentstotributesByUserid() {
        return presentstotributesByUserid;
    }
    public void setPresentstotributesByUserid(Collection<PresentsToTributesEntity> presentstotributesByUserid) {
        this.presentstotributesByUserid = presentstotributesByUserid;
    }

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    public Collection<TrainingsEntity> getTrainings() {
        return trainings;
    }
    public void setTrainings(Collection<TrainingsEntity> trainings) {
        this.trainings = trainings;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Collection<TributesEntity> getTributesByUser() {
        return tributesByUser;
    }
    public void setTributesByUser(Collection<TributesEntity> tributesByUser) {
        this.tributesByUser = tributesByUser;
    }

    @ManyToOne
    @JoinColumn(name = "district", referencedColumnName = "districtId")
    public DistrictsEntity getDistrict() {
        return district;
    }
    public void setDistrict(DistrictsEntity district) {
        this.district = district;
    }

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "statusId")
    public StatusesEntity getStatus() {
        return status;
    }
    public void setStatus(StatusesEntity status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name = "loginId", referencedColumnName = "loginId")
    public UserLoginEntity getUserLogin() {
        return userLogin;
    }
    public void setUserLogin(UserLoginEntity userLogin) {
        this.userLogin = userLogin;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "userskills",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "skillId")}
    )
    public Collection<SkillsEntity> getSkills() {
        return skills;
    }
    public void setSkills(Collection<SkillsEntity> skills) {
        this.skills = skills;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentstotributes",
            joinColumns = {@JoinColumn(name = "senderid")},
            inverseJoinColumns = {@JoinColumn(name = "tributeid")}
    )
    public Collection<ShopEntity> getSendings() {
        return sendings;
    }

    public void setSendings(Collection<ShopEntity> sendings) {
        this.sendings = sendings;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "presentstotributes",
            joinColumns = {@JoinColumn(name = "senderid")},
            inverseJoinColumns = {@JoinColumn(name = "productid")}
    )
    public Collection<TributesEntity> getRecipients() {
        return recipients;
    }

    public void setRecipients(Collection<TributesEntity> recipients) {
        this.recipients = recipients;
    }
}