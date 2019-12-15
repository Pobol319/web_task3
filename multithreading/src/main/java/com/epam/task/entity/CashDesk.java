package com.epam.task.entity;

import com.epam.task.comparator.ClientPriorityComparator;
import com.epam.task.exception.ResourceException;
import org.apache.logging.log4j.core.net.Priority;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk{
    private static Lock lock = new ReentrantLock();
    private static Lock lockForServing = new ReentrantLock();
    private Queue<Client> clients;
    private int numberOfCashDesk;
    private int timeOfServiceForOneProduct;

    public CashDesk(int numberOfCashDesk, int timeOfServiceForOneProduct) {
        clients = new PriorityQueue<>(new ClientPriorityComparator());
        this.numberOfCashDesk = numberOfCashDesk;
        this.timeOfServiceForOneProduct = timeOfServiceForOneProduct;
    }

    public void serveClient(Client client) throws ResourceException {
        System.out.println("Client " + client.getClientName() + " is serving on cashDesk#" + getNumberOfCashDesk());
        try {
            TimeUnit.MILLISECONDS.sleep(timeOfServiceForOneProduct * client.getNumberOfProducts());
        } catch (InterruptedException e) {
            throw new ResourceException("InterruptedException!!!", e);
        }
        System.out.println("Client " + client.getClientName() + " is served");
    }

    public Queue<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.poll();
    }

    public int getNumberOfCashDesk() {
        return numberOfCashDesk;
    }

    public Lock getLock() {
        return lock;
    }
}