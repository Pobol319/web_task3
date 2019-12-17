package com.epam.task.runner;

import com.epam.task.comparator.ClientPriorityComparator;
import com.epam.task.creator.EntityCreator;
import com.epam.task.entity.CashDesk;
import com.epam.task.entity.Client;
import com.epam.task.entity.ClientPriorityEnum;
import com.epam.task.entity.Restaurant;
import com.epam.task.reader.JsonReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RestaurantRunner {

   /* public static void main(String[] args) {
        String path = "src\\main\\resources\\restaurant.json";

        EntityCreator creator = new EntityCreator();
        JsonReader jsonReader = new JsonReader(path);
        String nameOfRestaurant = jsonReader.getRestaurantName();
        Restaurant restaurant = creator.createRestaurant(nameOfRestaurant);
        Iterator iteratorCashDesk = jsonReader.getIteratorCashDesks();
        List<CashDesk> cashDesks = creator.createListOfCashDesk(iteratorCashDesk);

        for(CashDesk cashDesk: cashDesks){
            restaurant.addCashDesk(cashDesk);
        }

        Iterator iteratorClients = jsonReader.getIteratorClients();
        List<Client> clients = creator.createListOfClients(iteratorClients,restaurant);

        for (Client client: clients){
            client.getThread().start();
        }
    }*/



    public static void main(String[] args) {
        String path = "src\\main\\resources\\restaurant.json";

        Restaurant restaurant = Restaurant.getInstance("Mcdonalds");

        CashDesk cashDesk1 = new CashDesk(1, 140);
        CashDesk cashDesk2 = new CashDesk(2, 250);
        CashDesk cashDesk3 = new CashDesk(3, 150);
        CashDesk cashDesk4 = new CashDesk(4, 50);

        restaurant.addCashDesk(cashDesk1);
        restaurant.addCashDesk(cashDesk2);
        /*restaurant.addCashDesk(cashDesk3);
        restaurant.addCashDesk(cashDesk4);*/

        Client client1 = new Client(restaurant, 10, "1", ClientPriorityEnum.IN_ORDER);
        Client client2 = new Client(restaurant, 15, "2", ClientPriorityEnum.OUT_OF_TURN);
        Client client3 = new Client(restaurant, 35, "3", ClientPriorityEnum.IN_ORDER);
        Client client4 = new Client(restaurant, 9, "4", ClientPriorityEnum.OUT_OF_TURN);
        Client client5 = new Client(restaurant, 1, "5", ClientPriorityEnum.IN_ORDER);
        Client client6 = new Client(restaurant, 10, "6", ClientPriorityEnum.OUT_OF_TURN);
        Client client7 = new Client(restaurant, 4, "7", ClientPriorityEnum.IN_ORDER);
        Client client8 = new Client(restaurant, 7, "8", ClientPriorityEnum.IN_ORDER);
        Client client9 = new Client(restaurant, 2, "9", ClientPriorityEnum.IN_ORDER);
        Client client10 = new Client(restaurant, 11, "10", ClientPriorityEnum.OUT_OF_TURN);

        List<Client> list = new ArrayList<>();
        list.add(client1);
        list.add(client2);
        list.add(client3);
        list.add(client4);
        list.add(client5);
        /*list.add(client6);
        list.add(client7);
        list.add(client8);
        list.add(client9);
        list.add(client10);*/

        for (Client client : list) {
            client.getThread().start();
        }

    }
}
