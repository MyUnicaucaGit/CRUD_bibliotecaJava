<%-- 
    Document   : author-form
    Created on : 17/07/2022, 4:29:01 p. m.
    Author     : User
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Formulario Prestamos</title>
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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">

                    <c:if test="${loan == null}">
                        <form action="insertLoan" method="post">
                        </c:if>
                        <caption>
                            <h2>
                                Nuevo Prestamo
                            </h2>
                        </caption>

                        <fieldset class="form-group">
                            <select class="form-control selectpicker" name="book_id" >
                                <c:forEach var="book" items="${listBooks}">
                                    <option VALUE=${book.idB} >${book.title}                                   
                                    </c:forEach>
                            </select>   
                        </fieldset>             
                        <fieldset class="form-group">
                            <select class="form-control selectpicker" name="user_id" >
                                <c:forEach var="user" items="${listUsers}">
                                    <option VALUE=${user.idU} >${user.name}                                   
                                    </c:forEach>
                            </select>   
                        </fieldset>
                        <button type="submit" class="btn btn-success btn-block">Guardar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>