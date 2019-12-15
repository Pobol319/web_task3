package com.epam.task.creator;

import com.epam.task.entity.CashDesk;
import com.epam.task.entity.Client;
import com.epam.task.entity.ClientPriorityEnum;
import com.epam.task.entity.Restaurant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EntityCreator {

    public Restaurant createRestaurant(String name) {
        return Restaurant.getInstance(name);
    }

    public List<CashDesk> createListOfCashDesk(Iterator itr) {
        List<CashDesk> listOfCashDesks = new ArrayList<>();
        while (itr.hasNext()) {
            Iterator<Map.Entry> tempItr = ((Map) itr.next()).entrySet().iterator();
            while (tempItr.hasNext()) {
                Map.Entry entry = tempItr.next();
                int numberOfCashDesk = Integer.parseInt(entry.getValue().toString());
                entry = tempItr.next();
                int timeOfServiceForOneProduct = Integer.parseInt(entry.getValue().toString());
                CashDesk cashDesk = new CashDesk(numberOfCashDesk, timeOfServiceForOneProduct);
                listOfCashDesks.add(cashDesk);
            }
        }
        return listOfCashDesks;
    }

    public List<Client> createListOfClients(Iterator itr, Restaurant restaurant) {
        List<Client> listOfCashDesks = new ArrayList<>();
        while (itr.hasNext()) {
            Iterator<Map.Entry> tempItr = ((Map) itr.next()).entrySet().iterator();
            while (tempItr.hasNext()) {
                Map.Entry entry = tempItr.next();
                String nameOfClient = entry.getValue().toString();
                entry = tempItr.next();
                String clientPriority = entry.getValue().toString().toUpperCase();
                entry = tempItr.next();
                int numberOfProducts = Integer.parseInt(entry.getValue().toString());
                Client client = new Client(restaurant, numberOfProducts, nameOfClient, ClientPriorityEnum.valueOf(clientPriority));
                listOfCashDesks.add(client);
            }
        }
        return listOfCashDesks;
    }

}
