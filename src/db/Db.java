package db;
import java.sql.*;

public class Db {
	private Connection dbConn;
	private Statement stateMent;
	public Db() {
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=teach";
		String userName ="sa";
		String userPwd="";
		try {
			Class.forName(driverName);
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			//������ӳɹ�  ����̨��������ӳɹ���
			System.out.println("���ӳɹ�");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int executeUpdate(String sql)throws SQLException{
		stateMent=dbConn.createStatement();
		return stateMent.executeUpdate(sql);
	}
	public ResultSet executeQuery(String sql)throws SQLException{
		stateMent=dbConn.createStatement();
		return stateMent.executeQuery(sql);
	}
	public void closeConn()throws SQLException{
		stateMent.close();
		dbConn.close();
	}
	public PreparedStatement PreparedStatement(String sql) throws SQLException{
		return dbConn.prepareStatement(sql);
	}
	
	

}
