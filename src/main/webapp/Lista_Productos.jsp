<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.pildorasinformaticas.productos.*"%>

<html>
<head>
    <title>Inventario de productos</title>
    <style>
        .cabecera{
            border-bottom: solid #f00 1px;
        }
    </style>
</head>
<%
    // Obtener los productos desde el controlador (servlet)
    List<Productos> losProductos = (List<Productos>) request.getAttribute("LISTAPRODUCTOS");
%>
<body>
<table>
    <tr>
        <th class="cabecera">Código artículo</th>
        <th class="cabecera">Sección</th>
        <th class="cabecera">Nombre artículo</th>
        <th class="cabecera">Fecha</th>
        <th class="cabecera">Precio</th>
        <th class="cabecera">Importado</th>
        <th class="cabecera">País de origen</th>
    </tr>
    <% for(Productos tempProd : losProductos) { %>
        <tr>
            <td><%= tempProd.getcArt() %></td>
            <td><%= tempProd.getSeccion() %></td>
            <td><%= tempProd.getnArt() %></td>
            <td><%= tempProd.getFecha() %></td>
            <td><%= tempProd.getPrecio() %></td>
            <td><%= tempProd.getImportado() %></td>
            <td><%= tempProd.getpOrig() %></td>
        </tr>
    <% } %>
</table>
</body>
</html>
