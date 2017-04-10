/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.report3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.report2.Airplane;
import pl.polsl.report2.Customer;
import pl.polsl.report2.Report3SesionBeanLocal;

/**
 *
 * @author Raul Hernandez and Muhammed Zahid KIZMAZ
 * @version 1.0
 */
public class Servlet2 extends HttpServlet {

    
    /**
     * This is the injection bean
     */
    @EJB
    private Report3SesionBeanLocal localBean;

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

        try (PrintWriter out = response.getWriter()) {
            try {
                if (request.getParameter("erase") != null) {
                    if (localBean.findC(Integer.parseInt(request.getParameter("hidden"))) != null) {
                        localBean.deleteC(localBean.findC(Integer.parseInt(request.getParameter("hidden"))));
                    }
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id can not be null!");
                return;
            }
            try {
                if (request.getParameter("save") != null) {
                    Customer customer = (Customer) localBean.findC(Integer.parseInt(request.getParameter("hidden")));
                    if (customer != null) {
                        customer.setName(request.getParameter("Name"));
                        customer.setLastname(request.getParameter("lastname"));
                        localBean.updateC(customer);
                    }
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id can not be null!");
                return;
            }

            List<Airplane> airplanes = localBean.findAll();
            out.println("<form action='Servlet2' method='POST'>");
            out.println("<input type = 'text' placeholder='Insert name' name = 'newName'>");
            out.println("<input type = 'text' placeholder='Insert lastname' name = 'newLastname'>");
            out.println("<select  name='id' >");
            for (Iterator<Airplane> iterator = airplanes.iterator(); iterator.hasNext();) {
                Airplane next = iterator.next();
                out.println("<option value=\"" + next.getId() + "\">" + next.getAirplaneName() + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"create\" name='create'/>");
            out.println("<br/>");
            out.println("<br/>");
            out.println("</form>");
            try {
                if (request.getParameter("create") != null) {
                    Customer c = new Customer();
                    c.setName(request.getParameter("newName"));
                    c.setLastname(request.getParameter("newLastname"));
                    c.setAirplane(localBean.find(Integer.parseInt(request.getParameter("id"))));
                    localBean.insert(c);
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id can not be null!");
                return;
            }

            List<Customer> customers = localBean.findAllCustomer();
            for (Iterator<Customer> iterator = customers.iterator();
                    iterator.hasNext();) {
                out.println("<form action='Servlet2' method='POST'>");
                Customer next = iterator.next();
                out.println("<input type='hidden' value=\"" + next.getId() + "\" name='hidden'/>");
                out.println("<input type = 'text' value=\"" + next.getName() + "\" name = 'Name'>");
                out.println("<input type = 'text' value=\"" + next.getLastname() + "\" name='lastname'>");
                out.println("<input type=\"submit\" value=\"erase\" name='erase'/>");
                out.println("<input type=\"submit\" value=\"save\" name='save'/>");
                out.println("<br/>");
                out.println("</form>");
            }
            
            out.println("<a href= 'Servlet3'>Back</a> ");
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

}
