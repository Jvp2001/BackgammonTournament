package com.joshuapetersen.backgammontournament.data;

public enum Contestant
{
    ONE("One"),
    TWO("Two");

    Contestant(String contestentID)
    {

    }

    @Override
    public String toString()
    {
        return this.name();
    }
}
