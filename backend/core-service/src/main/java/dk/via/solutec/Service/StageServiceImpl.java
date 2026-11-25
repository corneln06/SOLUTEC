package dk.via.solutec.Service;

import dk.via.solutec.Model.Stage.Stage;
import dk.via.solutec.Repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService{

    private final StageRepository stageRepository;

    @Override
    public Stage getStageById(Long id) {
        return stageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stage not found"));
    }

    @Override
    public Stage nextStage(Long id) {
        Stage stage = getStageById(id);
        try {
            stage.next();
        }
        catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return stageRepository.save(stage);
    }

    @Override
    public Stage previousStage(Long id) {
        Stage stage = getStageById(id);
        try {
            stage.previous();
        }
        catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return stageRepository.save(stage);
    }
}
