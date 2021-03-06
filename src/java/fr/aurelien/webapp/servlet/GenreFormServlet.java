/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.servlet;

import fr.aurelien.webapp.dao.DBCN;
import fr.aurelien.webapp.dao.GenreDAO;
import fr.aurelien.webapp.entity.GenreEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author formation
 */
public class GenreFormServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GenreFormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenreFormServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        
       Integer id = Integer.valueOf(request.getParameter("id"));

        // Récupération d'une entité Author en fonction de l'id
        // Instanciation d'une entité Author
        GenreEntity genre = new GenreEntity();
        if (id != null) {

            // récupération de l'instance de connection à la base de donnée
            
                 
           try {
             Connection  cn = DBCN.getInstance();
                               // instanciation du DAO
                GenreDAO dao = new GenreDAO(cn);
                // Affetation de la variable author avec la requette executée
                genre = dao.findOneById(id).getOne();
               
           } catch (SQLException ex) {
               Logger.getLogger(GenreFormServlet.class.getName()).log(Level.SEVERE, null, ex);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(GenreFormServlet.class.getName()).log(Level.SEVERE, null, ex);
           }



        }

        // Définir un attribut pour passer l'entité Author au jsp
        request.setAttribute("genre", genre);

        // Délégation de l'affichage au JSP
        getServletContext()
                .getRequestDispatcher("/genre-form.jsp")
                .forward(request, response);
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
        getServletContext()
                    .getRequestDispatcher("/genre-form.jsp")
                    .forward(request, response);
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
