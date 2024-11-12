package team_3.BW_CRM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team_3.BW_CRM.entities.Utente;
import team_3.BW_CRM.payloads.UtenteDTO;
import team_3.BW_CRM.services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUserById(@PathVariable Long id) {
        Utente user = userService.findById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    public ResponseEntity<Utente> registerUser(@RequestBody UtenteDTO userDTO) {
        Utente createdUser = userService.save(userDTO);
        return ResponseEntity.created(URI.create("/api/utenti/" + createdUser.getId())).body(createdUser);
    }


    @PutMapping("/{id}/assign-role")
    public ResponseEntity<Void> assignRoleToUser(@PathVariable Long id, @RequestParam String roleType) {
        userService.assignRoleToUser(id, roleType);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-role")
    public ResponseEntity<List<Utente>> getUsersByRole(@RequestParam String tipoRuolo) {
        List<Utente> users = userService.findUtenteByRuolo(tipoRuolo);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Utente> updateUser(@PathVariable Long id, @RequestBody UtenteDTO userDTO) {
        Utente updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}