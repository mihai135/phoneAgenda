package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.AgendaItemService;
import org.fasttrackit.transfer.SaveAgendaItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/agenda-item")
public class AgendaItemServlet extends HttpServlet {

    private AgendaItemService agendaItemService = new AgendaItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SaveAgendaItemRequest saveAgendaItemRequest = objectMapper.readValue(req.getReader(), SaveAgendaItemRequest.class);
        try {
            agendaItemService.createAgendaItem(saveAgendaItemRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }
}
