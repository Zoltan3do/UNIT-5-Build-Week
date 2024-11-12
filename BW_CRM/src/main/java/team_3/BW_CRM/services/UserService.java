package team_3.BW_CRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team_3.BW_CRM.entities.Ruolo;
import team_3.BW_CRM.entities.Utente;
import team_3.BW_CRM.exceptions.BadRequestException;
import team_3.BW_CRM.exceptions.NotFoundException;
import team_3.BW_CRM.payloads.UtenteDTO;
import team_3.BW_CRM.repositories.RuoloRepository;
import team_3.BW_CRM.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private RuoloRepository ruoloRepository;


    public Utente findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nessun utente trovato con ID: " + id));
    }
    public Utente findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Nessun utente registrato con questa email"));
    }

    public List<Utente> findAllUsers() {
        return this.userRepository.findAll();
    }


    public Utente save(UtenteDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );

        Utente newUser = new Utente(body.username(), body.email(), bcrypt.encode(body.password()), body.nome(), body.cognome());
        return this.userRepository.save(newUser);
    }


    public void assignRoleToUser(Long userId, String tipoRuolo) {
        Utente utente = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Nessun utente trovato con ID: " + userId));

        Ruolo ruolo = ruoloRepository.findByTipo(tipoRuolo)
                .orElseThrow(() -> new NotFoundException("Nessun ruolo trovato con tipo: " + tipoRuolo));


        if (utente.getRuoli().contains(ruolo)) {
            throw new BadRequestException("L'utente ha già il ruolo specificato: " + tipoRuolo);
        }

        utente.getRuoli().add(ruolo);
        userRepository.save(utente);
    }


    public Utente updateUser(Long id, UtenteDTO userDTO) {
        Utente utente = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));


        utente.setUsername(userDTO.username());
        utente.setEmail(userDTO.email());
        utente.setNome(userDTO.nome());
        utente.setCognome(userDTO.cognome());

        // Non aggiorna la password a meno che non venga fornita
        if (userDTO.password() != null && !userDTO.password().isEmpty()) {
            utente.setPassword(bcrypt.encode(userDTO.password()));
        }

        return userRepository.save(utente);
    }
    public List<Utente> findUtenteByRuolo(String ruoloTipo) {
        Ruolo ruolo = ruoloRepository.findByTipo(ruoloTipo)
                .orElseThrow(() -> new NotFoundException("Ruolo non trovato con tipo: " + ruoloTipo));

        return userRepository.findByRuoli(ruolo);
    }

    public void deleteUser(Long id) {
        Utente utente = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + id));

        userRepository.delete(utente);
    }
}
