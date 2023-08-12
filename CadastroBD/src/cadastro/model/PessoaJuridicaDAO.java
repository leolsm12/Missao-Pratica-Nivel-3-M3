/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model;
import cadastrobd.model.PessoaJuridica;
import cadastro.model.util.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leosc
 */
public class PessoaJuridicaDAO {
    private ConectorBD conector;
    
    
    public PessoaJuridicaDAO(){
    }

    public PessoaJuridicaDAO(ConectorBD conector) {
        this.conector = conector;
        
    }

    public PessoaJuridica getPessoa(int id) {
        String sql = "SELECT * FROM Pessoas P INNER JOIN PessoasJuridicas PJ ON P.idPessoa = PJ.idPJuridica WHERE P.idPessoa = ?";
        try (Connection conn = conector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PessoaJuridica pessoaJuridica = new PessoaJuridica(rs.getInt("idPessoa"),
                            rs.getString("nome"), rs.getString("logradouro"),
                            rs.getString("cidade"), rs.getString("estado"),
                            rs.getString("telefone"), rs.getString("email"),
                            rs.getString("cnpj"));
                    return pessoaJuridica;
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoas P INNER JOIN PessoasJuridicas PJ ON P.idPessoa = PJ.idPJuridica";
        try (Connection conn = conector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica(rs.getInt("idPessoa"),
                        rs.getString("nome"), rs.getString("logradouro"),
                        rs.getString("cidade"), rs.getString("estado"),
                        rs.getString("telefone"), rs.getString("email"),
                        rs.getString("cnpj"));
                pessoasJuridicas.add(pessoaJuridica);
            }
        } catch (SQLException e) {
            return null;
        }
        return pessoasJuridicas;
    }

    public boolean incluir(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "INSERT INTO Pessoas (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoasJuridicas (idPJuridica, cnpj) VALUES (?, ?)";

        try (Connection conn = conector.getConnection();
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {
            
            stmtPessoa.setInt(1, pessoaJuridica.getId());
            stmtPessoa.setString(2, pessoaJuridica.getNome());
            stmtPessoa.setString(3, pessoaJuridica.getLogradouro());
            stmtPessoa.setString(4, pessoaJuridica.getCidade());
            stmtPessoa.setString(5, pessoaJuridica.getEstado());
            stmtPessoa.setString(6, pessoaJuridica.getTelefone());
            stmtPessoa.setString(7, pessoaJuridica.getEmail());
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica.setInt(1, pessoaJuridica.getId());
            stmtPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
            stmtPessoaJuridica.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean alterar(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "UPDATE Pessoas SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE idPessoa=?";
        String sqlPessoaJuridica = "UPDATE PessoasJuridicas SET cnpj=? WHERE idPJuridica=?";

        try (Connection conn = conector.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {

            stmtPessoa.setString(1, pessoaJuridica.getNome());
            stmtPessoa.setString(2, pessoaJuridica.getLogradouro());
            stmtPessoa.setString(3, pessoaJuridica.getCidade());
            stmtPessoa.setString(4, pessoaJuridica.getEstado());
            stmtPessoa.setString(5, pessoaJuridica.getTelefone());
            stmtPessoa.setString(6, pessoaJuridica.getEmail());
            stmtPessoa.setInt(7, pessoaJuridica.getId());
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            stmtPessoaJuridica.setInt(2, pessoaJuridica.getId());
            stmtPessoaJuridica.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean excluir(int id) {
        String sqlPessoa = "DELETE FROM Pessoas WHERE idPessoa=?";
        String sqlPessoaJuridica = "DELETE FROM PessoasJuridicas WHERE idPJuridica=?";
         try (Connection conn = conector.getConnection();
         PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
         PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);) {
         
        // Excluir da tabela PessoasJuridicas primeiro
        stmtPessoaJuridica.setInt(1, id);
        stmtPessoaJuridica.executeUpdate();
        
        // Em seguida, excluir da tabela Pessoas
        stmtPessoa.setInt(1, id);
        stmtPessoa.executeUpdate();
        
        return true;
    } catch (SQLException e) {
        return false;
    }
  }
    
}
