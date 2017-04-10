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
import pl.polsl.report2.Report3SesionBeanLocal;

/**
 *
 * @author Raul Hernandez and Muhammed Zahid KIZMAZ
 * @version 1.0
 */
public class Servlet1 extends HttpServlet {

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
        // request.getParameter("erase");

        try (PrintWriter out = response.getWriter()) {
            try {
                if (request.getParameter("erase") != null) {
                    if (localBean.find(Integer.parseInt(request.getParameter("hidden"))) != null) {
                        localBean.delete(localBean.find(Integer.parseInt(request.getParameter("hidden"))));
                    }
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id can not be null!");
                return;
            }

            try {
                if (request.getParameter("save") != null) {
                    Airplane airplane = (Airplane) localBean.find(Integer.parseInt(request.getParameter("hidden")));
                    if (airplane != null) {
                        out.println(request.getParameterMap());
                        airplane.setAirplaneName(request.getParameter("airplaneName"));
                        airplane.setPassenger(Integer.parseInt(request.getParameter("passenger")));
                        localBean.update(airplane);
                    }                  
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id can not be null!");
                return;
            }

            
            out.println("<form action='Servlet1' method='POST'>");
            out.println("<input type = 'text' placeholder='Airplane name' name = 'newAirplaneName'>");
            out.println("<input type = 'text' placeholder='Number of passengers' name = 'newPassengers'>");
            out.println("<input type=\"submit\" value=\"create\" name='create'/>");
            out.println("<br/>");
            out.println("</form>");
            out.println("<br/>");
            try {
                if (request.getParameter("create") != null) {
                    Airplane a = new Airplane();
                    a.setAirplaneName(request.getParameter("newAirplaneName"));
                    a.setPassenger(Integer.parseInt(request.getParameter("newPassengers")));
                    localBean.insert(a);
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id can not be null!");
                return;
            }
            List<Airplane> airplanes = localBean.findAll();
            for (Iterator<Airplane> iterator = airplanes.iterator();
                    iterator.hasNext();) {
                out.println("<form action='Servlet1' method='POST'>");
                Airplane next = iterator.next();
                out.println("<input type='hidden' value=\"" + next.getId() + "\" name='hidden'/>");
                out.println("<input type = 'text' value=\"" + next.getAirplaneName() + "\" name = 'airplaneName'>");
                out.println("<input type = 'text' value=\"" + next.getPassenger() + "\" name='passenger'>");
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
