/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.aurelien.webapp.servlet;

import fr.aurelien.webapp.dao.DBCN;
import fr.aurelien.webapp.dao.EditorDAO;
import fr.aurelien.webapp.entity.Editor;
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
public class EditorListServlet extends HttpServlet {

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
            out.println("<title>Servlet EditorListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditorListServlet at " + request.getContextPath() + "</h1>");
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

            EditorDAO dao = new EditorDAO(cn);
            List<Editor> editorList = dao.findAll().getAll();
            request.setAttribute("editorList", editorList);

            //Affichage Message d'erreur
            if (request.getSession().getAttribute("message") != null) {
                request.setAttribute("message", request.getSession().getAttribute("message"));
                request.getSession().removeAttribute("message");
            }
            getServletContext()
                    .getRequestDispatcher("/editor-list.jsp")
                    .forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(EditorListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditorListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
          Connection  cn = DBCN.getInstance();
          EditorDAO dao = new EditorDAO(cn);
        Editor entity = new Editor();
        
        entity.setId(id);
        
        dao.deleteOneById(entity);
        // Affichage du message d'erreur
        request.getSession().setAttribute("message", "la ligne " + entity.getId() + " a bien été supprimé");
        //Redirection
        response.sendRedirect("/firstWebApp/editor-list");
          
        } catch (SQLException ex) {
            Logger.getLogger(EditorListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditorListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
