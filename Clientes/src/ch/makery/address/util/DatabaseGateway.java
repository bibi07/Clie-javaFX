package ch.makery.address.util;

import java.sql.*;

import org.h2.result.Row;
import org.h2.tools.DeleteDbFiles;


public class DatabaseGateway {
	
	/**
	 * 
	 * Variables
	 */
	private Connection conn;
	private String driver;
	private String dataBaseName;
	
	public DatabaseGateway(){
		
		this.dataBaseName = "test";
		this.driver = "org.h2.Driver";		
		
		if(this.conn == null){
			openDatabase();
		}
	}
	
	public void openDatabase(){	
		try {
			if(this.conn == null){			
			
				Class.forName(driver);
			     conn = DriverManager.getConnection("jdbc:h2:~/"+dataBaseName);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeDatabase(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void eraseDatabase(){
		// delete the database named 'test' in the user home directory		 
		DeleteDbFiles.execute("~", dataBaseName, true);    
	}
	
	public void CrearBD() throws ClassNotFoundException, SQLException{
	
             Statement stat = conn.createStatement();

         // this line would initialize the database
         // from the SQL script file 'init.sql'
         // stat.execute("runscript from 'init.sql'");
         
        //stat.execute("create table test(id int primary key, name varchar(255))");//crea tabla nueva en: jdbc:h2:~/test
       // stat.execute("insert into test values(1, 'Hola!')");//inserta en tabla test fila 1
        //stat.execute("insert into test values(2, 'Mundo!')");//inserta en tabla test fila 1
       
        
        
        
        stat.execute("CREATE TABLE IF NOT EXISTS clientes(idventas INT PRIMARY KEY AUTO_INCREMENT NOT NULL, nombre VARCHAR(50) DEFAULT NULL, apellidos VARCHAR(255) DEFAULT NULL, direccion VARCHAR(50) DEFAULT NULL, ciudad VARCHAR(50) DEFAULT NULL, codigopos VARCHAR(20) DEFAULT NULL, fecha_registro date  DEFAULT NULL )");//crea tabla nueva en: jdbc:h2:~/test
       //stat.execute("INSERT INTO clientes(idventas,nombre,apellidos,direccion,ciudad, codigopos, fecha_registro) VALUES (2,'David','Camarillo', '5 de mayo no.10','Queretaro', 980354,  '2016-03-16')");
       // stat.execute("INSERT INTO clientes(idventas,nombre,apellidos,direccion,ciudad, codigopos, fecha_registro) VALUES (3,'Hector','Rodrigues Flores', 'quinta calle','DF', 90445454,  '2013-09-16')");
       // stat.execute("INSERT INTO clientes(idventas,nombre,apellidos,direccion,ciudad, codigopos, fecha_registro) VALUES (4, 'Javier','Brito Pacheco', 'pascuaro','Hermosillo', 158937, '2014-06-16')");//inserta en tabla test fila 1
        //stat.execute("insert into test values(2, 'Mundo!')");//inserta en tabla test fila 1
         
        //(1,'Bernado','Buriostigui Ruiz', 'lomas','mexico', 1245454,  '2016-02-1')
        
       
        
        
        ResultSet rs;//variable usada para buscar datos
         rs = stat.executeQuery("select * from clientes");//la variable es una busqueda en la tabla
         
         while (rs.next()) {// mientras haya pa leer 
        	 System.out.println(rs.getInt("idventas"));
             System.out.println(rs.getString("nombre"));
             System.out.println(rs.getString("apellidos"));
             System.out.println(rs.getString("direccion"));//imprime lo que haya en la tabla
             System.out.println(rs.getString("ciudad"));
             System.out.println(rs.getString("codigopos"));
             System.out.println(rs.getString("fecha_registro"));
             
            /*rs.getInt("idventas_columna");
            rs.getInt("nombre_columna");
            rs.getInt("apellidos_columna");
            rs.getInt("direccion_columna");
            rs.getInt("ciudad_columna");
            rs.getInt("codigopos_columna");
            rs.getInt("fecha_registro_columna");*/
         }
	}
	
	public ResultSet executeQuery(String query){
		ResultSet rs = null;
		Statement stat = null;
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stat.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return rs;
		
	
	}
     
}
