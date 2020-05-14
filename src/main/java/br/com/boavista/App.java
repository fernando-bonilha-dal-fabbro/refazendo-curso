package br.com.boavista;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class App 
{
    public static void main( String[] args )    {
    	
    	Connection con = null;
    	try {
    		
    		Class.forName("org.sqlite.JDBC");
    		con = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
    		Statement sql = con.createStatement();
    		
    		sql.executeUpdate("drop table if exists clientes");
    		sql.executeUpdate("create table clientes (id integer, nome string)");
    		sql.executeUpdate("insert into clientes values(1, 'joao')");
    		sql.executeUpdate("insert into clientes values(2, 'maria')");
    		
    		ResultSet cursor = sql.executeQuery("select * from clientes");
    		
    		while (cursor.next()) {
    			System.out.println(cursor.getInt("id") + "-" + cursor.getString("nome"));	
    		
    		}
    		    		
    	}
    	catch (Exception e) {
    		System.out.println("*** Erro ao processar dados !\n" + e.getMessage());
    	}
    	finally {
    		System.out.println("Fechando Conexão");
    		if (con != null) {
    			try {
    				con.close();
    			}
    			catch (Exception e) {
    				System.out.println("*** Erro ao desconectar o banco dados !\n" + e.getMessage());
    			}
    		}
    	}
        
    }
}
