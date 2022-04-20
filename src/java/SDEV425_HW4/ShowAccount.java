/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.Filter;
import java.util.*;
import java.time.LocalDateTime;

// DB resources
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.RequestDispatcher;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author jim
 */
public class ShowAccount extends HttpServlet {

    // Variable
    private static HttpSession session;
    // Database field data
    private int user_id;
    private String Cardholdername;
    private String CardType;
    private String ServiceCode;
    private String CardNumber;
    private Date expiredate;
    private String maskedCC;
    private String message;
    private String location;
    private static String log = "/Users/michaelgalyen/Documents/SDEV_425/HW4/log.txt";
    
    /**This method logs events by combining information with 
     a string that is passed in as an argument*/
    private final static void eventLogger(String message, String location) {
        
        // Get date and time as string
        String logTime = getTimeStamp();
        // Spllit date out of logtime
        String date = logTime.split(" ")[0];
        // Split time out of logTime
        String time = logTime.split(" ")[1];
        // Create fileWriter
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(log, true)))) {
            printWriter.println("Message=" +message + " Date=" + date 
                    + " Time=" + time + " User=" + session.getAttribute("UMUCUserEmail") 
                    + " Location=" + location + " Servlet-context=" + session.getServletContext() 
                    + " Class=" + session.getClass() + " Session-creation-time=" + session.getCreationTime() 
                    + " Current time=" + System.currentTimeMillis());
        }
        catch (IOException e) {
            
        }  
        
    } //
    
    /**This method returns a string of the current date and time local to US East coast.
     It takes no arguments and returns one string*/
    protected static String getTimeStamp(){
        DateTimeFormatter Formatter = 
                 DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm/ss");
        // Get time zone object for US East Coast
        ZoneId zoneID = ZoneId.of("-05:00");
        // Get the local date and time in East Coast time
        LocalDateTime now = LocalDateTime.now(zoneID);
        // Get formatted local date and time
        String dateTime = Formatter.format(now);
        return dateTime;
    }
    /**This method returns a string with all but the last 4 positions
     replaced by "x". It takes no arguments.*/
    protected void maskCC(String CC) {
        StringBuilder base = new StringBuilder(15);
        
        
        String[] tempList = CC.split("");
        for (int i = 0; i < tempList.length; i++) {
            if (i > 8) {
                base.append(tempList[i]);
            }
            else {
                base.append("x");
            }
        }
        maskedCC = base.toString();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        session = request.getSession(true);
        if (session.getAttribute("UMUCUserEmail") == null) {
            message = "Access denied to account data";
            location = "processRequest method in ShowAccount.java";
            eventLogger(message, location);
            // Send back to login page 
            response.sendRedirect("login.jsp");
        } else {
            // Connect to the Database and pull the data
            getData();
            
            // Set the Attribute for viewing in the JSP
            request.setAttribute("Cardholdername", Cardholdername);
            request.setAttribute("CardType", CardType);
            request.setAttribute("ServiceCode", ServiceCode);
            request.setAttribute("CardNumber", maskedCC);
            request.setAttribute("expiredate", expiredate);
            message = "Access granted to account data";
            location = "processRequest method in ShowAccount.java";
            eventLogger(message, location);
            RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
            dispatcher.forward(request, response);       
            
  
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void getData() {
        String maskedCC;
        try {
            ClientDataSource ds = new ClientDataSource();
            ds.setDatabaseName("SDEV425");
            ds.setServerName("localhost");
            ds.setPortNumber(1527);
            ds.setUser("sdev425");
            ds.setPassword("sdev425");
            ds.setDataSourceName("jdbc:derby");

            Connection conn = ds.getConnection();

            Statement stmt = conn.createStatement();
            String sql = "select user_id,Cardholdername, Cardtype,"
                    + "ServiceCode, CardNumber,expiredate"
                    + " from customeraccount  where user_id = " + session.getAttribute("UMUCUserID");
            ResultSet rs = stmt.executeQuery(sql);
            // Assign values
            while (rs.next()) {
                user_id = rs.getInt(1);
                Cardholdername = rs.getString(2);
                CardType = rs.getString(3);
                ServiceCode = rs.getString(4);
                CardNumber = rs.getString(5); 
                maskCC(CardNumber);
                expiredate = rs.getDate(6);                
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
