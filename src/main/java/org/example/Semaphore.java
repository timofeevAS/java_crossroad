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
                System.out.println("Go " + priorityCar);

                Iterator<Car> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    Car car = iterator.next();
                    if (!car.isIntersect(priorityCar)) {
                        System.out.println("Also go " + car);
                        iterator.remove();
                    }
                }
                System.out.println("------------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
