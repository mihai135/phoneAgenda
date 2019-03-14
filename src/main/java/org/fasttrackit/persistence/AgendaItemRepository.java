package org.fasttrackit.persistence;

import org.fasttrackit.transfer.SaveAgendaItemRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


}
