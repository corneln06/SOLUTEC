package dk.via.solutec.Model.Project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dk.via.solutec.Model.Location;
import dk.via.solutec.Model.ProjectItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "location_name")
    private Location location;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date projectStart;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date projectDeadline;

    private Boolean isAfterSale;
    // private Long paymentPlanId; // to be integrated when Marcus create the PaymentPlan class

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<ProjectItem> items = new ArrayList<>();

    // to be integrated when Marcus create the PaymentPlan class
//    @ManyToOne
//    @JoinColumn(name = "payment_plan_id")
//    private PaymentPlan paymentPlan;

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
