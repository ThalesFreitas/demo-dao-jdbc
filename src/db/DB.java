package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	//Metodo para conectar com o banco de dados
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
//Properties props = loadProperties(); => pega as propriedades do banco de dados
				Properties props = loadProperties();
//String url = props.getProperty("dburl"); => guarda a dburl que ta no arquivo db.properties
//na varial url
			String url = props.getProperty("dburl");
//conn = DriverManager.getConnection(url, props); => obtem uma conexão com banco de dados	
// passa a url do banco e as propriedades de conexão
			conn = DriverManager.getConnection(url, props);
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}
		return conn;
	}
	
	//Metodo para fechar a conexão com banco de dados
	public static void closeConnection() {
		
		if(conn != null) {
			try {
			conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		
	}
	
	
	//Metodo para carregar as propriedades que esta no arquivo db.properties
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream( "db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
		throw new DbException(e.getMessage());
	}	
}
	//metodo auxiliar para fechar Statement
			public static void closeStatement(Statement st){
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						throw new DbException(e.getMessage());
					}
					
				}
			}
			//metodo auxiliar para fechar ResultSet
			public static void closeResultSet(ResultSet rs){
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						throw new DbException(e.getMessage());
					}
					
				}
			}		
}
