package dk.via.solutec.Model.Stage;

public class BuildingState implements StageState{
    @Override
    public void next(Stage stage) {

        stage.setState(new InstallState());
    }

    @Override
    public void previous(Stage stage) {
        stage.setState(new PlanningAndBudgetState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.BUILDING;
    }
}
