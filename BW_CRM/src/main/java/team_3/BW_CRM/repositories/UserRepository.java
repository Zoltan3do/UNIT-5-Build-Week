package team_3.BW_CRM.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team_3.BW_CRM.entities.Utente;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);
}
