<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insertar Producto</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>

<h1>Insertar Nuevo Producto</h1>

<form name="form1" method="post" action="ControladorProductos">

    <input type="hidden" name="instruccion" value="insertarBBDD">

    <div class="campo">
        <label for="codigo_articulo">Código artículo</label>
        <input type="text" name="codigo_articulo" id="codigo_articulo">
    </div>

    <div class="campo">
        <label for="seccion">Sección</label>
        <input type="text" name="seccion" id="seccion">
    </div>

    <div class="campo">
        <label for="nombre_articulo">Nombre artículo</label>
        <input type="text" name="nombre_articulo" id="nombre_articulo">
    </div>

    <div class="campo">
        <label for="fecha">Fecha</label>
        <input type="text" name="fecha" id="fecha" placeholder="dd/mm/aaaa">
    </div>

    <div class="campo">
        <label for="precio">Precio</label>
        <input type="text" name="precio" id="precio">
    </div>

    <div class="campo">
        <label for="importado">Importado</label>
        <input type="text" name="importado" id="importado">
    </div>

    <div class="campo">
        <label for="pais_origen">País de origen</label>
        <input type="text" name="pais_origen" id="pais_origen">
    </div>

    <div class="botones">
        <input type="submit" value="Enviar">
        <input type="reset" value="Borrar datos">
    </div>

</form>

<a href="ControladorProductos" class="volver-link">← Volver a la lista</a>

</body>
</html>