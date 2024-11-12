package team_3.BW_CRM.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_3.BW_CRM.entities.Cliente;
import team_3.BW_CRM.entities.Fattura;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Page<Cliente> findById(Long id, Pageable pageable);
}
