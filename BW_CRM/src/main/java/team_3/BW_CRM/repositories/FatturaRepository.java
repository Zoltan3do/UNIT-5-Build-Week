package team_3.BW_CRM.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_3.BW_CRM.entities.Fattura;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura,Long> {

    Optional <Fattura> findByNumero(Integer numero);
    Page<Fattura> findByFatturaData(LocalDate data, Pageable pageable);
    Page<Fattura> findByClienteId(Long id, Pageable pageable);
    Page<Fattura> findByImportoBetween(Double minImporto, Double maxImporto, Pageable pageable);
    Page<Fattura> findByFatturaDataYear(Integer year, Pageable pageable);
    boolean existsByNumero(Integer numero);

}
