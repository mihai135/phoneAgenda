package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.AgendaItem;
import org.fasttrackit.service.AgendaItemService;
import org.fasttrackit.transfer.SaveAgendaItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            List<AgendaItem> agendaItem = agendaItemService.getAgendaItem();
            ObjectMapper objectMapper = new ObjectMapper();
            String responseJson = objectMapper.writeValueAsString(agendaItem);

            resp.setContentType("application/json");
            resp.getWriter().print(responseJson);
            resp.getWriter().flush();
        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your request. " +
                    e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        String id = req.getParameter("id");
        try {
            agendaItemService.deleteAgendaItems(Long.parseLong(id));
        }catch (Exception e) {
            resp.sendError(500, "There was an error processing your request. " +
                    e.getMessage());
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
        String id = req.getParameter("id");
        ObjectMapper objectMapper = new ObjectMapper();
        AgendaItem agendaItem = objectMapper.readValue(req.getReader(), AgendaItem.class);
        try {
            agendaItemService.updateAgendaItem(agendaItem);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }

    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }


}
