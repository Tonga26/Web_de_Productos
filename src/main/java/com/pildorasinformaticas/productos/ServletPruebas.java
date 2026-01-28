package com.pildorasinformaticas.productos;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.sql.DataSource;
import javax.servlet.ServletException;

@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {

    // Establecer el DataSource
    @Resource(name="jdbc/Productos")
    private DataSource miPool;

    public ServletPruebas(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Crear el objeto PrintWriter
        PrintWriter salida = response.getWriter();
        response.setContentType("text/plain");

        // Crear conexion con BBDD
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultSet = null;

        try{
            miConexion = miPool.getConnection();

            String miSql = "SELECT * FROM productos";

            miStatement = miConexion.createStatement();

            miResultSet = miStatement.executeQuery(miSql);

            while (miResultSet.next()){
                String nombre_articulo = miResultSet.getString(3);
                salida.println(nombre_articulo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}