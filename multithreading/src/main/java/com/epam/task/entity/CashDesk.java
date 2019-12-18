package com.epam.task.entity;

import com.epam.task.exception.ResourceException;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk {
    private Lock lockForServing = new ReentrantLock();
    private Lock lockForAddToQueue = new ReentrantLock();
    private PriorityQueue<Client> clients;
    private int numberOfCashDesk;
    private int timeOfServiceForOneProduct;

    public CashDesk(int numberOfCashDesk, int timeOfServiceForOneProduct, Comparator<Client> comparator) {
        clients = new PriorityQueue<>(comparator);
        this.numberOfCashDesk = numberOfCashDesk;
        this.timeOfServiceForOneProduct = timeOfServiceForOneProduct;
    }

    public void serveClient() throws ResourceException {
        lockForServing.lock();
        Client clientServiceAtCashDesk = clients.element();
        System.out.println("Client " + clientServiceAtCashDesk.getClientName() + " is serving on cashDesk #" + numberOfCashDesk);
        try {
            TimeUnit.MILLISECONDS.sleep(timeOfServiceForOneProduct * clientServiceAtCashDesk.getNumberOfProducts());
        } catch (InterruptedException e) {
            throw new ResourceException("InterruptedException!!!", e);
        }
        System.out.println("Client " + clientServiceAtCashDesk.getClientName() + " is served");
        clients.remove(clientServiceAtCashDesk);
        System.out.println("Client " + clientServiceAtCashDesk.getClientName() + " leaves restaurant");
        lockForServing.unlock();
    }

    public PriorityQueue<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        lockForAddToQueue.lock();
        System.out.println("Client " + client.getClientName() + " enter the cashDesk #" + numberOfCashDesk);
        clients.add(client);
        lockForAddToQueue.unlock();
    }

    public void removeClient(Client client) {
        System.out.println("Client " + client.getClientName() + " leaves restaurant");
        clients.poll();
    }

    public int getNumberOfCashDesk() {
        return numberOfCashDesk;
    }

    public Lock getLockForServing() {
        return lockForServing;
    }

    public Lock getLockForAddToQueue() {
        return lockForAddToQueue;
    }

}