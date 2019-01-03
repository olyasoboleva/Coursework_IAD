package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "training", schema = "public", catalog = "postgres")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private Integer trainingId;

    @Column(name = "name", length = 40)
    private String name;

    @Min(0)
    @Column(name = "coefficient")
    private int coefficient;

    @Min(0)
    @Column(name = "duration")
    private int duration;

    @Column(name = "description")
    private String description;

    @Column(name = "time_of_training")
    private LocalTime timeOfTraining;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "trainer", referencedColumnName = "user_id")
    private User trainer;

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
        return Objects.equals(trainingId, that.trainingId) &&
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
