package team_3.BW_CRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team_3.BW_CRM.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
