package com.epam.task.runner;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Comparator cc = new Comp();
        Queue<Integer> q = new PriorityQueue(cc);
        Random random = new Random();

        for (int i = 0; i<10 ;i++){
            int a = random.nextInt(100);
            System.out.print(a + " ");
            q.add(a);
        }
        System.out.println();
        for(int i = 0; i < 10; i++){

            System.out.print(q.element() + " ");
            q.poll();
        }

    }

}
