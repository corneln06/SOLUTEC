package dk.via.solutec.Model.Project;

public enum StageStatus {
    PLANNING_AND_BUDGET,
    BUILDING,
    INSTALL,
    CLOSURE,
    AFTERSERVICE;

    public ProjectState toState()
    {
       return switch (this) {
            case PLANNING_AND_BUDGET -> new PlanningAndBudgetState();
            case BUILDING -> new BuildingState();
            case INSTALL -> new InstallState();
            case CLOSURE -> new ClosureState();
            case AFTERSERVICE -> new AfterServiceState();
        };
    }
}
