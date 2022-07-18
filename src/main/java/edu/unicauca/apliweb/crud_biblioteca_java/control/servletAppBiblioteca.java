/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.unicauca.apliweb.crud_biblioteca_java.control;

import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Authors;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Books;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Userbooks;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Users;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.AuthorsJpaController;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.BooksJpaController;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.UserbooksJpaController;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.UsersJpaController;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.IllegalOrphanException;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                case "/authors": //Ir a la lista de autores
                    listAuthors(request, response);
                    break;
                case "/newAuthor": //Muestra el formulario para crear un nuevo autor
                    showNewFormAuthors(request, response);
                    break;
                case "/insertAuthor": //ejecuta la creación de un nuevo autor en la BD 
                    insertAuthor(request, response);
                    break;
                case "/deleteAuthor": //Ejecuta la eliminación de un autor de la BD
                    deleteAuthor(request, response);
                    break;
                case "/editAuthor": //Muestra el formulario para editar un autor
                    showEditFormAuthors(request, response);
                    break;
                case "/updateAuthor": //Ejecuta la edición de un autor de la BD
                    updateAuthor(request, response);
                    break;
                
                case "/books": //Ir a la lista de libros
                    listBooks(request, response);
                    break;
                case "/newBook": //Muestra el formulario para crear un nuevo libro
                    showNewFormBooks(request, response);
                    break;
                case "/insertBook": //Ir a la lista de libros
                    insertBook(request, response);
                    break;
                case "/deleteBook": //Ejecuta la eliminación de un libro de la BD
                    deleteBook(request, response);
                    break;
                case "/editBook": //Muestra el formulario para editar un libro
                    showEditFormBooks(request, response);
                    break;
                case "/updateBook": //Ejecuta la edición de un libro de la BD
                    updateBook(request, response);
                    break;
                
                case "/users": //Ir a la lista de usuarios
                    listUsers(request, response);
                    break;
                case "/newUser": //Muestra el formulario para crear un nuevo usuario
                    showNewFormUsers(request, response);
                    break;
                case "/insertUser": //Ir a la lista de usuarios
                    insertUser(request, response);
                    break;
                case "/deleteUser": //Ejecuta la eliminación de un usuario de la BD
                    deleteUser(request, response);
                    break;
                case "/editUser": //Muestra el formulario para editar un usuario
                    showEditFormUsers(request, response);
                    break;
                case "/updateUser": //Ejecuta la edición de un usuario de la BD
                    updateUser(request, response);
                    break;
                case "/Loans": //Ir a la lista de usuarios
                    listLoans(request, response);
                    break;
                case "/newLoan": //Muestra el formulario para crear un nuevo usuario
                    showNewFormLoans(request, response);
                    break;
                case "/insertLoan": //Ir a la lista de usuarios
                    insertLoan(request, response);
                    break;
                
                default:
                    inicio(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void inicio(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("inicio.jsp");
        
        dispatcher.forward(request, response);
    }
    
    private void listAuthors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Authors> listAuthors = authorsJPA.findAuthorsEntities();
        request.setAttribute("listAuthors", listAuthors);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-authors.jsp");
        
        dispatcher.forward(request, response);
    }

    //muestra el formulario para crear un nuevo autor
    private void showNewFormAuthors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("author-form.jsp");
        
        dispatcher.forward(request, response);
    }
    
    private void showEditFormAuthors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        //toma el id del autor a ser editaro
        int id = Integer.parseInt(request.getParameter("idA"));
        //busca al autor en la base de datos
        Authors existingAuthor = authorsJPA.findAuthors(id);
        RequestDispatcher dispatcher = null;
        if (existingAuthor != null) {
            //si lo encuentra lo envía al formulario
            dispatcher = request.getRequestDispatcher("author-form.jsp");
            request.setAttribute("author", existingAuthor);
        } else {
            //si no lo encuentra regresa a la página con la lista de los autores 
            dispatcher = request.getRequestDispatcher("list-authors.jsp");
        }
        dispatcher.forward(request, response);
    }

    //método para crear un autor en la base de datos
    private void insertAuthor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos del formulario de autores
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        //crea un objeto de tipo Authors vacío y lo llena con los datos obtenidos 
        Authors aut = new Authors();
        aut.setName(name);
        aut.setCountry(country);
        //Crea el autor utilizando el objeto controlador JPA
        authorsJPA.create(aut);
        //solicita al Servlet que muestre la página actualizada con la lista de autores
        response.sendRedirect("authors");
    }

    //Método para editar un autor
    private void updateAuthor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos enviados por el formulario de autores
        int id_author = Integer.parseInt(request.getParameter("idA"));
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        //crea un objeto vacío y lo llena con los datos del autores
        Authors aut = new Authors();
        aut.setIdA(id_author);
        aut.setName(name);
        aut.setCountry(country);
        try {
            //Edita el autor en la BD
            authorsJPA.edit(aut);
        } catch (Exception ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("authors");
    }

    //Elimina un autor de la BD
    private void deleteAuthor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //Recibe el ID del autor que se espera eliminar de la BD
        int id = Integer.parseInt(request.getParameter("idA"));
        try {
            //Elimina el autor con el id indicado
            authorsJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("authors");
    }
    
    private void listBooks(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Books> listBooks = booksJPA.findBooksEntities();
        request.setAttribute("listBooks", listBooks);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-books.jsp");
        
        dispatcher.forward(request, response);
    }

    //muestra el formulario para crear un nuevo libro
    private void showNewFormBooks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Authors> listAuthors = authorsJPA.findAuthorsEntities();
        request.setAttribute("listAuthors", listAuthors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        
        dispatcher.forward(request, response);
    }
    
    private void showEditFormBooks(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        //toma el id del libro a ser editaro
        int id = Integer.parseInt(request.getParameter("idB"));
        //busca al libro en la base de datos
        Books existingBook = booksJPA.findBooks(id);
        RequestDispatcher dispatcher = null;
        if (existingBook != null) {
            //si lo encuentra lo envía al formulario
            dispatcher = request.getRequestDispatcher("book-form.jsp");
            request.setAttribute("book", existingBook);
        } else {
            //si no lo encuentra regresa a la página con la lista de los libros 
            dispatcher = request.getRequestDispatcher("list-books.jsp");
        }
        dispatcher.forward(request, response);
    }

    //método para crear un autor en la base de datos
    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos del formulario de libros
        String title = request.getParameter("title");
        
        int idA = Integer.parseInt(request.getParameter("BookAuthor"));
        Authors aut = authorsJPA.findAuthors(idA);
        List<Authors> autList = new ArrayList();
        autList.add(aut);
        //aut.add(authorsJPA.findAuthors(idA));
        //crea un objeto de tipo Books vacío y lo llena con los datos obtenidos 
        Books book = new Books();
        book.setTitle(title);
        book.setAuthorsList(autList);

        //Crea el libro utilizando el objeto controlador JPA
        booksJPA.create(book);
        //solicita al Servlet que muestre la página actualizada con la lista de libros
        response.sendRedirect("books");
    }
    
    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos enviados por el formulario de libros
        int id_book = Integer.parseInt(request.getParameter("idB"));
        String title = request.getParameter("title");

        //crea un objeto vacío y lo llena con los datos del libro
        Books book = new Books();
        book.setIdB(id_book);
        book.setTitle(title);
        
        try {
            //Edita el libro en la BD
            booksJPA.edit(book);
        } catch (Exception ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("books");
    }

    //Elimina un libro de la BD
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //Recibe el ID del libro que se espera eliminar de la BD
        int id = Integer.parseInt(request.getParameter("idB"));
        try {
            booksJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("books");
    }
    
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Users> listUsers = usersJPA.findUsersEntities();
        request.setAttribute("listUsers", listUsers);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-users.jsp");
        
        dispatcher.forward(request, response);
    }

    //muestra el formulario para crear un nuevo usuario
    private void showNewFormUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        
        dispatcher.forward(request, response);
    }
    
    private void showEditFormUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        //toma el id del usuario a ser editaro
        int id = Integer.parseInt(request.getParameter("idU"));
        //busca al usuario en la base de datos
        Users existingUser = usersJPA.findUsers(id);
        RequestDispatcher dispatcher = null;
        if (existingUser != null) {
            //si lo encuentra lo envía al formulario
            dispatcher = request.getRequestDispatcher("user-form.jsp");
            request.setAttribute("user", existingUser);
        } else {
            //si no lo encuentra regresa a la página con la lista de los usuarios 
            dispatcher = request.getRequestDispatcher("list-users.jsp");
        }
        dispatcher.forward(request, response);
    }

    //método para crear un autor en la base de datos
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos del formulario de autores
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        //crea un objeto de tipo Authors vacío y lo llena con los datos obtenidos 
        Users us = new Users();
        us.setName(name);
        us.setEmail(email);
        //Crea el autor utilizando el objeto controlador JPA
        usersJPA.create(us);
        //solicita al Servlet que muestre la página actualizada con la lista de autores
        response.sendRedirect("users");
    }

    //Método para editar un usuario
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos enviados por el formulario de usuarios
        int id_user = Integer.parseInt(request.getParameter("idU"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        //crea un objeto vacío y lo llena con los datos del usuario
        Users us = new Users();
        us.setIdU(id_user);
        us.setName(name);
        us.setEmail(email);
        try {
            //Edita el autor en la BD
            usersJPA.edit(us);
        } catch (Exception ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("users");
    }

    //Elimina un autor de la BD
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //Recibe el ID del autor que se espera eliminar de la BD
        int id = Integer.parseInt(request.getParameter("idU"));
        try {
            //Elimina el autor con el id indicado
            usersJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("users");
    }
    
    private void listLoans(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Userbooks> loans = userbooksJPA.findUserbooksEntities();
        request.setAttribute("loans", loans);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-loans.jsp");
        
        dispatcher.forward(request, response);
    }

    //muestra el formulario para crear un nuevo usuario
    private void showNewFormLoans(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Users> listUsers = usersJPA.findUsersEntities();
        request.setAttribute("listUsers", listUsers);
        List<Books> listBooks = booksJPA.findBooksEntities();
        request.setAttribute("listBooks", listBooks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loan-form.jsp");
        
        dispatcher.forward(request, response);
    }

    //método para crear un autor en la base de datos
    private void insertLoan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //toma los datos del formulario de autores
        int idU = Integer.parseInt(request.getParameter("user_id"));
        int idB = Integer.parseInt(request.getParameter("book_id"));
        Users u = usersJPA.findUsers(idU);
        Books b = booksJPA.findBooks(idB);

        //crea un objeto de tipo Authors vacío y lo llena con los datos obtenidos 
        Userbooks usB = new Userbooks();
        usB.setUsers(u);
        usB.setBooks(b);
        Date time = new Date();
        time.setMonth(time.getMonth()+1);
        usB.setExDate(time);
        try {
            //usB.setBooks(title);
            //Crea el autor utilizando el objeto controlador JPA
            userbooksJPA.create(usB);
        } catch (Exception ex) {
            Logger.getLogger(servletAppBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        //solicita al Servlet que muestre la página actualizada con la lista de autores
        response.sendRedirect("Loans");
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
