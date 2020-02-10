package xyz.codingdaddy.resources;

import xyz.codingdaddy.domain.User;
import xyz.codingdaddy.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * Serves the requests for user service
 *
 * @author serhiy
 */
@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UserResource {

    @Inject
    private UserRepository userRepository;

    @GET
    @Path("/{username}")
    public Response find(@PathParam("username") String username) {
        return userRepository.findByUsername(username)
                .map(Response::ok)
                .orElseGet(() -> Response.status(NOT_FOUND))
                .build();
    }

    @GET
    public Response findAll() {
        return Response.ok(userRepository.findAll()).build();
    }

    @POST
    public Response create(User user) {
        return Response.ok(userRepository.create(user)).build();
    }

    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam("username") String username) {
        return Response.status(userRepository.delete(username) ? OK : NOT_FOUND).build();
    }
}
