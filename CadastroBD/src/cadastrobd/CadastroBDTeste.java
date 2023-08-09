/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.util.List;

/**
 *
 * @author leosc
 */
public class CadastroBDTeste {
    
    public static void main(String[] args) {
        ConectorBD conector = new ConectorBD
            ("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;",
                    "loja",
                    "loja");
        SequenceManager sequenceManager = new SequenceManager(conector);
        
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conector);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conector, sequenceManager);
        
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Joao azevedo");
        pessoaFisica.setLogradouro("Rua 15");
        pessoaFisica.setCidade("Riacho do Sul");
        pessoaFisica.setEstado("PA");
        pessoaFisica.setTelefone("4444-5555");
        pessoaFisica.setEmail("joaoazevedo@riacho.com");
        pessoaFisica.setCpf("44444444444");
        
        pessoaFisicaDAO.incluir(pessoaFisica);
        
        PessoaFisica pessoa1 = pessoaFisicaDAO.getPessoa(7);
        PessoaJuridica pessoa2 = pessoaJuridicaDAO.getPessoa(8);
        
        

        // Agora você pode usar o objeto pessoaFisicaDAO para acessar os métodos da classe
        List<PessoaFisica> pessoasF = pessoaFisicaDAO.getPessoas();
        List<PessoaJuridica> pessoasJ = pessoaJuridicaDAO.getPessoas();
         // ... fazer outras operações com o objeto pessoaFisicaDAO ...
        
        pessoa1.exibir();
      

        
        for (PessoaFisica pessoa : pessoasF) {
        pessoa.exibir();
        }
        for (PessoaJuridica pessoa : pessoasJ) {
        pessoa.exibir();
        
        }
        
        
    }
    
}
