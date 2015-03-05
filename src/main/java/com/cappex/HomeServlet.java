package com.cappex;

import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

//TODO note must include <meta name="fragment" content="!">

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String escapedFragment = request.getParameter("_escaped_fragment_");

        if(escapedFragment != null) {
            String urlFragment = URLDecoder.decode(escapedFragment, "UTF-8");

            String page = urlFragment.replace("page=", "");

            if(!NumberUtils.isNumber(page)) {
                page = "1";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/composite.jsp?page=" + page);
            dispatcher.forward(request, response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        dispatcher.forward(request, response);
    }
}