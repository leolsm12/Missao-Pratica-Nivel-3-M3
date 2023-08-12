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
import java.util.Scanner;

/**
 *
 * @author leosc
 */
public class CadastroBDTeste {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConectorBD conector = new ConectorBD
            ("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;",
                    "loja",
                    "loja");
        
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conector);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conector);
        PessoaFisica pessoaFisica = new PessoaFisica();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        
        int opcao;
        boolean menu = true;
        
        while(menu == true){
            System.out.println("=========================");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar pessoa ");
            System.out.println("3 - Exibir todos ");
            System.out.println("4 - Excluir pessoa ");
            System.out.println("0 - Sair");
            System.out.println("===========================");
            System.out.println("Digite a opção desejada:");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
        switch (opcao) {
                case 1:
                    //Instanciar uma pessoa física e persistir no banco de dados. 
                    pessoaFisica.setId(1);
                    pessoaFisica.setNome("André Cunha");
                    pessoaFisica.setLogradouro("Rua 15");
                    pessoaFisica.setCidade("Riacho do Sul");
                    pessoaFisica.setEstado("PA");
                    pessoaFisica.setTelefone("4444-5555");
                    pessoaFisica.setEmail("andrecunha@riacho.com");
                    pessoaFisica.setCpf("44444444444");
                    
                    pessoaFisica.exibir();
        
                    pessoaFisicaDAO.incluir(pessoaFisica);
                    
                    pessoaJuridica.setId(2); 
                    pessoaJuridica.setNome("AC");
                    pessoaJuridica.setLogradouro("Rua 55");
                    pessoaJuridica.setCidade("Riacho do oeste");
                    pessoaJuridica.setEstado("BA");
                    pessoaJuridica.setTelefone("9999-5555");
                    pessoaJuridica.setEmail("ac@riacho.com");
                    pessoaJuridica.setCnpj("99999999999");
        
                    pessoaJuridicaDAO.incluir(pessoaJuridica);
                    
                    break;
                    
                case 2:
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
                    
                    
                    
                    pessoaJuridica.setId(2);
                    pessoaJuridica.setNome("PA-eletric");
                    pessoaJuridica.setLogradouro("Rua 70");
                    pessoaJuridica.setCidade("Riacho");
                    pessoaJuridica.setEstado("TO");
                    pessoaJuridica.setTelefone("8888-1111");
                    pessoaJuridica.setEmail("pa@riacho.com");
                    pessoaJuridica.setCnpj("11111111111");
        
                    pessoaJuridicaDAO.alterar(pessoaJuridica);
                    break;
                
                case 3:
                    //Consultar todas as pessoas físicas do banco de dados e listar no console.
                    List<PessoaFisica> pessoasF = pessoaFisicaDAO.getPessoas();
                    for (PessoaFisica pessoa : pessoasF) {
                    pessoa.exibir();
                    }
                    List<PessoaJuridica> pessoasJ = pessoaJuridicaDAO.getPessoas();
                    for (PessoaJuridica pessoa : pessoasJ) {
                    pessoa.exibir();
                    }
                    
                    break;
                case 4:
                    //Excluir a pessoa física criada anteriormente no banco.
                                        
                    
                    pessoaFisicaDAO.excluir(1);
                    
                   
                    pessoaJuridicaDAO.excluir(2 );
                    
                    break;
                case 0:
                    System.out.println("programa encerrado!!");
                    menu = false;
                    break;
                        
            }
        }
        
    }
    
}
