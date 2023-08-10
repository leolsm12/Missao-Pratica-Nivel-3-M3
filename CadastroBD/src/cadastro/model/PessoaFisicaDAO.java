/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model;
import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
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
public class PessoaFisicaDAO {
    private ConectorBD conector;

    public PessoaFisicaDAO(ConectorBD conector) {
        this.conector = conector;
    }
    public PessoaFisicaDAO(){
    }

    public PessoaFisica getPessoa(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PessoaFisica pessoa = null;

        try {
            conn = conector.getConnection();
            String query = "SELECT * FROM Pessoas P JOIN PessoasFisicas PF ON P.idPessoa = PF.idPFisica WHERE P.idPessoa = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new PessoaFisica();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close(rs);
            conector.close(stmt);
            conector.close(conn);
        }

        return pessoa;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conector.getConnection();
            String query = "SELECT * FROM Pessoas P JOIN PessoasFisicas PF ON P.idPessoa = PF.idPFisica";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conector.close(rs);
            conector.close(stmt);
            conector.close(conn);
        }

        return pessoas;
    }
    
    public boolean incluir(PessoaFisica pessoa) {
        Connection conn = null;
        PreparedStatement stmtPessoa = null;
        PreparedStatement stmtPessoaFisica = null;
        boolean sucesso = false;

        try {
            conn = conector.getConnection();
            conn.setAutoCommit(false);
            
            // Inserir na tabela Pessoas
            String queryPessoa = "INSERT INTO Pessoas (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmtPessoa = conn.prepareStatement(queryPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtPessoa.setInt(1, pessoa.getId());
            stmtPessoa.setString(2, pessoa.getNome());
            stmtPessoa.setString(3, pessoa.getLogradouro());
            stmtPessoa.setString(4, pessoa.getCidade());
            stmtPessoa.setString(5, pessoa.getEstado());
            stmtPessoa.setString(6, pessoa.getTelefone());
            stmtPessoa.setString(7, pessoa.getEmail());
            stmtPessoa.executeUpdate();

            if (pessoa.getId() != -1) {
                // Inserir na tabela PessoasFisicas
                String queryPessoaFisica = "INSERT INTO PessoasFisicas (idPFisica, cpf) VALUES (?, ?)";
                stmtPessoaFisica = conn.prepareStatement(queryPessoaFisica);
                stmtPessoaFisica.setInt(1, pessoa.getId());
                stmtPessoaFisica.setString(2, pessoa.getCpf());
                stmtPessoaFisica.executeUpdate();
                conn.commit();
                sucesso = true;
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            conector.close(stmtPessoaFisica);
            conector.close(stmtPessoa);
            conector.close(conn);
        }

        return sucesso;
    }

    public boolean alterar(PessoaFisica pessoa) {
        Connection conn = null;
        PreparedStatement stmtPessoa = null;
        PreparedStatement stmtPessoaFisica = null;
        boolean sucesso = false;

        try {
            conn = conector.getConnection();
            conn.setAutoCommit(false);

            // Atualizar tabela Pessoas
            String queryPessoa = "UPDATE Pessoas SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
            stmtPessoa = conn.prepareStatement(queryPessoa);
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getLogradouro());
            stmtPessoa.setString(3, pessoa.getCidade());
            stmtPessoa.setString(4, pessoa.getEstado());
            stmtPessoa.setString(5, pessoa.getTelefone());
            stmtPessoa.setString(6, pessoa.getEmail());
            stmtPessoa.setInt(7, pessoa.getId());
            stmtPessoa.executeUpdate();

            // Atualizar tabela PessoasFisicas
            String queryPessoaFisica = "UPDATE PessoasFisicas SET cpf = ? WHERE idPFisica = ?";
            stmtPessoaFisica = conn.prepareStatement(queryPessoaFisica);
            stmtPessoaFisica.setString(1, pessoa.getCpf());
            stmtPessoaFisica.setInt(2, pessoa.getId());
            stmtPessoaFisica.executeUpdate();

            conn.commit();
            sucesso = true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            conector.close(stmtPessoaFisica);
            conector.close(stmtPessoa);
            conector.close(conn);
        }

        return sucesso;
    }

    public boolean excluir(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean sucesso = false;

        try {
            conn = conector.getConnection();
            conn.setAutoCommit(false);

            // Excluir da tabela PessoasFisicas
            String queryPessoaFisica = "DELETE FROM PessoasFisicas WHERE idPFisica = ?";
            stmt = conn.prepareStatement(queryPessoaFisica);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // Excluir da tabela Pessoas
            String queryPessoa = "DELETE FROM Pessoas WHERE idPessoa = ?";
            stmt = conn.prepareStatement(queryPessoa);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            conn.commit();
            sucesso = true;
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            conector.close(stmt);
            conector.close(conn);
            if (sucesso) {
                System.out.println("Pessoa excluída com sucesso!");
            } else {
                System.out.println("Erro ao excluir pessoa.");
            }
        }

        return sucesso;
    }
    
    
    
}
