package xyz.codingdaddy.repository;

import xyz.codingdaddy.domain.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory user repository
 *
 * @author serhiy
 */
@ApplicationScoped
public class UserRepository {
    private final AtomicLong id = new AtomicLong(0L);

    private final List<User> users = new CopyOnWriteArrayList<User>() {{
        add(create("admin", "admin", "admin@example.com"));
        add(create("user", "user", "user@example.com"));
    }};

    public User create(User user) {
        user.setId(getNextUserId());
        users.add(user);
        return user;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public List<User> findAll() {
        return users;
    }

    public boolean delete(String username) {
        return findByUsername(username).map(u -> users.remove(u)).orElse(false);
    }

    private User create(String username, String password, String email) {
        User user = new User();
        user.setId(getNextUserId());
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    private long getNextUserId() {
        return id.getAndIncrement();
    }
}
