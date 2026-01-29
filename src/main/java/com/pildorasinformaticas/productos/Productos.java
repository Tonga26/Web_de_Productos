package com.pildorasinformaticas.productos;

import java.util.Date;

/**
 * Clase Modelo que representa un Producto en el sistema de gestión.
 * <p>
 * Esta clase actúa como un POJO (Plain Old Java Object) que mapea
 * una fila de la tabla 'PRODUCTOS' de la base de datos a un objeto Java.
 * </p>
 *
 * @author Gaston
 * @version 1.0
 */
public class Productos {

    /** Código único del artículo (Identificador/Primary Key). */
    private String cArt;

    /** Sección o categoría a la que pertenece el producto (ej: "Deportes", "Hogar"). */
    private String seccion;

    /** Nombre descriptivo del artículo. */
    private String nArt;

    /** Precio unitario del producto. */
    private Double precio;

    /** Fecha de alta o registro del producto. */
    private Date fecha;

    /** Indica si el producto es importado (ej: "Verdadero"/"Falso" o "Sí"/"No"). */
    private String importado;

    /** País de origen de fabricación del producto. */
    private String pOrig;

    /**
     * Constructor completo.
     * <p>
     * Se utiliza principalmente cuando recuperamos datos YA EXISTENTES de la base de datos,
     * donde el producto ya tiene un código (cArt) asignado.
     * </p>
     *
     * @param cArt      Código del artículo (ID).
     * @param seccion   Sección del producto.
     * @param nArt      Nombre del artículo.
     * @param precio    Precio del artículo.
     * @param fecha     Fecha de registro.
     * @param importado Estado de importación.
     * @param pOrig     País de origen.
     */
    public Productos(String cArt, String seccion, String nArt, Double precio, Date fecha, String importado, String pOrig) {
        this.cArt = cArt;
        this.seccion = seccion;
        this.nArt = nArt;
        this.precio = precio;
        this.fecha = fecha;
        this.importado = importado;
        this.pOrig = pOrig;
    }

    /**
     * Constructor sin ID (Código de Artículo).
     * <p>
     * Se utiliza para crear NUEVOS productos en la aplicación antes de enviarlos
     * a la base de datos (cuando el ID es autogenerado o desconocido al momento de la creación).
     * </p>
     *
     * @param seccion   Sección del producto.
     * @param nArt      Nombre del artículo.
     * @param precio    Precio del artículo.
     * @param fecha     Fecha de registro.
     * @param importado Estado de importación.
     * @param pOrig     País de origen.
     */
    public Productos(String seccion, String nArt, Double precio, Date fecha, String importado, String pOrig) {
        this.seccion = seccion;
        this.nArt = nArt;
        this.precio = precio;
        this.fecha = fecha;
        this.importado = importado;
        this.pOrig = pOrig;
    }

    /**
     * Devuelve una representación en texto del objeto Producto.
     * Útil para depuración y logs.
     *
     * @return String con todos los valores de los campos.
     */
    @Override
    public String toString() {
        return "Productos{" +
                "cArt='" + cArt + '\'' +
                ", seccion='" + seccion + '\'' +
                ", nArt='" + nArt + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", importado='" + importado + '\'' +
                ", pOrig='" + pOrig + '\'' +
                '}';
    }

    // --- GETTERS Y SETTERS ---

    /**
     * Obtiene el país de origen.
     * @return El nombre del país.
     */
    public String getpOrig() {
        return pOrig;
    }

    /**
     * Establece el país de origen.
     * @param pOrig El nombre del país a establecer.
     */
    public void setpOrig(String pOrig) {
        this.pOrig = pOrig;
    }

    /**
     * Verifica si el producto es importado.
     * @return El estado de importación como String.
     */
    public String getImportado() {
        return importado;
    }

    /**
     * Establece si el producto es importado.
     * @param importado El estado a establecer.
     */
    public void setImportado(String importado) {
        this.importado = importado;
    }

    /**
     * Obtiene la fecha de registro.
     * @return Objeto Date con la fecha.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de registro.
     * @param fecha La fecha a establecer.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio como Double.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * @param precio El precio a establecer.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del artículo.
     * @return El nombre del artículo.
     */
    public String getnArt() {
        return nArt;
    }

    /**
     * Establece el nombre del artículo.
     * @param nArt El nombre a establecer.
     */
    public void setnArt(String nArt) {
        this.nArt = nArt;
    }

    /**
     * Obtiene la sección del producto.
     * @return El nombre de la sección.
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * Establece la sección del producto.
     * @param seccion La sección a establecer.
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    /**
     * Obtiene el código del artículo.
     * @return El código único (ID).
     */
    public String getcArt() {
        return cArt;
    }

    /**
     * Establece el código del artículo.
     * @param cArt El código a establecer.
     */
    public void setcArt(String cArt) {
        this.cArt = cArt;
    }
}