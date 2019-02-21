
package DAO;
import Modelo.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioDAO extends ExecuteSQL{
    
    public FuncionarioDAO(Connection con) {
        super(con);
    }
    
    public boolean LogarGerente(String login, String senha){
        boolean finalResult = false;
        try{
            String consulta = "select login, senha from gerente where login = '"+login+"' and senha ='"+senha+"'";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            if(rs != null){
                while(rs.next()){
                    Funcionario a = new Funcionario();
                    a.setLogin(rs.getString(1));
                    a.setSenha(rs.getString(2));
                    finalResult = true;
                }
            }
        }catch(Exception e){
        }
        return finalResult;
    }
        
    public boolean LogarGarcom(String login, String senha){
        boolean finalResult = false;
        try{
            String consulta = "select login, senha from garcom where login = '"+login+"' and senha ='"+senha+"'";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Funcionario a = new Funcionario();
                    a.setLogin(rs.getString(1));
                    a.setSenha(rs.getString(2));
                    finalResult = true;
                }
            }
        }catch(Exception e){
        }
        return finalResult;
    }
      public String Inserir_Funcionario(Funcionario f){
        
        String sql = "INSERT INTO usuario VALUES (0,?,?,?,?,?)";
        
        try{
            
            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1,f.getNome());
            ps.setString(2,f.getDatanasc());
            ps.setString(3,f.getTelefone());
            ps.setString(4, f.getEndereco());
            ps.setString(5, f.getCpf());
            
            if(ps.executeUpdate() > 0){
                Pesquisar_Cod_Funcionario(f);
                return "Inserido com Sucesso";
            }else{
                return "Erro ao Inserir";
            }
            
        }catch(Exception e){
            return e.getMessage();
        }
    }
      
      public List<Funcionario> Pesquisar_Cod_Funcionario(Funcionario f){
        String sql = "SELECT idUsuario FROM usuario where cpf = '"+f.getCpf()+"'";
        List<Funcionario> lista = new ArrayList<>();
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
                while(rs.next()){
                    f.setIdFuncionario(rs.getInt(1));
                    lista.add(f);
                }
                Inserir_Escolaridade(f);
                return lista;
                
            }else{
                return null;
            }
        }catch(Exception ex){
            return null;
            
        }
    
    }
      
       public String Inserir_Escolaridade(Funcionario f){
        String sql = "INSERT INTO garcom VALUES (0,?,?,?,?)";
        
        try{
            
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1,f.getIdFuncionario());
            ps.setString(2,f.getEscolaridade());
            ps.setString(3,f.getLogin());
            ps.setString(4,f.getSenha());
        
            if(ps.executeUpdate() > 0){
                return "Inserido com Sucesso";
            }else{
                return "Erro ao Inserir";
            }
            
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
