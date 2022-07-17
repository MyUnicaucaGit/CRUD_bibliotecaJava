/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.unicauca.apliweb.crud_biblioteca_java.control;

import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Authors;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.AuthorsJpaController;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.BooksJpaController;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.UserbooksJpaController;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.UsersJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class servletAppBiblioteca extends HttpServlet {

    private BooksJpaController booksJPA;
    private AuthorsJpaController authorsJPA;
    private UserbooksJpaController userbooksJPA;
    private UsersJpaController usersJPA;

    private final static String PU = "persistenceBiblioteca";

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletAppBiblioteca</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletAppBiblioteca at " + request.getContextPath() + "</h1>");
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

    @Override
    public void init() throws ServletException {
        super.init();
//creamos una instancia de la clase EntityManagerFactory
//esta clase se encarga de gestionar la construcción de entidades y
//permite a los controladores JPA ejecutar las operaciones CRUD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
//creamos una instancia del controldor JPA para la clase clients y le
//pasamos el gestor de entidades
        authorsJPA = new AuthorsJpaController(emf);
//esta parte es solamente para realizar la prueba:
//listamos todos los clientes de la base de datos y los imprimimos en consola 
List<Authors> listaAutores = authorsJPA.findAuthorsEntities();
//imprimimos los clientes en consola
        for (Authors autor : listaAutores) {
            System.out.println("Nombre " + autor.getName() + " pais " + autor.getCountry());
        }
    }

}
