package dk.via.solutec.Model.Project;



public class ClosureState implements ProjectState {
    @Override
    public void next(Project project) {
        project.setState(new AfterServiceState());
    }

    @Override
    public void previous(Project project) {
        project.setState(new InstallState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.CLOSURE;
    }
}
