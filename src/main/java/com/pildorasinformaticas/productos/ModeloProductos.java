package com.pildorasinformaticas.productos;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Clase que gestiona la lógica de negocio y acceso a datos (Repositorio).
 * <p>
 * Actúa como la capa de persistencia. En Spring esto sería un "Repository".
 * </p>
 *
 * @author Gaston
 * @version 1.0
 */
public class ModeloProductos {

    /** Fuente de datos (Pool de conexiones) inyectada desde el Servlet. */
    private final DataSource origenDatos;

    /**
     * Constructor del modelo.
     * <p>
     * Recibe el DataSource (Pool) para poder obtener conexiones a demanda.
     * </p>
     *
     * @param origenDatos El pool de conexiones configurado en el servidor.
     */
    public ModeloProductos(DataSource origenDatos){
        this.origenDatos = origenDatos;
    }

    // -------------------------------------------------------------------------
    // MÉTODOS CRUD (Create, Read, Update, Delete)
    // -------------------------------------------------------------------------

    /**
     * Recupera todos los registros de la tabla para el listado general.
     * <br>
     * <strong>Semántica Spring:</strong> Equivale a <code>findAll()</code>.
     *
     * <p>// Antes llamado por Juan: getProductos</p>
     *
     * @return Lista de objetos Productos.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public List<Productos> buscarTodos() throws SQLException {

        List<Productos> productos = new ArrayList<>();

        //----- 1. Crear la sentencia SQL -----
        String sentenciaSql = "SELECT * FROM PRODUCTOS";

        try (
                //----- 2. Establecer la conexión (Try-with-resources) -----
                Connection miConexion = origenDatos.getConnection();

                //----- 3. Crear el Statement -----
                Statement miStatement = miConexion.createStatement();

                // ----- 4. Ejecutar la sentencia SQL -----
                ResultSet miResultset = miStatement.executeQuery(sentenciaSql)
        ) {

            // ----- 5. Recorrer el ResultSet -----
            while (miResultset.next()){
                // Aquí SÍ necesitamos el ID para armar los links de "Actualizar" en la tabla
                String c_art = miResultset.getString("CODIGOARTICULO");
                String seccion = miResultset.getString("SECCION");
                String n_art = miResultset.getString("NOMBREARTICULO");
                Double precio = miResultset.getDouble("PRECIO");
                Date fecha = miResultset.getDate("FECHA");
                String importado = miResultset.getString("IMPORTADO");
                String p_orig = miResultset.getString("PAISDEORIGEN");

                //----- 6. Crear el objeto COMPLETO (Con ID) para la lista -----
                Productos tempProd = new Productos(c_art, seccion, n_art, precio, fecha, importado, p_orig);

                //----- 7. Agregarlo a la lista -----
                productos.add(tempProd);
            }
        }
        return productos;
    }

    /**
     * Persiste un nuevo objeto en la base de datos.
     * <br>
     * <strong>Semántica Spring:</strong> Equivale a <code>save()</code> (modo inserción).
     *
     * <p>// Antes llamado por Juan: agregarElNuevoProducto</p>
     *
     * @param nuevoProducto El objeto a guardar.
     * @throws SQLException Si falla la BBDD.
     */
    public void guardar(Productos nuevoProducto) throws SQLException {

        //----- 1. SQL Insertar -----
        String sql = "INSERT INTO PRODUCTOS (CODIGOARTICULO, SECCION, NOMBREARTICULO, PRECIO, FECHA, IMPORTADO, PAISDEORIGEN)" +
                "VALUES(?,?,?,?,?,?,?)";

        try (
                Connection miConexion = origenDatos.getConnection();
                PreparedStatement miStatement = miConexion.prepareStatement(sql)
        ) {
            //----- 2. Establecer parámetros -----
            miStatement.setString(1, nuevoProducto.getcArt());
            miStatement.setString(2, nuevoProducto.getSeccion());
            miStatement.setString(3, nuevoProducto.getnArt());
            miStatement.setDouble(4, nuevoProducto.getPrecio());

            // Tratamiento especial para fechas (java.util.Date -> java.sql.Date)
            java.util.Date utilDate = nuevoProducto.getFecha();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            miStatement.setDate(5, fechaConvertida);

            miStatement.setString(6, nuevoProducto.getImportado());
            miStatement.setString(7, nuevoProducto.getpOrig());

            //----- 3. Ejecutar -----
            miStatement.execute();
        }
    }

