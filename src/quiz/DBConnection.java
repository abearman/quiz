package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** 
 * DBConnection class handles creating and closing the connection.
 */
public class DBConnection {

	private Statement stmt;
	private Connection con;
	
	/** 
	 * Constructor connects to the database.
	 */
	public DBConnection (){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			//stmt.executeUpdate("SOURCE DBTables.sql;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	/** 
	 * Returns a statement so that product catalog can do querying.
	 */
	public Statement getStatement(){
		return stmt;
	}
	
	/** 
	 * Closes the connection.
	 */
	public void closeConnection(){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

