package com.week4.demo.helloworld.entity;

import javax.persistence.*;

@Table(name = "animal")
@Entity
public class Animal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    private Integer pid;
    private String animal;
    private String sound;

    public Integer getId()
    {
        return pid;
    }

    public void setId(Integer pid)
    {
        this.pid = pid;
    }

    public String getAnimal()
    {
        return animal;
    }

    public void setAnimal(String animal)
    {
        this.animal = animal;
    }

    public String getSound()
    {
        return sound;
    }

    public void setSound(String sound)
    {
        this.sound = sound;
    }

    @Override
    public String toString()
    {
        return "ID = " + pid + ", animal = " + animal + ", sound = " + sound;
    }
}