package dk.via.solutec.Model.Stage;

public interface StageState {

    void next(Stage stage);
    void previous(Stage stage); // if there's no need of going back to a previous state we can delete this
    StageStatus getStatus();

}
