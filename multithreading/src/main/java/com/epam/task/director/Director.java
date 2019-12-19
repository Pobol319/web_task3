package com.epam.task.director;

import com.epam.task.creator.EntityCreator;
import com.epam.task.entity.CashDesk;
import com.epam.task.entity.Client;
import com.epam.task.entity.Restaurant;
import com.epam.task.exception.JsonReaderException;
import com.epam.task.reader.JsonFileReader;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Director {
    private static final String PATH_TO_JSON_FILE = "src\\main\\resources\\restaurant.json";

    public static void main(String[] args) throws JsonReaderException {
        EntityCreator creator = new EntityCreator();
        JsonFileReader jsonReader = new JsonFileReader(PATH_TO_JSON_FILE);
        String nameOfRestaurant = jsonReader.getRestaurantName();
        Restaurant restaurant = creator.createRestaurant(nameOfRestaurant);
        Iterator iteratorCashDesk = jsonReader.getCashDesksIterator();
        List<CashDesk> cashDesks = creator.createListOfCashDesk(iteratorCashDesk);

        for (CashDesk cashDesk : cashDesks) {
            restaurant.addCashDesk(cashDesk);
        }

        Iterator iteratorClients = jsonReader.getClientsIterator();
        List<Client> clients = creator.createListOfClients(iteratorClients, nameOfRestaurant);

        ExecutorService executor = Executors.newFixedThreadPool(clients.size());

        for (Client client : clients) {
            executor.execute(client);
        }
        executor.shutdown();
    }
}
