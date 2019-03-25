package org.fasttrackit.persistence;

import org.fasttrackit.domain.AgendaItem;
import org.fasttrackit.transfer.SaveAgendaItemRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaItemRepository {

    public void createAgendaItem(SaveAgendaItemRequest request) throws SQLException, IOException, ClassNotFoundException {

        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "INSERT INTO agenda_items (first_name, last_name, phone_number) VALUES(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getFirst_name());
            preparedStatement.setString(2, request.getLast_name());
            preparedStatement.setString(3, request.getPhone_number());

            preparedStatement.executeUpdate();}



    }

    public List<AgendaItem> getAgendaItem() throws SQLException, IOException, ClassNotFoundException {

        try (Connection connection = DatabaseConfiguration.getConnection()){

            String query = "SELECT id, first_name, last_name, phone_number FROM agenda_items ORDER BY id DESC ;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<AgendaItem> response = new ArrayList<>();

            while (resultSet.next()){
                AgendaItem item = new AgendaItem();
                item.setId(resultSet.getLong("id"));
                item.setFirst_name(resultSet.getString("first_name"));
                item.setLast_name(resultSet.getString("last_name"));
                item.setPhone_number(resultSet.getString("phone_number"));



                response.add(item);
            }

            return response;}

    }


}
