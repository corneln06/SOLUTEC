package dk.via.solutec.Controller;


import dk.via.solutec.Model.Stage.Stage;
import dk.via.solutec.Service.StageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/stages")
@RequiredArgsConstructor
@Tag(name = "Stage management", description = "Api for viewing and moving project stages")
public class StageController {

    private final StageService stageService;

    @Operation(summary = "Get stage", description = "Get a stage and its current status")
    @GetMapping("/{id}")
    public Stage getStage(@PathVariable Long id)
    {
        return stageService.getStageById(id);
    }

    @Operation(summary = "Move to next stage", description = "Moves the stage one step forward")
    @PatchMapping("/{id}/next")
    public Stage nextStage(@PathVariable Long id) {
        return stageService.nextStage(id);
    }

    @Operation(summary = "Move to previous stage", description = "Moves the stage one step back") // can be deleted if theres no need
    @PatchMapping("/{id}/previous")
    public Stage previousStage(@PathVariable Long id) {
        return stageService.previousStage(id);
    }


}
