package com.epam.task.comparator;

import com.epam.task.entity.Client;
import com.epam.task.entity.ClientPriorityEnum;

import java.util.Comparator;

public class ClientPriorityComparator implements Comparator<Client> {
    public int compare(Client o1, Client o2) {
        if (o1.getClientPriority().equals(ClientPriorityEnum.OUT_OF_TURN) && o2.getClientPriority().equals(ClientPriorityEnum.IN_ORDER)) {
            return 1;
        } else if (o1.getClientPriority().equals(ClientPriorityEnum.IN_ORDER) && o2.getClientPriority().equals(ClientPriorityEnum.OUT_OF_TURN)) {
            return -1;
        }
        return 0;
    }
}
