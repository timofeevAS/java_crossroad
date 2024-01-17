package org.example;

public enum Direction {
    SN,
    ES,
    NS,
    WE;

    public static Direction getRandomDirection(){
        // Function to get random direction
        return values()[(int) (Math.random() * values().length)];
    }
}
