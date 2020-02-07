package xyz.codingdaddy.repository;

import xyz.codingdaddy.domain.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(createUser("admin", "admin", "admin@example.com"));
        users.add(createUser("user", "user", "user@example.com"));
    }

    public Optional<User> findUser(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    private User createUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
