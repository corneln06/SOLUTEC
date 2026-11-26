package dk.via.solutec.Model.Project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;// you can add more variables if needed this is just what i need to implement the state pattern

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private StageStatus stage = StageStatus.PLANNING_AND_BUDGET;

    @Setter(AccessLevel.NONE)
    @Transient
    @JsonIgnore
    private ProjectState state = new PlanningAndBudgetState();

    @PostLoad
    private void restoreState()
    {
        this.state = stage.toState();
    }

    public void setState(ProjectState state)
    {
        this.state = state;
        this.stage = state.getStatus();
    }

    public void nextStage()
    {
        state.next(this);
    }

    public void previousStage()
    {
        state.previous(this);
    }
}
