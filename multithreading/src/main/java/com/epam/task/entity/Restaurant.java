package com.epam.task.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
    private static Restaurant instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private String name;
    private List cashDesks;

    private Restaurant(String name) {
        this.name = name;
        cashDesks = new ArrayList();
    }

    public static Restaurant getInstance(String name) {
        lock.lock();
        try {
            if (instance == null) {
                synchronized (Restaurant.class) {
                    Restaurant tempInstance = new Restaurant(name);
                    instance = tempInstance;
                }
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public void addCashDesk(CashDesk cashDesk) {
        cashDesks.add(cashDesk);
    }

    public String getName() {
        return name;
    }

    public List<CashDesk> getCashDesks() {
        return cashDesks;
    }
}
