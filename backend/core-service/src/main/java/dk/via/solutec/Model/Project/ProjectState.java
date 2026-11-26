package dk.via.solutec.Model.Project;

public interface ProjectState {

    void next(Project project);
    void previous(Project project); // if there's no need of going back to a previous state we can delete this
    StageStatus getStatus();

}
