<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.pildorasinformaticas.productos.*"%>

<html>
<head>
    <title>Title</title>
</head>
<%
    // Obtener los productos desde el controlador (servlet)
    List<Productos> losProductos = (List<Productos>) request.getAttribute("LISTAPRODUCTOS");
%>
<body>
<%= losProductos %>
</body>
</html>
