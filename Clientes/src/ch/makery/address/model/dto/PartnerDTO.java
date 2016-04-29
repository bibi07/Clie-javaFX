package ch.makery.address.model.dto;

public class PartnerDTO {
	/**
	 * 
	 * variables
	 */
	
	private String nombre;
	private String apellidos;
	private String direccion;
	private String ciudad;
    private String CodigoPostal;
    private java.sql.Date Fecha;
    private int NoVentas;
    
    
    /*
	 *Constructor sin parametros 
	 * 
	 */
	public PartnerDTO(){
		
	}
		
	
	/*
	 * 
	 * Encapsulamiento
	 * 
	 */

	
	/**
	 * @return the idventas
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param idventas the idventas to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    
    
    /**
	 * @return the fecha
	 */
	public java.sql.Date getFecha() {
		return Fecha;
	}


	/**
	 * @param date the fecha to set
	 */
	public void setFecha(java.sql.Date date) {
		Fecha = date;
	}


	/**
	 * @return the noVentas
	 */
	public int getNoVentas() {
		return NoVentas;
	}


	/**
	 * @param noventas the noVentas to set
	 */
	public void setNoVentas(int noventas) {
		NoVentas = noventas;
	}


	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return CodigoPostal;
	}


	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
	}


	

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}


	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}


	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	/**
	 * @return the nombre
	 */
	public String getApellidos() {
		return apellidos;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	
	/*
	 * ETC..
	 * 
	 */


	
	
	
	
	
}
