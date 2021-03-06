package org.iBookStore.servlet.utility;

import javax.servlet.http.HttpServletResponse;

public class ServletUtility {
    public static void setCORS(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Authorization," +
                " Content-Type,Accept,Cookie,token,User-Agent,Connection, X-Auth-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("utf-8");
    }
}
