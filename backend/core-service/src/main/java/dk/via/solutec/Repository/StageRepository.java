package dk.via.solutec.Repository;


import dk.via.solutec.Model.Stage.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
}
