package com.epam.task.entity;

import com.epam.task.exception.ResourceException;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk {
    private Lock lockForServing = new ReentrantLock();
    private Lock lockForAddToTurn = new ReentrantLock();
    private LinkedList<Client> clients;
    private int numberOfCashDesk;
    private int timeOfServiceForOneProduct;

    public CashDesk(int numberOfCashDesk, int timeOfServiceForOneProduct) {
        clients = new LinkedList<>();
        this.numberOfCashDesk = numberOfCashDesk;
        this.timeOfServiceForOneProduct = timeOfServiceForOneProduct;
    }

    public void serveClient() throws ResourceException {
        lockForServing.lock();
        Client nextClientToServe = getNextClientToServe();
        int indexOfClient = clients.indexOf(nextClientToServe);
        System.out.println("Client " + nextClientToServe.getClientName() + " is serving on cashDesk #" + numberOfCashDesk);
        try {
            TimeUnit.MILLISECONDS.sleep(timeOfServiceForOneProduct * nextClientToServe.getNumberOfProducts());
        } catch (InterruptedException e) {
            throw new ResourceException("InterruptedException!!!", e);
        }
        System.out.println("Client " + nextClientToServe.getClientName() + " is served");
        clients.remove(indexOfClient);
        System.out.println("Client " + nextClientToServe.getClientName() + " leaves restaurant");
        lockForServing.unlock();
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        lockForAddToTurn.lock();
        System.out.println("Client " + client.getClientName() + " enter the cashDesk #" + numberOfCashDesk);
        clients.add(client);
        lockForAddToTurn.unlock();
    }

    public Client getNextClientToServe() {
        for (Client client : clients) {
            if (client.getClientPriority().equals(ClientPriorityEnum.OUT_OF_TURN)) {
                return client;
            }
        }
        return clients.element();
    }

}