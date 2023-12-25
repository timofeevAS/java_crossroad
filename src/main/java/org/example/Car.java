package org.example;

public class Car {
    private String direction;
    /* Direction of car, example:
    * SN
    * NS
    * WE
    * EW
    * ES
    *  */

    public Car(String direction){
        this.direction = direction;
    }

    public String getDirection(){
        return this.direction;
    }

    public boolean isIntersect(Car car){
        if(car == null) return false;
        String carDir = car.getDirection();

        if(carDir == this.direction){
            return false;
        }

        if (this.direction.startsWith("S")){
            return !carDir.startsWith("N");
        }
        else if(carDir.startsWith("N")){
            return !carDir.startsWith("S");
        }
        else if(carDir.startsWith("W")){
            return carDir.startsWith("EW");
        }
        else if(carDir.startsWith("E")){
            return carDir.startsWith("N");
        }
        else{
            return false;
        }
    }

    public String toString(){
        return new String("Car: " + this.direction);
    }

}
