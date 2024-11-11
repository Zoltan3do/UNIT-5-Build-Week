package team_3.BW_CRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import team_3.BW_CRM.entities.Utente;
import team_3.BW_CRM.exceptions.NotFoundException;
import team_3.BW_CRM.repositories.UserRepository;

public class UserService {

    @Autowired
   private UserRepository userRepository;

    public Utente findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun utente trovato!"));
    }

}
