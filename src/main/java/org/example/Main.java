package org.example;

public class Main {

    public static Car[] generateCars(int n){
        Car[] cars = new Car[n];
        for(int i  = 0;i<n;i++){
            cars[i] = new Car(Direction.getRandomDirection());
        }
        return cars;
    }
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        Thread carAdderThread = new Thread(() -> {
            while(true){
                Car[] cars = generateCars(6);

                for (int i = 0; i < cars.length; i++) {
                    semaphore.addCar(cars[i]);
                }

                try {
                    Thread.sleep((long) (100 + Math.random() * 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread regulatorThread = new Thread(() -> semaphore.regulateCars());

        carAdderThread.start();
        regulatorThread.start();

    }
}
