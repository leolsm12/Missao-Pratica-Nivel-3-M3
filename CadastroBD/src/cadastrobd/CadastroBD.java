/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastro.model.PessoaFisicaDAO;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import cadastrobd.model.PessoaFisica;

/**
 *
 * @author leosc
 */
public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         ConectorBD conector = new ConectorBD
            ("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;",
                    "loja",
                    "loja");
        // TODO code application logic here
        PessoaFisica pessoaFisica = new PessoaFisica();
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conector);
        
        SequenceManager sequenceManager = new SequenceManager(conector);
            int id = sequenceManager.getValue("novaId");
                    pessoaFisica.setId(id);
                    pessoaFisica.setNome("André Cunha");
                    pessoaFisica.setLogradouro("Rua 15");
                    pessoaFisica.setCidade("Riacho do Sul");
                    pessoaFisica.setEstado("PA");
                    pessoaFisica.setTelefone("4444-5555");
                    pessoaFisica.setEmail("andrecunha@riacho.com");
                    pessoaFisica.setCpf("44444444444");
        
                    pessoaFisicaDAO.incluir(pessoaFisica);
        
    }
    
}
