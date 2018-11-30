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
    private int userid;
    private String surname;
    private String name;
    private int height;
    private int weight;
    private boolean sex;
    private Date birthday;
    private StatusesEntity status;
    private int cash;
    private String picturePath;
    private Collection<GamesEntity> gamesByUserid;
    private Collection<PresentstotributesEntity> presentstotributesByUserid;
    private Collection<TrainingsEntity> trainingsByUserid;
    private Collection<TributesEntity> tributesByUserid;

    private Collection<SkillsEntity> skills;
    private DistrictsEntity districtsByDistrict;
    private UserloginEntity userlogin;

    public UsersEntity() {}


    public UsersEntity(String surname, String name, int height, int weight, boolean sex, DistrictsEntity district, Date birthday, String picturePath, UserloginEntity userlogin) {
        this.surname = surname;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.birthday = birthday;
        this.picturePath = picturePath;
        this.cash = 1000;
        this.status = status;
        this.userlogin = userlogin;
        this.districtsByDistrict = district;
    }

    public UsersEntity(String surname, String name, String picturePath, UserloginEntity userlogin, StatusesEntity status) {
        this.surname = surname;
        this.name = name;
        this.picturePath = picturePath;
        this.userlogin = userlogin;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
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
        return userid == that.userid &&
                height == that.height &&
                weight == that.weight &&
                sex == that.sex &&
                cash == that.cash &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(status, that.status) &&
                Objects.equals(picturePath, that.picturePath) &&
                Objects.equals(gamesByUserid, that.gamesByUserid) &&
                Objects.equals(presentstotributesByUserid, that.presentstotributesByUserid) &&
                Objects.equals(trainingsByUserid, that.trainingsByUserid) &&
                Objects.equals(tributesByUserid, that.tributesByUserid) &&
                Objects.equals(skills, that.skills) &&
                Objects.equals(districtsByDistrict, that.districtsByDistrict) &&
                Objects.equals(userlogin, that.userlogin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userid, surname, name, height, weight, sex, birthday, status, cash, picturePath, gamesByUserid, presentstotributesByUserid, trainingsByUserid, tributesByUserid, skills, districtsByDistrict, userlogin);
    }

    @OneToMany(mappedBy = "usersBySteward", fetch = FetchType.LAZY)
    public Collection<GamesEntity> getGamesByUserid() {
        return gamesByUserid;
    }
    public void setGamesByUserid(Collection<GamesEntity> gamesByUserid) {
        this.gamesByUserid = gamesByUserid;
    }

    @OneToMany(mappedBy = "usersBySenderid", fetch = FetchType.LAZY)
    public Collection<PresentstotributesEntity> getPresentstotributesByUserid() {
        return presentstotributesByUserid;
    }
    public void setPresentstotributesByUserid(Collection<PresentstotributesEntity> presentstotributesByUserid) {
        this.presentstotributesByUserid = presentstotributesByUserid;
    }

    @OneToMany(mappedBy = "usersByTrainer", fetch = FetchType.LAZY)
    public Collection<TrainingsEntity> getTrainingsByUserid() {
        return trainingsByUserid;
    }
    public void setTrainingsByUserid(Collection<TrainingsEntity> trainingsByUserid) {
        this.trainingsByUserid = trainingsByUserid;
    }

    @OneToMany(mappedBy = "usersByUserid", fetch = FetchType.LAZY)
    public Collection<TributesEntity> getTributesByUserid() {
        return tributesByUserid;
    }
    public void setTributesByUserid(Collection<TributesEntity> tributesByUserid) {
        this.tributesByUserid = tributesByUserid;
    }

    @ManyToOne
    @JoinColumn(name = "district", referencedColumnName = "districtid", insertable = false, updatable = false)
    public DistrictsEntity getDistrictsByDistrict() {
        return districtsByDistrict;
    }
    public void setDistrictsByDistrict(DistrictsEntity districtsByDistrict) {
        this.districtsByDistrict = districtsByDistrict;
    }

    @ManyToOne
    @JoinColumn(name = "statusid", referencedColumnName = "statusid", insertable = false, updatable = false)
    public StatusesEntity getStatus() {
        return status;
    }
    public void setStatus(StatusesEntity status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name = "loginid", referencedColumnName = "loginid")
    public UserloginEntity getUserlogin() {
        return userlogin;
    }
    public void setUserlogin(UserloginEntity userlogin) {
        this.userlogin = userlogin;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "userskills",
            joinColumns = {@JoinColumn(name = "userid")},
            inverseJoinColumns = {@JoinColumn(name = "skillid")}
    )
    public Collection<SkillsEntity> getSkills() {
        return skills;
    }
    public void setSkills(Collection<SkillsEntity> skills) {
        this.skills = skills;
    }
}
