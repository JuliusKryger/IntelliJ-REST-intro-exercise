package com.week4.demo.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource
{
    @GET
    @Produces("text/plain")
    public String hello()
    {
        //This is the first page you reach upon running the program.
        return "Hello, World! .... yeah yeah yeah ... down below is where the fun begins" + "\n" + "\n" + "/animals" + "\n" + "/animals_db";
    }
}