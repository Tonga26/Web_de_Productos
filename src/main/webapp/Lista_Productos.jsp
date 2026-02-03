<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventario de productos</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; /* Mismo fondo gris que el formulario */
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        table {
            border-collapse: collapse;
            width: 80%;
            margin: 0 auto; /* Centra la tabla horizontalmente */
            background-color: #fff; /* Fondo blanco para los datos */
            box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Sombra suave para efecto 3D */
            border-radius: 8px; /* Bordes redondeados en las esquinas de la tabla */
            overflow: hidden; /* Para que el border-radius funcione en las esquinas */
        }

        th, td {
            border: 1px solid #dddddd;
            padding: 12px 15px; /* Un poco más de aire */
            text-align: center;
        }

        th {
            background-color: #2c3e50; /* Un color oscuro elegante para la cabecera */
            color: #ffffff;
            font-weight: bold;
            text-transform: uppercase;
            font-size: 14px;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e6f7ff; /* Efecto hover azul suave */
            transition: background-color 0.3s;
        }

        /* Estilos para el contenedor del botón (centrado) */
        #contenedorBoton {
            width: 80%; /* Mismo ancho que la tabla */
            margin: 20px auto; /* Centrado */
            text-align: center; /* Alinea el botón al centro (puedes poner 'right' si prefieres) */
        }

        /* Estilo del Botón (Igual al de Insertar) */
        .boton-insertar {
            background-color: #4CAF50; /* Verde */
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: background 0.3s;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .boton-insertar:hover {
            background-color: #45a049;
            transform: translateY(-2px); /* Pequeño efecto de elevación */
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

<div id="contenedorBoton">
    <input type="button" value="Insertar Nuevo Producto" class="boton-insertar" onclick="window.location.href='Inserta_Producto.jsp'"/>
</div>

</body>
</html>