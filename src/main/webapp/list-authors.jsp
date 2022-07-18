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
        <title>Lista de Autores</title>
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
                </ul>
                <ul class="navbar-nav">
                    <li><a href="http://localhost:8080/CRUD_Biblioteca_Java/Loans" class="nav-link">Gestion Prestamos</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
                <h3 class="text-center">Lista de Autores</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/newAuthor" class="btn btn-outline-info btn-block">Nuevo Autor</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>                            
                            <th>País</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>

                        <!-- for (Todo todo: todos) { -->
                    <c:forEach var="author" items="${listAuthors}">
                        <tr>
                            <td>
                        <c:out value="${author.idA}" />
                        </td>
                        <td>

                        <c:out value="${author.name}" />
                        </td>
                        
                        <td>

                        <c:out value="${author.country}" />
                        </td>

                        <td><a href="editAuthor?idA=<c:out value='${author.idA}' />">Editar</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteAuthor?idA=<c:out value='${author.idA}' />">Eliminar</a></td>
                        </tr>
                    </c:forEach>
                    <!-- } -->
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
