package mk.ukim.finki.service;

import mk.ukim.finki.models.User;
import mk.ukim.finki.models.enumeration.Role;

import java.util.Optional;

public interface AuthService {

      User login(String username, String password);
}
