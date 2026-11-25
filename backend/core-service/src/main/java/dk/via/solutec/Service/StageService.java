package dk.via.solutec.Service;

import dk.via.solutec.Model.Stage.Stage;

public interface StageService {

    Stage getStageById(Long id);
    Stage nextStage(Long id);
    Stage previousStage(Long id);
}
