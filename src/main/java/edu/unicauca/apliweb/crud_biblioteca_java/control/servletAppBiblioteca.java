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
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet("/")
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
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new": //Muestra el formulario para crear un nuevo cliente
                    showNewForm(request, response);
                    break;
                case "/insert": //ejecuta la creación de un nuevo cliente en la BD 
                    insertAuthor(request, response);
                    break;
                case "/delete": //Ejecuta la eliminación de un cliente de la BD
                    deleteAuthor(request, response);
                    break;
                case "/edit": //Muestra el formulario para editar un cliente
                    showEditForm(request, response);
                    break;
                case "/update": //Ejecuta la edición de un cliente de la BD
                    updateAuthor(request, response);
                    break;
                case "/authors": //Ejecuta la edición de un cliente de la BD
                    listAuthors(request, response);
                    break;
                default:
                    inicio(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAuthors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Authors> listAuthors = authorsJPA.findAuthorsEntities();
        request.setAttribute("listAuthors", listAuthors);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list-authors.jsp");

        dispatcher.forward(request, response);
    }
    private void inicio(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("inicio.jsp");

        dispatcher.forward(request, response);
    }

    //muestra el formulario para crear un nuevo usuario
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("author-form.jsp");

        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
//toma el id del cliente a ser editaro
        int id = Integer.parseInt(request.getParameter("idA"));
//busca al cliente en la base de datos
        Authors existingClient = authorsJPA.findAuthors(id);
        RequestDispatcher dispatcher = null;
        if (existingClient != null) {
//si lo encuentra lo envía al formulario
            dispatcher = request.getRequestDispatcher("author-form.jsp");
            request.setAttribute("author", existingClient);
        } else {
//si no lo encuentra regresa a la página con la lista de los clientes 
            dispatcher = request.getRequestDispatcher("list-authors.jsp");
        }
        dispatcher.forward(request, response);
    }
//método para crear un cliente en la base de datos

    private void insertAuthor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
//toma los datos del formulario de clientes
        String name = request.getParameter("name");        
        String country = request.getParameter("country");
//crea un objeto de tipo Clients vacío y lo llena con los datos obtenidos 
        Authors aut = new Authors();
        aut.setName(name);
        aut.setCountry(country);
//Crea el cliente utilizando el objeto controlador JPA
        authorsJPA.create(aut);
//solicita al Servlet que muestre la página actualizada con la lista de
        response.sendRedirect("authors");
    }
//Método para editar un cliente

    private void updateAuthor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
//toma los datos enviados por el formulario de autores
        int id_author = Integer.parseInt(request.getParameter("idA"));
        String name = request.getParameter("name");
        String country = request.getParameter("country");
//crea un objeto vacío y lo llena con los datos del cliente
        Authors aut = new Authors();
        aut.setIdA(id_author);
        aut.setName(name);
        aut.setCountry(country);
        try {
//Edita el cliente en la BD
            authorsJPA.edit(aut);
        } catch (Exception ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("authors");
    }
//Elimina un cliente de la BD

    private void deleteAuthor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
//Recibe el ID del cliente que se espera eliminar de la BD
        int id = Integer.parseInt(request.getParameter("idA"));
        try {
//Elimina el cliente con el id indicado
            authorsJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("authors");
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);

        authorsJPA = new AuthorsJpaController(emf);
        usersJPA = new UsersJpaController(emf);
        booksJPA = new BooksJpaController(emf);
        userbooksJPA = new UserbooksJpaController(emf);

    }

}
