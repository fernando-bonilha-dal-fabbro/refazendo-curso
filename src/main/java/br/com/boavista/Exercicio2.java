package br.com.boavista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exercicio2 {

	public static void main(String[] args) {

		System.out.println("Programa inicializando...");
		Connection con = null;
		try {
			
    		Class.forName("org.sqlite.JDBC");
    		con = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
    		Statement sql = con.createStatement();

    		sql.executeUpdate("drop table if exists clientes");
    		sql.executeUpdate("create table clientes (" +  
    				          "id string, " +
    				          "nome string, " +
    				          "cpf string, " +
    				          "endereco string, " +
    				          "bairro string, " +
    				          "cidade string, " +
    				          "uf string, " +
    				          "cond string)");

			FileReader arqmvtoe = new FileReader("c:/temp/dados.txt");
			BufferedReader bloco = new BufferedReader(arqmvtoe);

			String regmvtoe = bloco.readLine();
			

			while (regmvtoe != null) {
				
				String regmvtos = regmvtoe.replace(";", "', '");
				
				regmvtos = "'" + regmvtos + "'";
	    		sql.executeUpdate("insert into clientes values(" + regmvtos + ")");
				regmvtoe = bloco.readLine();
				
			}

			bloco.close();

		} catch (Exception error) {
			System.out.println("***Error: \n" + error.getMessage());

		}
		finally {
    		
    		if (con != null) {
    			try {
    				con.close();
    			}
    			catch (Exception e) {
    				System.out.println("*** Erro ao desconectar o banco dados !\n" + e.getMessage());
    			}
    		}
    	}

		System.out.println("Programa Finalizando...");
		
	}

}
