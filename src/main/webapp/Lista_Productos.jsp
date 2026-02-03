<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventario de productos</title>

    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
            font-family: Arial, sans-serif;
        }

        th, td {
            border: 1px solid #dddddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e6f7ff;
        }

        h1 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>

<h1>Lista de Productos</h1>

<table>
    <tr>
        <th>Código</th>
        <th>Sección</th>
        <th>Nombre</th>
        <th>Fecha</th>
        <th>Precio</th>
        <th>Importado</th>
        <th>País</th>
    </tr>

    <c:forEach var="tempProd" items="${LISTAPRODUCTOS}">
        <tr>
            <td>${tempProd.cArt}</td>
            <td>${tempProd.seccion}</td>
            <td>${tempProd.nArt}</td>
            <td>${tempProd.fecha}</td>
            <td>${tempProd.precio}</td>
            <td>${tempProd.importado}</td>
            <td>${tempProd.pOrig}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>