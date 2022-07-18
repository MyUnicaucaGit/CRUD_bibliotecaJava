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
        <title>Lista de Prestamos</title>
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
                <h3 class="text-center">Lista de Prestamos</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/newLoan" class="btn btn-outline-info btn-block">Nuevo Prestamo</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Titulo</th>
                            <th>Fecha de prestamo</th>
                            <th>Fecha de vencimiento de prestamo</th>
                        </tr>
                    </thead>
                    <tbody>

                        <!-- for (Todo todo: todos) { -->
                        <c:forEach var="loan" items="${loans}">
                            <tr>
                                <td>
                                    <c:out value="${loan.users.name}" />
                                </td>
                                <td>

                                    <c:out value="${loan.books.title}" />
                                </td>
                                <td>

                                    <c:out value="${loan.userbooksPK.loanDate}" />
                                </td>
                                <td>

                                    <c:out value="${loan.exDate}" />
                                </td>


                            </c:forEach>
                            <!-- } -->
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Toast Exito -->
        <div class="position-fixed bottom-0 right-0 p-3" style="z-index: 5; right: 0; bottom: 0;">
            <div id="toastExito" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="3000">
                <div class="toast-header">
                    <strong class="mr-auto">Biblioteca</strong>
                    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="toast-body">
                    Acción realizada con exito
                </div>
            </div>
        </div>
        <!-- End Toast Exito -->
        <!-- Toast Fallo -->
        <div class="position-fixed bottom-0 right-0 p-3" style="z-index: 5; right: 0; bottom: 0;">
            <div id="toastFallo" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="3000">
                <div class="toast-header">
                    <strong class="mr-auto">Biblioteca</strong>
                    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="toast-body">
                    No se pudo realizar esa acción
                </div>
            </div>
        </div>
        <!-- End Toast Fallo -->


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


        <c:if test="${success==true}">
            <script>    $('#toastExito').toast('show');</script>
        </c:if>
            <c:if test="${success == false}">
            <script>    $('#toastFallo').toast('show');</script>
        </c:if>

    </body>
</html>
