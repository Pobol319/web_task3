package com.epam.task.entity;

import com.epam.task.comparator.ClientPriorityComparator;
import com.epam.task.exception.ResourceException;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk {
    private Lock lock = new ReentrantLock();
    private Lock lockOrder = new ReentrantLock();
    private PriorityQueue<Client> clients;
    private int numberOfCashDesk;
    private int timeOfServiceForOneProduct;

    public CashDesk(int numberOfCashDesk, int timeOfServiceForOneProduct) {
        clients = new PriorityQueue<>(new ClientPriorityComparator());
        this.numberOfCashDesk = numberOfCashDesk;
        this.timeOfServiceForOneProduct = timeOfServiceForOneProduct;
    }

    public void serveClient( ) throws ResourceException {
        Client client = clients.element();
        System.out.println("Client " + client.getClientName() + " is serving on cashDesk #" + numberOfCashDesk);
        try {
            TimeUnit.MILLISECONDS.sleep(timeOfServiceForOneProduct * client.getNumberOfProducts());
        } catch (InterruptedException e) {
            throw new ResourceException("InterruptedException!!!", e);
        }
        System.out.println("Client " + client.getClientName() + " is served");
        removeClient(client);
    }

    public PriorityQueue<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) throws ResourceException {
        lock.lock();
        System.out.println("Client " + client.getClientName() + " enter the cashDesk #" + numberOfCashDesk);
        clients.add(client);
        serveClient();
    }

    public void removeClient(Client client) {
        System.out.println("Client " + client.getClientName() + " leaves restaurant");
        clients.poll();
        lock.unlock();
    }

    public int getNumberOfCashDesk() {
        return numberOfCashDesk;
    }

    public Lock getLock() {
        return lock;
    }

}