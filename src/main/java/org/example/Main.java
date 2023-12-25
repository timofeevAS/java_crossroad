package org.example;

public class Main {
    static final String[] directions = {"SN","NS","WE","EW","ES"};
    public static String getRandomDirection() {
        int randomIndex = (int) (Math.random() * directions.length);
        return directions[randomIndex];
    }
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        Thread carAdderThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Car car1 = new Car(getRandomDirection());
                Car car2 = new Car(getRandomDirection());
                Car car3 = new Car(getRandomDirection());
                Car car4 = new Car(getRandomDirection());
                System.out.println("New " + car1 + " " + car2 + " " + car3 + " " + car4);
                semaphore.addCar(car1);
                semaphore.addCar(car2);
                semaphore.addCar(car3);
                semaphore.addCar(car4);


                try {
                    Thread.sleep((long) (100 + Math.random() * 500));
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
