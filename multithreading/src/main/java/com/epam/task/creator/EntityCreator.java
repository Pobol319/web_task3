package com.epam.task.creator;

import com.epam.task.entity.CashDesk;
import com.epam.task.entity.Client;
import com.epam.task.entity.ClientPriorityEnum;
import com.epam.task.entity.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class EntityCreator {
    private final static Logger LOG = LogManager.getLogger(EntityCreator.class);

    public Restaurant createRestaurant(String name) {
        LOG.info("Restaurant created");
        return Restaurant.getInstance(name);
    }

    public List<CashDesk> createListOfCashDesk(Iterator cashDeskIterator) {
        List<CashDesk> listOfCashDesks = new ArrayList<>();
        while (cashDeskIterator.hasNext()) {
            Iterator<Map.Entry> tempItr = ((Map) cashDeskIterator.next()).entrySet().iterator();
            while (tempItr.hasNext()) {
                Map.Entry entry = tempItr.next();
                int numberOfCashDesk = Integer.parseInt(entry.getValue().toString());
                entry = tempItr.next();
                int timeOfServiceForOneProduct = Integer.parseInt(entry.getValue().toString());
                CashDesk cashDesk = new CashDesk(numberOfCashDesk, timeOfServiceForOneProduct);
                listOfCashDesks.add(cashDesk);
            }
        }
        LOG.info("List of CashDesks created");
        return listOfCashDesks;
    }

    public List<Client> createListOfClients(Iterator clientIterator, String nameOfRestaurant) {
        List<Client> listOfCashDesks = new ArrayList<>();
        while (clientIterator.hasNext()) {
            Iterator<Map.Entry> tempItr = ((Map) clientIterator.next()).entrySet().iterator();
            while (tempItr.hasNext()) {
                Map.Entry entry = tempItr.next();
                String nameOfClient = entry.getValue().toString();
                entry = tempItr.next();
                String clientPriority = entry.getValue().toString().toUpperCase();
                entry = tempItr.next();
                int numberOfProducts = Integer.parseInt(entry.getValue().toString());
                Client client = new Client(Restaurant.getInstance(nameOfRestaurant), numberOfProducts, nameOfClient, ClientPriorityEnum.valueOf(clientPriority));
                listOfCashDesks.add(client);
            }
        }
        LOG.info("List of Clients created");
        return listOfCashDesks;
    }

}
