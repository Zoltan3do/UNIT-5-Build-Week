package team_3.BW_CRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_3.BW_CRM.entities.Comune;

@Repository
public interface ComuneRepository extends JpaRepository<Comune,Long> {

}
