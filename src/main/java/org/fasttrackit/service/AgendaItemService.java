package org.fasttrackit.service;

import org.fasttrackit.domain.AgendaItem;
import org.fasttrackit.persistence.AgendaItemRepository;
import org.fasttrackit.transfer.SaveAgendaItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgendaItemService {

    private AgendaItemRepository agendaItemRepository = new AgendaItemRepository();

    public void createAgendaItem(SaveAgendaItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating item: " + request);
        agendaItemRepository.createAgendaItem(request);
    }

    public List<AgendaItem> getAgendaItem() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("getting agenda items");
        return agendaItemRepository.getAgendaItem();
    }

    public void deleteAgendaItems(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("deleting to do item " + id);
        agendaItemRepository.deleteAgendaItem(id);
        System.out.println("deleted to do item " + id);

    }

    public void updateAgendaItem(AgendaItem request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating item: " + request);
        agendaItemRepository.updateAgendaItem(request);
    }


}
