package com.epam.task.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Restaurant {
    private static final AtomicReference<Restaurant> ref = new AtomicReference<>();
    private String name;
    private List<CashDesk> cashDesks;

    private Restaurant(String name) {
        this.name = name;
        cashDesks = new ArrayList<>();
    }

    public static Restaurant getInstance(String name) {
        if (ref.get() == null) {
            ref.compareAndSet(null, new Restaurant(name));
        }
        return ref.get();
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
