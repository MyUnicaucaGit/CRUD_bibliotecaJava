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
        <title>Formulario Autores</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
           <<nav class="navbar navbar-expand-md navbar-dark" style="background-color: #188494 ">
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
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${author != null}">
                        <form action="updateAuthor" method="post">
                        </c:if>

                        <c:if test="${author == null}">
                            <form action="insertAuthor" method="post">
                            </c:if>
                            <caption>
                                <h2>
                                    <c:if test="${author != null}">
                                        Editar Autor
                                    </c:if>

                                    <c:if test="${author == null}">
                                        Nuevo Autor
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${author != null}">
                                <fieldset class="form-group">
                                    <input type="number" hidden name="idA" value="<c:out value='${author.idA}' />" />
                                </fieldset>         
                            </c:if>
                            <fieldset class="form-group">
                                <label>Nombre</label> <input type="text" value="<c:out value='${author.name}' />"
                                                             class="form-control" name="name" required="required">
                            </fieldset>                            
                            <fieldset class="form-group">
                                <label>País</label> <input type="text" value="<c:out value='${author.country}'
                                       />" class="form-control" name="country">
                            </fieldset>
                            <button type="submit" class="btn btn-success btn-block">Guardar</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>