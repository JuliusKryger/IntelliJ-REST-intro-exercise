package com.week4.demo.helloworld;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.week4.demo.helloworld.model.AnimalNoDB;
import javax.ws.rs.*;

@Path("/animals")
public class AnimalDemo
{
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String getRoar()
    {
        return "RAAAR! I'm a DEADLY dinosaur!" + "\n" + "\n" +"Besides being a dangerous dino, I really don't do anything, so go visit my friends ->" + "\n" + "\n" + "/arrayofanimals" + "\n" + "/dogbarking";
    }

    public AnimalDemo()
    {
        super();
    }

    @Path("/arrayofanimals")
        @GET
        @Produces("application/json")
        public String getAnimalList()
        {
            String [] animalArray = {"Dog", "Cat", "Mouse", "Bird"};
            try
            {
                String array = gson.toJson(animalArray);
                return array;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "Something went terribly wrong with our simple string array!";
        }

    @Path("/dogbarking")
    @GET
    @Produces("application/json")
    public String animalnodb()
    {
        try {
        AnimalNoDB animalNoDB = new AnimalNoDB("Dog","Mæææh! Woops i mean Wuuf! *Barking*");
        String jsonString = gson.toJson(animalNoDB);
        return jsonString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Well this was NOT supposed to happen :(";
    }
}