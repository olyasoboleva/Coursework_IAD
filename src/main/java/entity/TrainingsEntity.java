package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "trainings", schema = "public", catalog = "postgres")
public class TrainingsEntity {
    private int trainingid;
    private String name;
    private int coefficient;
    private int duration;
    private String description;
    private Time timeoftraining;
    private int dayofweek;
    private SkillsEntity skillsBySkillid;
    private UsersEntity usersByTrainer;

    public TrainingsEntity() {}

    public TrainingsEntity(String name, SkillsEntity skill, int coefficient, int duration, String description, UsersEntity trainer, Time timeoftraining, int dayofweek) {
        this.name = name;
        this.coefficient = coefficient;
        this.duration = duration;
        this.description = description;
        this.timeoftraining = timeoftraining;
        this.dayofweek = dayofweek;
        this.skillsBySkillid = skill;
        this.usersByTrainer = trainer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainingid")
    public int getTrainingid() {
        return trainingid;
    }

    public void setTrainingid(int trainingid) {
        this.trainingid = trainingid;
    }

    @Basic
    @Column(name = "name", length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Min(0)
    @Column(name = "coefficient")
    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    @Basic
    @Min(0)
    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "timeoftraining")
    public Time getTimeoftraining() {
        return timeoftraining;
    }

    public void setTimeoftraining(Time timeoftraining) {
        this.timeoftraining = timeoftraining;
    }

    @Basic
    @Column(name = "dayofweek")
    public int getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(int dayofweek) {
        this.dayofweek = dayofweek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingsEntity that = (TrainingsEntity) o;
        return trainingid == that.trainingid &&
                coefficient == that.coefficient &&
                duration == that.duration &&
                dayofweek == that.dayofweek &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(timeoftraining, that.timeoftraining) &&
                Objects.equals(skillsBySkillid, that.skillsBySkillid) &&
                Objects.equals(usersByTrainer, that.usersByTrainer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(trainingid, name, coefficient, duration, description, timeoftraining, dayofweek, skillsBySkillid, usersByTrainer);
    }

    @ManyToOne
    @JoinColumn(name = "skillid", referencedColumnName = "skillid", insertable = false, updatable = false)
    public SkillsEntity getSkillsBySkillid() {
        return skillsBySkillid;
    }

    public void setSkillsBySkillid(SkillsEntity skillsBySkillid) {
        this.skillsBySkillid = skillsBySkillid;
    }

    @ManyToOne
    @JoinColumn(name = "trainer", referencedColumnName = "userid", insertable = false, updatable = false)
    public UsersEntity getUsersByTrainer() {
        return usersByTrainer;
    }

    public void setUsersByTrainer(UsersEntity usersByTrainer) {
        this.usersByTrainer = usersByTrainer;
    }
}
