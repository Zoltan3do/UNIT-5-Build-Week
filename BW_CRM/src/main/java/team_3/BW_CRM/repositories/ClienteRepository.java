package team_3.BW_CRM.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_3.BW_CRM.entities.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> clienti = new ArrayList<>();

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByPartitaIva(String partitaIva);

    Optional<Cliente> findByRagioneSociale(String ragioneSociale);


    default Page<Cliente> findClienti(Double fatturatoMinimo,
                                      LocalDate dataInserimento,
                                      LocalDate dataUltimoContatto,
                                      String parteDelNome,
                                      String nomeContatto,
                                      String cognomeContatto,
                                      Pageable pageable
    ) {
        List<Cliente> filtrati =
                clienti.stream()
                        .filter(cliente -> {
                            boolean include = (fatturatoMinimo == null || cliente.getFatturatoAnnuale() >= fatturatoMinimo);
                            System.out.println("Filtro fatturato: " + include);
                            return include;
                        })
                        .filter(cliente -> {
                            boolean include = (dataInserimento == null || cliente.getDataInserimento().equals(dataInserimento));
                            System.out.println("Filtro dataInserimento: " + include);
                            return include;
                        })
                        .filter(cliente -> {
                            boolean include = (dataUltimoContatto == null || cliente.getDataUltimoContatto().equals(dataUltimoContatto));
                            System.out.println("Filtro dataUltimoContatto: " + include);
                            return include;
                        })
                        .filter(cliente -> {
                            boolean include = (parteDelNome == null || cliente.getRagioneSociale().toLowerCase().contains(parteDelNome.toLowerCase()));
                            System.out.println("Filtro parteDelNome: " + include);
                            return include;
                        })
                        .filter(cliente -> {
                            boolean include = (nomeContatto == null || cliente.getNomeContatto().toLowerCase().contains(nomeContatto.toLowerCase()));
                            System.out.println("Filtro nomeContatto: " + include);
                            return include;
                        })
                        .filter(cliente -> {
                            boolean include = (cognomeContatto == null || cliente.getCognomeContatto().toLowerCase().contains(cognomeContatto.toLowerCase()));
                            System.out.println("Filtro cognomeContatto: " + include);
                            return include;
                        })
                        .collect(Collectors.toList());
        System.out.println("Clienti filtrati: " + filtrati.size());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filtrati.size());
        return new PageImpl<>(filtrati.subList(start, end), pageable, filtrati.size());


    }

}
