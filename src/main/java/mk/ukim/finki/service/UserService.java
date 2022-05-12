package mk.ukim.finki.service;

import mk.ukim.finki.models.User;
import mk.ukim.finki.models.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;



     User register(String username, String password, String repeatPassword, String name, String surname, Role role);

}
