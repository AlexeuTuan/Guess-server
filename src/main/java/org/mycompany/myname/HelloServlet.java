package org.mycompany.myname;


import Databases.SQLiteClass;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import static Databases.SQLiteClass.getPicture;


public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        // httpServletResponse.getWriter().print("Hello from servlet");


    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        if(jb.toString().startsWith("1")) {
            try {
                SQLiteClass.addPicture(jb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if(jb.toString().startsWith("2")) {
            try {
                String ss = SQLiteClass.getPicture( (int) 1);
                res.setStatus(HttpServletResponse.SC_OK);
                res.getWriter().write(ss);
                res.getWriter().flush();
                res.getWriter().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}