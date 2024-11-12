package team_3.BW_CRM.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "utenti")
@JsonIgnoreProperties({"password","role","enabled","accountNonLocked","credentialsNonExpired","accountNonExpired","authorities"})
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ruoli_utente",
    joinColumns = @JoinColumn(name = "utente_id"),
    inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private List<Ruolo> ruoli = new ArrayList<>();

    public Utente(String username, String email, String password, String nome, String cognome) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatar = "https://ui-avatars.com/api/?name=" + this.nome + "+" + this.cognome;
        this.ruoli.add(new Ruolo("USER"));
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRuoli(List<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> listaGranted = new ArrayList<>();
        for(Ruolo r : ruoli) {
            listaGranted.add(new SimpleGrantedAuthority(r.getTipo()));
        }
        return listaGranted;
    }


}
