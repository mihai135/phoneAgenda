package org.fasttrackit.service;

import org.fasttrackit.persistence.AgendaItemRepository;
import org.fasttrackit.transfer.SaveAgendaItemRequest;

import java.io.IOException;
import java.sql.SQLException;

public class AgendaItemService {

    private AgendaItemRepository agendaItemRepository = new AgendaItemRepository();

    public void createAgendaItem(SaveAgendaItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating item: " + request);
        agendaItemRepository.createAgendaItem(request);
    }
}
