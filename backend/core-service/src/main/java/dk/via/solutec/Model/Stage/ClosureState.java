package dk.via.solutec.Model.Stage;

public class ClosureState implements StageState{
    @Override
    public void next(Stage stage) {
        stage.setState(new AfterServiceState());
    }

    @Override
    public void previous(Stage stage) {
        stage.setState(new InstallState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.CLOSURE;
    }
}
