package dk.via.solutec.Model.Stage;

public class PlanningAndBudgetState implements StageState {
    @Override
    public void next(Stage stage) {
        stage.setState(new BuildingState());
    }

    @Override
    public void previous(Stage stage) {
        throw new IllegalStateException("Planning is the first stage");
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.PLANNING_AND_BUDGET;
    }
}
