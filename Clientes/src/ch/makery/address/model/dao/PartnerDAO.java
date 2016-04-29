package ch.makery.address.model.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.makery.address.model.dto.PartnerDTO;
import ch.makery.address.util.DatabaseGateway;
public class PartnerDAO {

	private DatabaseGateway db;
	
	
	
	public PartnerDAO(){
		if(this.db == null)
			this.db = new DatabaseGateway();
		
	}
	
	public List<PartnerDTO> SelecceccionarTodosPartner() throws SQLException{
		PartnerDTO partner = null;
		List<PartnerDTO> listaPartners = new ArrayList<PartnerDTO>();
		String query = "SELECT * FROM clientes"; 
		ResultSet row = null;
		row = db.executeQuery(query);
		while(row.next()){
			partner = new PartnerDTO();
			partner.setNombre(row.getString("nombre"));
			partner.setApellidos(row.getString("apellidos"));
			partner.setDireccion(row.getString("direccion"));
			partner.setCiudad(row.getString("ciudad"));
			partner.setCodigoPostal(row.getString("codigopos"));
			partner.setFecha(row.getDate("fecha_registro"));
			
			partner.setNoVentas(row.getInt("idventas"));
			listaPartners.add(partner);
		}		
		
		return listaPartners;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
