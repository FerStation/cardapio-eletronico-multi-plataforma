package BancoDeDados;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * 
 */
public class CriaConexao {
    
    public static java.sql.Connection getConexao() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
                        return DriverManager.getConnection("jdbc:mysql://localhost/minister","root","");
		} catch (ClassNotFoundException e){
			throw new SQLException (e.getMessage());
		}
	}
    
}
