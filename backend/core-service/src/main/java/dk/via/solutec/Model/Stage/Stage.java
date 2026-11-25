package dk.via.solutec.Model.Stage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stages")
@Getter
@NoArgsConstructor

public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StageStatus status = StageStatus.PLANNING_AND_BUDGET;

    @Transient
    private StageState state = new PlanningAndBudgetState();

    @PostLoad
    private void restoreState()
    {
        this.state = status.toState();
    }

    void setState(StageState state) {
        this.state = state;
        this.status = state.getStatus();
    }

    public void next()
    {
        state.next(this);
    }

    public void previous()
    {
        state.previous(this);
    }
}
