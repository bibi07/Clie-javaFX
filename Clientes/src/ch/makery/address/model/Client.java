package ch.makery.address.model;

import java.util.Date;
import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.makery.address.util.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Client {


	private final StringProperty Nombre;
    private final StringProperty Apellidos;
    private int NoVentas;
    private final StringProperty Direccion;
    //private final IntegerProperty Ciudad;
    private final StringProperty Ciudad;
    private final StringProperty CodigoPostal;
    private Date Fecha;

    /**
     * Default constructor.
     */
    public Client() {
        this(null, null,null,null,null,null, 0);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param Nombre
     * @param Apellidos
     */
    public Client(String nombre, String apellidos, String direccion, String ciudad,String CodigoPostal, Date Fecha, int NoVentas) {
        this.Nombre = new SimpleStringProperty(nombre);
        this.Apellidos = new SimpleStringProperty(apellidos);

        // Some initial dummy data, just for convenient testing.
        this.NoVentas = NoVentas;
        this.Direccion = new SimpleStringProperty(direccion);
        //this.Precio1 = new SimpleIntegerProperty();
        this.Ciudad = new SimpleStringProperty(ciudad);
        this.CodigoPostal = new SimpleStringProperty(CodigoPostal);
       this.Fecha = Fecha;
       
    }

    public String getNombre() {
        return Nombre.get();
    }

    public void setNombre(String nombre) {
        this.Nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.Apellidos.set(apellidos);
    }

    public StringProperty apellidosProperty() {
        return Apellidos;
    }
    
    public int getNoVentas() {
        return NoVentas;
    }

    public void setNoVentas(int noVentas) {
        this.NoVentas = noVentas;
    }

    

    public String getDireccion() {
        return Direccion.get();
    }

    public void setDireccion(String direccion) {
        this.Direccion.set(direccion);
    }

    public StringProperty direccionProperty() {
        return Direccion;
    }
    
    

    /*public int getPrecio1() {
        return Precio1.get();
    }

    public void setPrecio1(int precio1) {
        this.Precio1.set(precio1);
    }

    public IntegerProperty precio1Property() {
        return Precio1;
    }*/
    
    
    
    public String getCiudad() {
        return Ciudad.get();
    }

    public void setCiudad(String ciudad) {
        this.Ciudad.set(ciudad);
    }

    public StringProperty ciudadProperty() {
        return Ciudad;
    }

    
    
    public String getCodigoPostal() {
        return CodigoPostal.get();
    }

    public void settCodigoPostal(String codigopostal) {
        this.CodigoPostal.set(codigopostal);
    }

    public StringProperty codigoPostalProperty() {
        return CodigoPostal;
    }
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date date) {
        this.Fecha=date;
    }

    public Date fechaProperty() {
        return Fecha;
    }
}