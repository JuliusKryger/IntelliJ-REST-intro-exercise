package com.week4.demo.helloworld;

import com.google.gson.Gson;
import com.week4.demo.helloworld.entity.Animal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import java.util.List;
import java.util.Random;

@Path("/animals_db")
public class AnimalFromDB
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @GET
    @Produces("text/plain")
    public String ShowMeDaWay()
    {
        return "Hey you! you're awesome! Visit these places ->" + "\n" + "\n" +"/allanimalsfromdb" + "\n" + "/findanimalbyid/{Enter desired id here}" + "\n" + "/findanimalbytype/{Enter desired type here}" + "\n" + "/give_me_a_random_animal_on_this_really_really_long_url";
    }

    @Path("/allanimalsfromdb")
    @GET
    @Produces("application/json")
    public String getAnimals()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        }
        catch (Exception e)
        {
            return "Something went wrong! Could not retrieve animals from the database";
        }
        finally
        {
            em.close();
        }
    }

    @Path("/findanimalbyid/{id}")
    @GET
    @Produces("application/json")
    public String getAnimal(@PathParam("id") int id)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Animal animal = em.find(Animal.class, id);
            if (animal != null)
            {
                return new Gson().toJson(animal);
            }
            else
            {
                return "The provided ID does not exist in the database.";
            }
        }
        finally
        {
            em.close();
        }
    }

    @Path("/findanimalbytype/{type}")
    @GET
    @Produces("application/json")
    public String getAnimalType(@PathParam("type") String type)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
        Animal animal = em.createQuery("SELECT a FROM Animal a WHERE a.animal = :animal", Animal.class).setParameter("animal", type).getSingleResult();
        }
        catch (Exception e)
        {
            return "An animal of that type does not exist in the database";
        }
        return new Gson().toJson(type);
    }

    @Path("/give_me_a_random_animal_on_this_really_really_long_url")
    @GET
    @Produces("application/json")
    public String getAnimalByType()
    {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        int x = random.nextInt(4);
        try
        {
            em.getTransaction().begin();
            //if (x != 0 || x != 4) #Well this shit doesn't work, and I frankly don't know why
            Animal y = em.find(Animal.class, x);
            if ( y != null )
            {
                em.getTransaction().commit();
                return new Gson().toJson(y);
            }
            else
            {
                return "You got scammed by the random generator and I don't know why, but now you owe me pizza & cola!";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "There was an error with the random generated id";
        }
        finally
        {
            em.close();
        }
    }
}