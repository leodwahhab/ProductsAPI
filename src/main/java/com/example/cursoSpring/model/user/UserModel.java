package com.example.cursoSpring.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
//a classe User deve implementar a UserDetails
//UserDetails é usada para identificar uma classe que represente um usuário na aplicação
public class UserModel implements UserDetails {
    private String id;
    private String login;
    private String password;
    private UserRole role;

    //O metodo getAuthorities retorna como coleção as roles que o usuário possui de acordo com seu nivel de acesso
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //verifica a role do usuário e retorna os acessos que ele possui.
        //aqui vamos utilizar as roles do Spring Security
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));

    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
