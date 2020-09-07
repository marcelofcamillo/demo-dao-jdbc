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
	private static Connection conn = null;
	
	// m�todo para conectar o banco de dados
	public static Connection getConnection() {
		if(conn == null) { // se for nulo
			try {
				Properties props = loadProperties(); // pega as propriedades de conex�o
				String url = props.getProperty("dburl"); // pega a url do banco
				conn = DriverManager.getConnection(url, props); // obt�m a conex�o com o banco de dados e salva em conn
			} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		
		return conn;
	}
	
	// m�todo para fechar a conex�o
	public static void closeConnection() {
		if(conn != null) { // testa se a conex�o est� instanciada
			try {
				conn.close(); // fecha a conex�o
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// m�todo para carregar as propriedades do arquivo db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) { // tenta abrir o arquivo db.properties
			Properties props = new Properties();
			props.load(fs); // faz a leitura do arquivo apontado pelo fs e guarda em props
			
			return props;
		} catch(IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// m�todo para fechar o statement
	public static void closeStatement(Statement st) {
		if(st != null) { // se estiver instanciado
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// m�todo para fechar o resultset
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) { // se estiver instanciado
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
