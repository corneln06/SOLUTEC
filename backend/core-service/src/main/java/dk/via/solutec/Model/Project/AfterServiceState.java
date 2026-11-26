package dk.via.solutec.Model.Project;

public class AfterServiceState implements ProjectState {
    @Override
    public void next(Project project) {
        throw new IllegalStateException("Afterservice is the final stage");
    }

    @Override
    public void previous(Project project) {
        project.setState(new ClosureState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.AFTERSERVICE;
    }
}
