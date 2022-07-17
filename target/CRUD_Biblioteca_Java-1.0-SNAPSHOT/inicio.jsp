<%-- 
    Document   : inicio
    Created on : 17/07/2022, 4:32:39 p. m.
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS only -->
        <link rel="stylesheet" href="index.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


        <title>Gestión biblioteca</title>
    </head>

    <body class="container">
        <div class="d-flex justify-content-center flex-column  m-5">
            <h1 class="text-center">Gestión para la biblioteca</h1>
            <h4 class="text-center">Seleccione que desa gestionar</h4>
            <a href="authors" class="btn btn-info mb-2 mt-2">Gestión de Autores</a> <br>
            <a href="./Libros/gestionLibros.php" class="btn btn-info mb-2">Gestión de Libros</a> <br>
            <a href="./Usuarios/gestionUsuarios.php" class="btn btn-info mb-2">Gestión de Usuarios</a> <br>
            <a href="./Prestamos/gestionPrestamos.php" class="btn btn-outline-info mb-2">Gestionar Prestamos</a> <br>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>

</html>