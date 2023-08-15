/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;
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
        
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conector);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conector);
        PessoaFisica pessoaFisica = new PessoaFisica();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
 
        //Instanciar uma pessoa física e persistir no banco de dados. 
        pessoaFisica.setId(1);
        pessoaFisica.setNome("João");
        pessoaFisica.setLogradouro("Rua 12, casa 3, Quitanda");
        pessoaFisica.setCidade("Riacho do Sul");
        pessoaFisica.setEstado("PA");
        pessoaFisica.setTelefone("1111-1111");
        pessoaFisica.setEmail("joao@riacho.com");
        pessoaFisica.setCpf("11111111111");
                    
        pessoaFisica.exibir();
        
        pessoaFisicaDAO.incluir(pessoaFisica);
        System.out.println("Pessoa Fisica incluida");
                    
        pessoaJuridica.setId(2); 
        pessoaJuridica.setNome("JJC");
        pessoaJuridica.setLogradouro("Rua 11, Centro");
        pessoaJuridica.setCidade("Riacho do Norte");
        pessoaJuridica.setEstado("PA");
        pessoaJuridica.setTelefone("1212-1212");
        pessoaJuridica.setEmail("jjc@riacho.com");
        pessoaJuridica.setCnpj("22222222222");
                    
        pessoaJuridica.exibir();
        
        pessoaJuridicaDAO.incluir(pessoaJuridica);
        System.out.println("Pessoa Juridica incluida");
      
        //Alterar os dados da pessoa física no banco.           
        pessoaFisica.setId(1);
        pessoaFisica.setNome("Paulo André");
        pessoaFisica.setLogradouro("Rua 20");
        pessoaFisica.setCidade("Riacho do Norte");
        pessoaFisica.setEstado("SP");
        pessoaFisica.setTelefone("7777-8888");
        pessoaFisica.setEmail("pauloandre@riacho.com");
        pessoaFisica.setCpf("99999999999");
                    
        pessoaFisicaDAO.alterar(pessoaFisica);
        System.out.println("Pessoa Fisica alterada");
  
        pessoaJuridica.setId(2);
        pessoaJuridica.setNome("PA-eletric");
        pessoaJuridica.setLogradouro("Rua 70");
        pessoaJuridica.setCidade("Riacho");
        pessoaJuridica.setEstado("TO");
        pessoaJuridica.setTelefone("8888-1111");
        pessoaJuridica.setEmail("pa@riacho.com");
        pessoaJuridica.setCnpj("11111111111");
        
        pessoaJuridicaDAO.alterar(pessoaJuridica);
        System.out.println("Pessoa Juridica alterada");
        
        //Consultar todas as pessoas físicas do banco de dados e listar no console.
        System.out.println("Exibir todas Pessoas Fisicas");
        List<PessoaFisica> pessoasF = pessoaFisicaDAO.getPessoas();
        for (PessoaFisica pessoa : pessoasF) {
        pessoa.exibir();
        }
        
        System.out.println("Exibir todas Pessoas Juridicas");        
        List<PessoaJuridica> pessoasJ = pessoaJuridicaDAO.getPessoas();
        for (PessoaJuridica pessoa : pessoasJ) {
        pessoa.exibir();
        }
        
        //Excluir a pessoa física criada anteriormente no banco.
        pessoaFisicaDAO.excluir(1);
        System.out.println("Pessoa fisica excluida!!");
        
        pessoaJuridicaDAO.excluir(2 );
        System.out.println("Pessoa Juridica excluida!!");

        System.out.println("programa encerrado!!");                       
    }
}
