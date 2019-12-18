package com.epam.task.entity;

import com.epam.task.exception.ResourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Client implements Runnable {
    private final static Logger LOG = LogManager.getLogger(Client.class);
    private Restaurant restaurant;
    private CashDesk cashDesk;
    private String name;
    private int numberOfProducts;
    private ClientPriorityEnum clientPriority;
    private Thread thread;

    public Client(Restaurant restaurant, int numberOfProducts, String name, ClientPriorityEnum clientPriority) {
        this.restaurant = restaurant;
        this.numberOfProducts = numberOfProducts;
        this.name = name;
        this.clientPriority = clientPriority;
        this.thread = new Thread(this);
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

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        System.out.println("Client " + name + " comes to restaurant " + restaurant.getName());
        this.cashDesk = chooseCashDesk();
        /* System.out.println("Client " + getClientName() + " chose the cashDesk #"+ cashDesk.getNumberOfCashDesk());*/

      //  cashDesk.getLockForAddToQueue().lock();
        cashDesk.addClient(this);
        //cashDesk.getLockForAddToQueue().unlock();
        //cashDesk.getLockForServing().lock();
        try {
            cashDesk.serveClient();
        } catch (ResourceException e) {
            LOG.error("ResourceException!!! ", e);
        }
        //cashDesk.getLockForServing().unlock();

        /*while (true) {
            if (cashDesk.getLock().tryLock()) {
                try {
                    cashDesk.serveClient(this);
                } catch (ResourceException e) {
                    LOG.error("ResourceException!!! ", e);
                } finally {
                    cashDesk.getLock().unlock();
                    break;
                }
            }
        }*/


    }

    private CashDesk chooseCashDesk() {
        List<CashDesk> listOfCashDesk = restaurant.getCashDesks();
        CashDesk cashDeskWithLowestNumberOfClients = restaurant.getCashDesks().get(0);
        for (CashDesk cashDesk : listOfCashDesk) {
            if (cashDeskWithLowestNumberOfClients.getClients().size() > cashDesk.getClients().size()) {
                cashDeskWithLowestNumberOfClients = cashDesk;
            }
        }
        return cashDeskWithLowestNumberOfClients;
    }

}

