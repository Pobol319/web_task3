package com.epam.task.entity;

import com.epam.task.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class Client implements Runnable {
    private final static Logger LOG = LogManager.getLogger(Client.class);
    private Restaurant restaurant;
    private String name;
    private int numberOfProducts;
    private ClientPriorityEnum clientPriority;

    public Client(Restaurant restaurant, int numberOfProducts, String name, ClientPriorityEnum clientPriority) {
        this.restaurant = restaurant;
        this.numberOfProducts = numberOfProducts;
        this.name = name;
        this.clientPriority = clientPriority;
    }

    public String getClientName() {
        return name;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public ClientPriorityEnum getClientPriority() {
        return clientPriority;
    }


    @Override
    public void run() {
        System.out.println("Client " + name + " comes to restaurant " + restaurant.getName());
        CashDesk cashDesk = chooseCashDesk();
        cashDesk.addClient(this);
        try {
            cashDesk.serveClient();
        } catch (ResourceException e) {
            LOG.error("ResourceException!!! ", e);
        }
    }

    private CashDesk chooseCashDesk() {
        List<CashDesk> listOfCashDesk = restaurant.getCashDesks();
        Random random = new Random();
        int firstVerifiedCashDesk = random.nextInt(listOfCashDesk.size());
        CashDesk cashDeskWithLowestNumberOfClients = restaurant.getCashDesks().get(firstVerifiedCashDesk);
        for (CashDesk cashDesk : listOfCashDesk) {
            if (cashDeskWithLowestNumberOfClients.getClients().size() > cashDesk.getClients().size()) {
                cashDeskWithLowestNumberOfClients = cashDesk;
            }
        }
        return cashDeskWithLowestNumberOfClients;
    }

}

