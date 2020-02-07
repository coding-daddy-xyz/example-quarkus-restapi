package xyz.codingdaddy.service;

import xyz.codingdaddy.domain.User;
import xyz.codingdaddy.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {

    @Inject
    private UserRepository userRepository;

    @GET
    public Optional<User> getUser(String username) {
        return userRepository.findUser(username);
    }
}
