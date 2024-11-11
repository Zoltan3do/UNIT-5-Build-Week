package team_3.BW_CRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import team_3.BW_CRM.entities.Utente;
import team_3.BW_CRM.exceptions.BadRequestException;
import team_3.BW_CRM.exceptions.NotFoundException;
import team_3.BW_CRM.payloads.UtenteDTO;
import team_3.BW_CRM.repositories.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun utente trovato!"));
    }

    public Utente save(UtenteDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );

        Utente newUser = new Utente(body.username(), body.email(), bcrypt.encode(body.password()), body.nome(), body.cognome());
        Utente savedUser = this.userRepository.save(newUser);

        // 4. Invio email di benvenuto
        // mailgunSender.sendRegistrationEmail(savedUser);

        return savedUser;
    }

}
