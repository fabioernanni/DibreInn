package br.com.lebrehotel.dibreinn.model.pessoa;

/**
 *
 * @author Fabio
 */
import br.com.lebrehotel.dibreinn.persistencia.ConectarBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

  public Usuario validarDados(String usuario, String senha) {
    
    ConectarBD conexao = new ConectarBD();
    
    PreparedStatement stmt = null;
    Usuario u = null;

    String query = "SELECT P.EMAIL, F.SENHA FROM TB_PESSOA AS P "
	    + "JOIN TB_FUNCIONARIO AS F "
	    + "ON P.ID_PESSOA = F.ID_PESSOA "
	    + "WHERE P.EMAIL = '" + usuario + "' AND F.SENHA = '" + senha + "'";

    try {
      
      conexao.openConection();
      stmt = conexao.conn.prepareStatement(query);

      ResultSet rs = stmt.executeQuery();
      
      //Condição que verifica se o usuário tem login no sistema
      if (rs.getString("SENHA").equalsIgnoreCase("")) {
	
	return u;
	
      //Condição que verifica se a consulta retornou algo
      } else if (rs.getString("EMAIL").equalsIgnoreCase("")) {
	
	return u;
	
      }
      
      u = new Usuario();
      u.setEmail(rs.getString("EMAIL"));
      u.setSenha(rs.getString("SENHA"));
      
    } catch (SQLException ex) {
      // Caso haja erro retorna 0 como ID e informa no log
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "[INFO] Erro ao buscar os dados: " + query, ex);

    } finally {
      if (stmt != null) {
	try {
	  stmt.close();
	} catch (SQLException ex) {
	  Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
      }
      if (conexao != null) {
	conexao.closeConection();
      }
    }
    return u;
  }
}
