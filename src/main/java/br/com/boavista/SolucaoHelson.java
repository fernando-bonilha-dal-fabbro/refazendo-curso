package br.com.boavista;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class SolucaoHelson {
    private static final String SQL_INSERT = "INSERT INTO clientes ( " +
        " id , nome , cpf , endereco , bairro , cidade , uf , status) " +
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
    
    public static void main(String[] args) {
        System.out.println("Iniciando processamento...");
        FileReader reader = null;
        BufferedReader buffer = null;
        Connection con = null;
        try {
            reader = new FileReader("c:/temp/dados.txt");
            buffer = new BufferedReader(reader);
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
                    
            Statement sqlDDL = con.createStatement();
            
            sqlDDL.executeUpdate("drop table if exists clientes");
            sqlDDL.executeUpdate("create table clientes ( id integer, nome string, cpf string, endereco string, bairro string, cidade string, uf string, status string)");
            PreparedStatement sqlInsert = con.prepareStatement(SQL_INSERT);
            
            String registro = buffer.readLine();
            
            while (registro != null) {
                String[] dados = registro.split(";");
                
                sqlInsert.setInt(1, Integer.valueOf(dados[0]));
                sqlInsert.setString(2, dados[1]);
                sqlInsert.setString(3, dados[2]);
                sqlInsert.setString(4, dados[3]);
                sqlInsert.setString(5, dados[4]);
                sqlInsert.setString(6, dados[5]);
                sqlInsert.setString(7, dados[6]);
                sqlInsert.setString(8, dados[7]);
                
                System.out.println("SqlInsert " + sqlInsert.toString());
                sqlInsert.executeUpdate();
                
                registro = buffer.readLine();
            }
        } catch(Exception e) {
            System.out.println("Erro ao processar arquivo!\n" + e.getMessage());
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Exception e) {
                    System.out.println("Erro ao fechar arquivo de dados!\n" + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println("Erro ao fechar conexão com o banco de dados! \n" + e.getMessage());
                }
            }
            System.out.println("Processamento concluído!");
        }           
    }
}