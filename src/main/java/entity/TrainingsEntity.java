package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "trainings", schema = "public", catalog = "postgres")
public class TrainingsEntity {
    private int trainingId;
    private String name;
    private int coefficient;
    private int duration;
    private String description;
    //TODO: change type
    private Time timeOfTraining;
    private int dayOfWeek;
    private SkillsEntity skill;
    private UsersEntity trainer;

    public TrainingsEntity() {}

    public TrainingsEntity(String name, SkillsEntity skill, int coefficient, int duration, String description, UsersEntity trainer, Time timeOfTraining, int dayOfWeek) {
        this.name = name;
        this.coefficient = coefficient;
        this.duration = duration;
        this.description = description;
        this.timeOfTraining = timeOfTraining;
        this.dayOfWeek = dayOfWeek;
        this.skill = skill;
        this.trainer = trainer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainingId")
    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
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
    @Column(name = "timeOfTraining")
    public Time getTimeOfTraining() {
        return timeOfTraining;
    }

    public void setTimeOfTraining(Time timeOfTraining) {
        this.timeOfTraining = timeOfTraining;
    }

    @Basic
    @Column(name = "dayOfWeek")
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingsEntity that = (TrainingsEntity) o;
        return trainingId == that.trainingId &&
                coefficient == that.coefficient &&
                duration == that.duration &&
                dayOfWeek == that.dayOfWeek &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(timeOfTraining, that.timeOfTraining) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(trainer, that.trainer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(trainingId, name, coefficient, duration, description, timeOfTraining, dayOfWeek, skill, trainer);
    }

    @ManyToOne
    @JoinColumn(name = "skillId", referencedColumnName = "skillId", insertable = false, updatable = false)
    public SkillsEntity getSkill() {
        return skill;
    }
    public void setSkill(SkillsEntity skill) {
        this.skill = skill;
    }

    @ManyToOne
    @JoinColumn(name = "trainer", referencedColumnName = "userId", insertable = false, updatable = false)
    public UsersEntity getTrainer() {
        return trainer;
    }
    public void setTrainer(UsersEntity trainer) {
        this.trainer = trainer;
    }
}
