package dk.via.solutec.Model.Stage;

public class InstallState implements StageState {

    @Override
    public void next(Stage stage) {
        stage.setState(new ClosureState());
    }

    @Override
    public void previous(Stage stage) {
        stage.setState(new BuildingState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.INSTALL;
    }
}