    /**
     * Busca una entidad única por su clave primaria para rellenar el formulario de edición.
     * <br>
     * No devuelve el ID dentro del objeto porque ya lo tenemos.
     * <br>
     * <strong>Semántica Spring:</strong> Equivale a <code>findById(id)</code>.
     *
     * <p>// Antes llamado por Juan: getProducto</p>
     *
     * @param codArticulo El ID del producto a buscar.
     * @return El objeto Producto (SIN el ID seteado).
     * @throws Exception Si no se encuentra.
     */
    public Productos buscarPorId(String codArticulo) throws Exception {
        Productos elProducto = null;

        //----- 1. SQL Buscar por ID -----
        String sentenciaSql = "SELECT * FROM PRODUCTOS WHERE CODIGOARTICULO = ?";

        try (
                Connection miConexion = origenDatos.getConnection();
                PreparedStatement miStatement = miConexion.prepareStatement(sentenciaSql)
        ) {
            //----- 2. Establecer parámetro ID -----
            miStatement.setString(1, codArticulo);

            //----- 3. Ejecutar y obtener resultados -----
            try (ResultSet miResultset = miStatement.executeQuery()) {

                if (miResultset.next()) {
                    String c_Art = miResultset.getString("CODIGOARTICULO");
                    String seccion = miResultset.getString("SECCION");
                    String n_art = miResultset.getString("NOMBREARTICULO");
                    Double precio = miResultset.getDouble("PRECIO");
                    Date fecha = miResultset.getDate("FECHA");
                    String importado = miResultset.getString("IMPORTADO");
                    String p_orig = miResultset.getString("PAISDEORIGEN");

                    //----- 4. Crear objeto con Constructor completo -----
                    elProducto = new Productos(c_Art, seccion, n_art, precio, fecha, importado, p_orig);

                } else {
                    throw new Exception("No hemos encontrado el producto con código articulo: " + codArticulo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return elProducto;
    }

    /**
     * Actualiza los datos de un registro existente en la base de datos.
     * <br>
     * <strong>Semántica Spring:</strong> Equivale a <code>save()</code> cuando la entidad ya tiene ID (modo actualización).
     *
     * @param productoActualizado Objeto que contiene los nuevos datos y el ID (cArt) para el WHERE.
     * @throws SQLException Si ocurre un error al ejecutar la sentencia UPDATE.
     */
    public void actualizarProducto(Productos productoActualizado) throws SQLException{

        //----- 1. Creamos la sentencia sql -----
        String sql = "UPDATE productos SET SECCION=?, NOMBREARTICULO=?, PRECIO=?, FECHA=?, " +
                "IMPORTADO=?, PAISDEORIGEN=? WHERE CODIGOARTICULO=?";

        try (
                //----- 2. Establecemos la conexion con la BBDD -----
                Connection miConexion = origenDatos.getConnection();
                //----- 3. Creamos la consulta preparada -----
                PreparedStatement miStatement = miConexion.prepareStatement(sql)
        ) {
            //----- 4. Establecemos los parametros -----
            miStatement.setString(1, productoActualizado.getSeccion());
            miStatement.setString(2, productoActualizado.getnArt());
            miStatement.setDouble(3, productoActualizado.getPrecio());

            // Tratamiento especial para fechas (java.util.Date -> java.sql.Date)
            java.util.Date utilDate = productoActualizado.getFecha();
            java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
            miStatement.setDate(4, fechaConvertida);

            miStatement.setString(5, productoActualizado.getImportado());
            miStatement.setString(6, productoActualizado.getpOrig());
            miStatement.setString(7, productoActualizado.getcArt());

            //----- 5. Ejecutamos la instruccion sql -----
            miStatement.execute();
        }
    }
}