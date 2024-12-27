package com.example.systemegestionuniversitaire.Service;

import com.example.systemegestionuniversitaire.Entity.Utilisateur;
import com.example.systemegestionuniversitaire.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user != null) {
            var utilisateur = User.withUsername(user.getEmail())
                    .password(user.getMdp())
                    .build();
            return utilisateur;

        }

        return null;



    }




}
