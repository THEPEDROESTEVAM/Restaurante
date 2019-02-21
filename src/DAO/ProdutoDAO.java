
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProdutoDAO  extends ExecuteSQL{
    
    public ProdutoDAO(Connection con) {
        super(con);
    }
     public String Inserir_Produto(ProdutoDAO p){
        
        String sql = "INSERT INTO produto VALUES (0,?,?,?,?,?)";
        
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
}
