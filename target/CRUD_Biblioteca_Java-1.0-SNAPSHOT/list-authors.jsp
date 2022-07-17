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
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="<%=request.getContextPath()%>" class="navbar-brand"> Aplicación Biblioteca </a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista Clientss</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
                <h3 class="text-center">Lista de Clientes</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Nuevo Cliente</a>
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

                        <td><a href="edit?idA=<c:out value='${author.idA}' />">Editar</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?idA=<c:out value='${author.idA}' />">Eliminar</a></td>
                        </tr>
                    </c:forEach>
                    <!-- } -->
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
