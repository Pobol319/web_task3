package com.epam.task.runner;

import com.epam.task.entity.CashDesk;
import com.epam.task.entity.Client;
import com.epam.task.entity.Restaurant;

import java.util.Random;

public class RestaurantRunner {
    public static void main(String[] args) {
        Restaurant restaurant = Restaurant.getInstance("Mcdonalds");
        CashDesk cashDesk1 = new CashDesk(1, 140);
        CashDesk cashDesk2 = new CashDesk(2, 250);

        restaurant.addCashDesk(cashDesk1);
        restaurant.addCashDesk(cashDesk2);


        new Client(restaurant, 100, "client50").start();
        Random random = new Random();
        for (int i = 1; i < 8; i++) {

            int randNumbOfItems = random.nextInt(10) + 1;
            Client client =  new Client(restaurant, randNumbOfItems, "client"+i);
            client.start();
        }
    }
}
