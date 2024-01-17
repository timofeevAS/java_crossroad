package org.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Semaphore {
    private Queue<Car> queue;
    private Lock lock;
    private Condition carArrived;

    public Semaphore(){
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.carArrived = lock.newCondition();
    }

    public void addCar(Car car){
        lock.lock();
        try {
            this.queue.offer(car);
            carArrived.signal();
        }finally {
            lock.unlock();
        }

    }

    public void regulateCars() {
        while (true) {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    carArrived.await();
                }

                Car priorityCar = queue.poll();
                long driveTime = (long) (300 + Math.random()*1200); // In range [300;1500] ms
                Thread.sleep(driveTime);
                System.out.println("Priority car drive (time: " + driveTime + "): " + priorityCar);

                Iterator<Car> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    Car car = iterator.next();

                    if (car.canDrive(priorityCar)) {
                        driveTime = (long) (300 + Math.random()*1200); // In range [300;1500] ms
                        Thread.sleep(driveTime);
                        System.out.println("Also car drive (time: " + driveTime + "): " + car);
                        iterator.remove();
                    }
                }
                System.out.println("------------------------");
                System.out.println(queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}