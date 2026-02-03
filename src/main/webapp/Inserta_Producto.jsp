<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insertar Producto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; /* Fondo gris suave para la página */
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            width: 450px;
            margin: 0 auto;
            background-color: #fff; /* Fondo blanco para el formulario */
            padding: 30px;
            border-radius: 8px;     /* Bordes redondeados */
            box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Sombra elegante */
            border: 1px solid #ddd;
        }

        .campo {
            display: flex;
            justify-content: space-between;
            align-items: center; /* Alinea verticalmente label e input */
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            color: #555;
            width: 130px; /* Ancho fijo para alinear todo */
        }

        input[type="text"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 250px; /* Ancho del campo de texto */
            font-size: 14px;
        }

        /* Efecto al hacer clic en el input */
        input[type="text"]:focus {
            border-color: #66afe9;
            outline: none;
        }

        .botones {
            text-align: center;
            margin-top: 25px;
        }

        input[type="submit"], input[type="reset"] {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin: 0 5px;
            transition: background 0.3s;
        }

        input[type="submit"] {
            background-color: #4CAF50; /* Verde */
            color: white;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        input[type="reset"] {
            background-color: #f44336; /* Rojo suave */
            color: white;
        }

        input[type="reset"]:hover {
            background-color: #d32f2f;
        }

        .volver-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #555;
        }
    </style>
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