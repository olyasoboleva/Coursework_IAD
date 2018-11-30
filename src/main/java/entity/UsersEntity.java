package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {
    private int userid;
    private String surname;
    private String name;
    private Short height;
    private Short weight;
    private boolean sex;
    private Date birthday;
    private String status;
    private Integer cash;
    private String picturePath;
    private Collection<GamesEntity> gamesByUserid;
    private Collection<PresentstotributesEntity> presentstotributesByUserid;
    private Collection<TrainingsEntity> trainingsByUserid;
    private Collection<TributesEntity> tributesByUserid;

    private Collection<SkillsEntity> skills;
    private DistrictsEntity districtsByDistrict;
    private UserloginEntity userlogin;

    public UsersEntity() {}

    public UsersEntity(String surname, String name, Short height, Short weight, boolean sex, DistrictsEntity district, Date birthday, String picturePath, UserloginEntity userlogin) {
        this.surname = surname;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
        this.birthday = birthday;
        this.picturePath = picturePath;
        this.cash = 1000;
        this.status = "Наблюдатель";
        this.userlogin = userlogin;
        this.districtsByDistrict = district;
    }

    public UsersEntity(String surname, String name, String picturePath, UserloginEntity userlogin) {
        this.surname = surname;
        this.name = name;
        this.picturePath = picturePath;
        this.userlogin = userlogin;
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
    public Short getHeight() {
        return height;
    }
    public void setHeight(Short height) {
        this.height = height;
    }

    @Basic
    @Min(0)
    @Column(name = "weight")
    public Short getWeight() {
        return weight;
    }
    public void setWeight(Short weight) {
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
    @Column(name = "status", length = 40)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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

        if (userid != that.userid) return false;
        if (sex != that.sex) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (cash != null ? !cash.equals(that.cash) : that.cash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (sex ? 1 : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        return result;
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
