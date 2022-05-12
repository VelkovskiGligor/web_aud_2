package mk.ukim.finki.repository.impl;

import mk.ukim.finki.bootstrap.DataHolder;
import mk.ukim.finki.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository {

    public Optional<User> findByUserName(String username) {
        return DataHolder.usersList.stream().filter(r -> r.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findUserByNameAndPassword(String username, String password) {
        return DataHolder.usersList.stream().filter(r -> r.getUsername().equals(username) && r.getPassword().equals(password)).findFirst();
    }

    public User saveOrUpdate(User user) {
        DataHolder.usersList.removeIf(r -> r.getUsername().equals(user.getUsername()));
        DataHolder.usersList.add(user);
        return user;
    }
    public void delete(String username) {
        DataHolder.usersList.removeIf(r -> r.getUsername().equals(username));

    }
}
