package mk.ukim.finki.service.impl;

import mk.ukim.finki.models.User;
import mk.ukim.finki.models.enumeration.Role;
import mk.ukim.finki.models.exeptions.InvalidArgumentException;
import mk.ukim.finki.models.exeptions.PasswordDoNotMatchException;
import mk.ukim.finki.models.exeptions.UsernameAlreadyExistsException;
import mk.ukim.finki.repository.jpa.UserRepository;
import mk.ukim.finki.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }



    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordDoNotMatchException();
        }
        if (this.userRepository.findUserByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username,passwordEncoder.encode(password),name,surname,role);
        return userRepository.save(user);

    }

}
