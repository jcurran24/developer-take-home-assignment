package com.cappex;

import com.cappex.dao.College;
import com.cappex.service.CollegeService;
import com.cappex.service.CollegeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet is responsible for loading the College Search page.
 */

@WebServlet(urlPatterns = {"/college-search", ""})
public class SearchServlet extends HttpServlet {
    private CollegeService collegeService = new CollegeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("collegeName") != null) {
            String searchParam = request.getParameter("collegeName");

            List<College> searchResults = null;
            try {
                searchResults = collegeService.searchCollege(searchParam);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("An error has occurred. Please try again later.");
            }

            ObjectMapper mapper = new ObjectMapper();
            //Object to JSON in String
            String jsonInString = mapper.writeValueAsString(searchResults);

            request.setAttribute("resultsFound", true);
            request.setAttribute("searchResults", jsonInString);
        } else {
            request.setAttribute("resultsFound", false);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
        dispatcher.forward(request, response);
    }
}