package com.letv.app.sample.rest;

import com.letv.javax.ws.rs.POST;
import com.letv.javax.ws.rs.Path;
import com.letv.javax.ws.rs.PathParam;
import com.letv.javax.ws.rs.QueryParam;

@Path("/user")
public interface IUserResource {
  @POST
  @Path("/login")
  void login(@QueryParam("username") String userName,@QueryParam("password") String password);
}
