package org.iBookStore;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/bookRequest")
public class BookRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson g = new Gson();
        Database db = new Database();
        try {
            String js;
            db.open();
            ArrayList<Book> books = db.fetchAllBooks();
            js = g.toJson(books);
            out.print(js);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
