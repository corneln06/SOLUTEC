package dk.via.solutec.Model.Project;


public class BuildingState implements ProjectState {
    @Override
    public void next(Project project) {

        project.setState(new InstallState());
    }

    @Override
    public void previous(Project project) {
        project.setState(new PlanningAndBudgetState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.BUILDING;
    }
}
