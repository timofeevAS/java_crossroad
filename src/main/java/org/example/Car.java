package org.example;

public class Car {
    private Direction direction;

    public Car(Direction direction){
        this.direction = direction;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public boolean canDrive(Car car){
        if(car == null){
            return false;
        }

        // Direcion of car to check intersection
        Direction carDirection = car.getDirection();

        if(carDirection.equals(this.direction)){
            return true;
        }

        if(this.direction.equals(Direction.SN)){
            return carDirection.equals(Direction.NS);
        }
        else if(this.direction.equals(Direction.NS)){
            return carDirection.equals(Direction.SN);
        }
        else if(this.direction.equals(Direction.WE)){
            return false;
        }else if(this.direction.equals(Direction.ES)){
            return carDirection.equals(Direction.NS);
        }else{
            return false;
        }


    }

    public String toString(){
        return new String("Car: " + this.direction);
    }

}
