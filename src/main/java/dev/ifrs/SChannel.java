package dev.ifrs;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.ifrs.model.Channel;
import dev.ifrs.model.User;

@Path("/api")
@Transactional
public class SChannel {


    @GET
    @Path("/channel/create/{hash}")
    @Produces(MediaType.APPLICATION_JSON)
    public Channel createChannel(@PathParam("hash") String hash) {
        Channel channel = new Channel();
        channel.setHash(hash);
        channel.persist();
        return channel;
    }

    @GET
   @Path("/add/{idChannel}/{userId}")
   @Produces(MediaType.APPLICATION_JSON)
   public User add(@PathParam("idChannel") Long idChannel, @PathParam("userId") Long userId) {

      Channel channel = Channel.findById(idChannel);
      if (channel == null)
         throw new BadRequestException("Channel not found");

      User user = User.findById(userId);
      if (user == null)
         throw new BadRequestException("User not found");

      channel.addUser(user);
      user.addChannel(channel);

      user.persist();

      return user;
   }

   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Channel> list() {
      return Channel.listAll();
   }

}
