package mk.ukim.finki.service.impl;

import mk.ukim.finki.models.User;
import mk.ukim.finki.models.enumeration.Role;
import mk.ukim.finki.models.exeptions.InvalidArgumentException;
import mk.ukim.finki.models.exeptions.InvalidUserCredentialsException;
import mk.ukim.finki.models.exeptions.PasswordDoNotMatchException;
import mk.ukim.finki.models.exeptions.UsernameAlreadyExistsException;
import mk.ukim.finki.repository.impl.InMemoryUserRepository;
import mk.ukim.finki.repository.jpa.UserRepository;
import mk.ukim.finki.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return userRepository.findUserByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }


}
