package sk.tmconsulting.eshoptricka.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.tmconsulting.eshoptricka.model.Pouzivatel;
import sk.tmconsulting.eshoptricka.repository.PouzivatelRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private PouzivatelRepository pouzivatelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pouzivatel pouzivatelObjekt = pouzivatelRepository.findByUsername(username);
        if (pouzivatelObjekt == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(pouzivatelObjekt.getUsername()).password(pouzivatelObjekt.getHeslo()).authorities("USER").build(); // heslo je siftovane Bcrypt, https://bcrypt-generator.com/
        // napr. slovo heslo123 reprezentuje $2a$12$hM8V5ss.KaaT5PtP8vScSeIQ5GwEvwNteuX1vxigBPTgIcVo88IuC
        return user;
    }
}
