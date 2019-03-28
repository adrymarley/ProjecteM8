package classes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Form
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		//CONEXIÓ BASE DE DADES
		
		String url = "jdbc:hsqldb:hsql://localhost/Test";
        String user = "SA";
        String password = "";
        
        
        //DADES FORMULARI 
        String userForm = request.getParameter("user");
		String pass = request.getParameter("password");
		//String email = request.getParameter("email");
        try {
			Class.forName("org.hsqldb.jdbcDriver");
		
        try (Connection con = DriverManager.getConnection(url, user, password)) {
        	boolean esta= false;
        	//RECUPEREM LES DADES DE LA TAULA
        	Statement stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE USUARIO='"+userForm+"'");
            
            while(rs.next()) {              
                String usuario = rs.getString("USUARIO");
                if(userForm.equalsIgnoreCase(usuario)) {
        			esta=true;
        		}
            }	
        
    		//CONSULTA INSERT
    		if(esta==false) {
    			stmt.executeUpdate("INSERT INTO USERS (USUARIO,CONTRASEÑA)" + "VALUES ('"+userForm+"','"+pass+"')");
        		con.commit();         	
            	getServletContext().getRequestDispatcher("/JSP/oklogin.jsp").forward(request, resp);
    		}
    		if(esta==true) {
    			getServletContext().getRequestDispatcher("/JSP/errorlogin.jsp").forward(request, resp);
    		}
    	
		    
        	//System.out.println("Insertado correctamente");
        
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
	
        
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
