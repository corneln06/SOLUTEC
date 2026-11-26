package dk.via.solutec.Model.Project;



public class InstallState implements ProjectState {

    @Override
    public void next(Project project) {
        project.setState(new ClosureState());
    }

    @Override
    public void previous(Project project) {
        project.setState(new BuildingState());
    }

    @Override
    public StageStatus getStatus() {
        return StageStatus.INSTALL;
    }
}
