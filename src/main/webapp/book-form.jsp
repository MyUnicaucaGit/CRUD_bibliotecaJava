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
        <title>Formulario Libos</title>
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
                    <c:if test="${book != null}">
                        <form action="updateBook" method="post">
                        </c:if>

                        <c:if test="${book == null}">
                            <form action="insertBook" method="post">
                            </c:if>
                            <caption>
                                <h2>
                                    <c:if test="${book != null}">
                                        Editar Libro
                                    </c:if>

                                    <c:if test="${book == null}">
                                        Nuevo Libro
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${book != null}">
                                <fieldset class="form-group">
                                    <input type="number" hidden name="idB" value="<c:out value='${book.idB}' />" />
                                </fieldset>         
                            </c:if>
                            <fieldset class="form-group">
                                <label>Titulo</label> <input type="text" value="<c:out value='${book.title}' />"
                                                             class="form-control" name="title" required="required">
                            </fieldset>
                            <c:if test="${book == null}">
                                <fieldset class="form-group">
                                    <select class="form-control selectpicker" name="BookAuthor" >
                                        <c:forEach var="author" items="${listAuthors}">
                                            <option VALUE=${author.idA} >${author.name}                                   
                                            </c:forEach>
                                    </select>   
                                </fieldset>
                            </c:if>
                            <button type="submit" class="btn btn-success btn-block">Guardar</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>