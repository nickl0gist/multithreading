package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created on 20.07.2022
 * Shows how multithreading is faster than usage of one thread.
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
public class Main {

    static List<Integer> list1 = new ArrayList<>();
    static List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        doWorkByOneThread();
        doWorkByTwoThreads();
    }

    public static void listIntegerGenerator(List<Integer> list) {
        list.clear();
        Random randomizer = new Random();
        for (int i = 0; i < 1_000_000; i++) {
            list.add(randomizer.nextInt(100));
        }
    }

    public static void doWorkByOneThread() {
        long start = System.currentTimeMillis();
        listIntegerGenerator(list1);
        listIntegerGenerator(list2);
        long finish = System.currentTimeMillis();
        System.out.println("One Thread duration: " + (finish - start) + " ms.");
        System.out.println("OT size: " + list1.size() + " ms.");
        System.out.println("OT size: " + list2.size() + " ms.");
    }

    public static void doWorkByTwoThreads() throws InterruptedException {
        Thread thread1 = new Thread(() -> listIntegerGenerator(list1));
        Thread thread2 = new Thread(() -> listIntegerGenerator(list2));

        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        long finish = System.currentTimeMillis();
        System.out.println("MultiThread duration: " + (finish - start));
        System.out.println("MT size: " + list1.size() + " ms.");
        System.out.println("MT size: " + list2.size() + " ms.");
    }
}
