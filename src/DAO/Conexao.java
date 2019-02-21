
package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    
  public static Connection AbrirConexao(){
    Connection con = null;
  
    try{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/restaurante";
        con = DriverManager.getConnection(url,"root","");
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Erro na conexão com o banco","Restaurante",JOptionPane.ERROR_MESSAGE);
        e.getMessage();
    }
  
    return con;
  
  }
  
  public static void FecharConexao(Connection con){
      try{
          con.close();
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão com banco"+e.getMessage());
      }
  }
}
