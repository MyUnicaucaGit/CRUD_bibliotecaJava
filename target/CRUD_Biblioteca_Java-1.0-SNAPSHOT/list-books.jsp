<%-- 
    Document   : list-authors
    Created on : 17/07/2022, 4:15:58 p. m.
    Author     : User
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Lista de Libros</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
       <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #188494 ">
                <div>
                    <a href="<%=request.getContextPath()%>" class="navbar-brand"> Aplicación Biblioteca </a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="http://localhost:8080/CRUD_Biblioteca_Java/authors" class="nav-link">Gestion Autores</a></li>
                </ul>
                <ul class="navbar-nav">
                    <li><a href="http://localhost:8080/CRUD_Biblioteca_Java/books" class="nav-link">Gestion Libros</a></li>
                </ul>
                  <ul class="navbar-nav">
                    <li><a href="http://localhost:8080/CRUD_Biblioteca_Java/users" class="nav-link">Gestion Usuarios</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
                <h3 class="text-center">Lista de Libros</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/newBook" class="btn btn-outline-info btn-block">Nuevo Libro</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Titulo</th>                                                                    
                        </tr>
                    </thead>
                    <tbody>

                        <!-- for (Todo todo: todos) { -->
                    <c:forEach var="book" items="${listBooks}">
                        <tr>
                            <td>
                        <c:out value="${book.idB}" />
                        </td>
                        <td>

                        <c:out value="${book.title}" />
                        </td>
                                            

                        <td><a href="editBook?idB=<c:out value='${book.idB}' />">Editar</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteBook?idB=<c:out value='${book.idB}' />">Eliminar</a></td>
                        </tr>
                    </c:forEach>
                    <!-- } -->
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
