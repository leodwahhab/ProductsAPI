package com.example.cursoSpring.service;

import com.example.cursoSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//implementar a interface UserDetailsService faz com o Security identifique esta como a classe de Authorization Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    //define como o Security vai verificar as credenciais do usuario que está tentando acessar
    //neste caso, vamos consultar o usuario no banco
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
}
