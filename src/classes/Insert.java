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
import java.util.logging.Level;
import java.util.logging.Logger;

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
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean esta= false;
       	
        try {
        		con = DriverManager.getConnection(url, user, password);
        		
        		Class.forName("org.hsqldb.jdbcDriver");
        		
        		stmt = con.createStatement();
        		rs = stmt.executeQuery("SELECT * FROM USERS WHERE USUARIO='"+userForm+"'");
        		
        		//RECUPEREM LES DADES DE LA TAULA
        	        
        		while(rs.next()) {              
        			String usuario = rs.getString("USUARIO");
        			if(userForm.equalsIgnoreCase(usuario)) {
        				esta=true;
        			}
        		}	 
        		//CONSULTA INSERT
        		if(!esta) {
        			stmt.executeUpdate("INSERT INTO USERS (USUARIO,CONTRASEÑA)" + "VALUES ('"+userForm+"','"+pass+"')");
        			con.commit();         	
        			getServletContext().getRequestDispatcher("/JSP/oklogin.jsp").forward(request, resp);
        		}else {
        			getServletContext().getRequestDispatcher("/JSP/errorlogin.jsp").forward(request, resp);
        		}      
        } catch (SQLException e) {
        	Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE, "Mensaje crítico...", e);
        } catch (ClassNotFoundException e1) {
        	Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE, "Mensaje crítico...", e1);
        } finally {
        	if (stmt != null) {
                try {
                	stmt.close();
                } catch (SQLException e) {  }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {  }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {  }
            }
        }
	}	



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
