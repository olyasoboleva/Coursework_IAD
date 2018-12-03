package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "trainings", schema = "public", catalog = "postgres")
public class Training {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainingId")
    private int trainingId;

    @Getter
    @Setter
    @Basic
    @Column(name = "name", length = 40)
    private String name;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Column(name = "coefficient")
    private int coefficient;

    @Getter
    @Setter
    @Basic
    @Min(0)
    @Column(name = "duration")
    private int duration;

    @Getter
    @Setter
    @Basic
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Basic
    @Column(name = "timeOfTraining")
    private LocalTime timeOfTraining;

    @Getter
    @Setter
    @Basic
    @Column(name = "dayOfWeek")
    private int dayOfWeek;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "skillId", referencedColumnName = "skillId")
    private Skill skill;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "trainer", referencedColumnName = "userId")
    private User trainer;

    public Training() {}

    public Training(String name, Skill skill, int coefficient, int duration, String description, User trainer, LocalTime timeOfTraining, int dayOfWeek) {
        this.name = name;
        this.coefficient = coefficient;
        this.duration = duration;
        this.description = description;
        this.timeOfTraining = timeOfTraining;
        this.dayOfWeek = dayOfWeek;
        this.skill = skill;
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training that = (Training) o;
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
}
