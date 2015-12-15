package com.movieticketing.resources;

import com.movieticketing.bo.LoginBO;
import com.movieticketing.model.Login;
import com.movieticketing.model.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nagal_000 on 12/2/2015.
 */
@Component
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)

public class LoginResource {

    @Autowired
    LoginBO loginBo;

    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateLogin(Login login) {
        System.out.println("Login: " + login);
        ResultBean result = loginBo.validateLogin(login);
        return Response.status(result.getStatus()).entity(result).build();

    }

    @GET
    public Response getHey(){
        System.out.println("Get Hey");
        ResultBean result = new ResultBean();
        result.setStatus(200);
        return Response.status(result.getStatus()).entity(result).build();
    }

}
