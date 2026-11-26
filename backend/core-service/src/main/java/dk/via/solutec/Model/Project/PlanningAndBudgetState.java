package dk.via.solutec.Model.Project;


public class PlanningAndBudgetState implements ProjectState {
    @Override
    public void next(Project project) {
        project.setState(new BuildingState());
    }

    @Override
    public void previous(Project project) {
        throw new IllegalStateException("Planning is the first stage");
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.PLANNING_AND_BUDGET;
    }
}
