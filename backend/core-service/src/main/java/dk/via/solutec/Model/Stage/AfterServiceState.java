package dk.via.solutec.Model.Stage;

public class AfterServiceState implements StageState {
    @Override
    public void next(Stage stage) {
        throw new IllegalStateException("Afterservice is the final stage");
    }

    @Override
    public void previous(Stage stage) {
        stage.setState(new ClosureState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.AFTERSERVICE;
    }
}
