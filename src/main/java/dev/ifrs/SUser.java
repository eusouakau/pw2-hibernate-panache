package dev.ifrs;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dev.ifrs.model.User;

@Path("/api")
@Transactional
public class SUser {
    
    @GET
    @Path("/user/create/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(@PathParam("name") String name) {
        User user = new User();
        user.setName(name);
        user.persist();
        return user;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> list() {
        return User.listAll();
    }

    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User list(@PathParam("id") Long id) {
        return User.findById(id);
    }

 
}
