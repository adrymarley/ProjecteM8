package classes;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class ConexionBDD {

	    public static void main(String[] args) throws SQLException {
	        
	        String url = "jdbc:hsqldb:hsql://localhost/Test";
	        String user = "SA";
	        String password = "";
	     
	        try (Connection con = DriverManager.getConnection(url, user, password)) {
	       
	            Statement stmt = con.createStatement();
	            //SENTENCIA SELECT
	            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
	            
	            while(rs.next()) {
	                
	                //Long id = rs.getLong("ID");
	                String usuario = rs.getString("USUARIO");
	                String contraseña = rs.getString("CONTRASEÑA");
	              
	                
	               String out = String.format("%s, %s", usuario, contraseña);
	                
	               System.out.println(out);
	            }	
	            /*
	            Statement stmt2 = con.createStatement();
	            //Consulta CREATE TABLE
	            
	            stmt2.executeQuery("CREATE TABLE Users (" + 
	            		"   Usuario VARCHAR(50) NOT NULL," + 
	            		"   Contraseña VARCHAR(20) NOT NULL," + 
	            		"   PRIMARY KEY (Usuario) " + 
	            		");");
	            System.out.println("-----SE HA CREADO LA BASE DE DATOS USUARIOS-----");
	            */
	            
	            Statement stmt3 = con.createStatement();
	            
	            //CONSULTA INSERT
	            stmt3.executeUpdate("INSERT INTO USERS (USUARIO,CONTRASEÑA)" + 
	            		"VALUES ('XAVIER','12345666')");
	            con.commit();
	            System.out.println("Insertado correctamente");
	            
	            
	            
	        }
	    }
	}

