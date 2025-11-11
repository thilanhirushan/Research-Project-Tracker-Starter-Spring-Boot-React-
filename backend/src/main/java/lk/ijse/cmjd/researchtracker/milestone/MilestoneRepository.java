package lk.ijse.cmjd.researchtracker.milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MilestoneRepository extends JpaRepository<Milestone, String> {
    List<Milestone> findByProject_Id(String projectId);
}
