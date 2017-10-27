/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.servlet;

import fr.aurelien.webapp.dao.DBCN;
import fr.aurelien.webapp.dao.GenreDAO;
import fr.aurelien.webapp.entity.Genre;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
public class GenreListServlet extends HttpServlet {

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
            out.println("<title>Servlet GenreListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenreListServlet at " + request.getContextPath() + "</h1>");
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
        
         
        try {
           Connection cn = DBCN.getInstance();
            GenreDAO dao = new GenreDAO(cn);
            List<Genre> genreList = dao.findAll().getAll();
            request.setAttribute("genreList", genreList);
            
            //Affichage Message d'erreur
            if(request.getSession().getAttribute("message") != null){
                request.setAttribute("message", request.getSession().getAttribute("message"));
                request.getSession().removeAttribute("message");
            }
        
       getServletContext()
                    .getRequestDispatcher("/genre-list.jsp")
                    .forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(GenreListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenreListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

            
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
        
        
        int id = Integer.valueOf(request.getParameter("id"));
            
            
        try {
            // Connexion à la base de donnée
          Connection  cn = DBCN.getInstance();
          GenreDAO dao = new GenreDAO(cn);
            Genre entity = new Genre();
            
            entity.setId(id);
            
            dao.deleteOneById(entity); 
            // Affichage du message d'erreur
            request.getSession().setAttribute("message", "la ligne " + entity.getId()+" a bien été supprimé");
            //Redirection
            response.sendRedirect("/firstWebApp/genre-list");
        
          
        } catch (SQLException ex) {
            Logger.getLogger(GenreListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenreListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
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