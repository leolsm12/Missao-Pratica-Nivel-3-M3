/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import cadastro.model.util.SetPessoas;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.util.List;
import java.util.Scanner;

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
        SequenceManager sequenceManager = new SequenceManager(conector);
        Scanner scanner = new Scanner(System.in);
        
        PessoaFisica pessoaFisica = new PessoaFisica();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conector);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conector);
        boolean menu = true;
        boolean caso; 
        int numMenu;
        String tipoPessoa;
        int id;
        
        
        while(menu == true){
            System.out.println("=========================");
            System.out.println("1 - Incluir pessoa");
            System.out.println("2 - Alterar pessoa ");
            System.out.println("3 - Excluir pessoa ");
            System.out.println("4 - Exibir pelo ID ");
            System.out.println("5 - Exibir todos ");
            System.out.println("0 - Finalizar Programa");
            System.out.println("===========================");
                        
            System.out.println("Digite o numero desejado: ");
            numMenu = scanner.nextInt();
            scanner.nextLine();
            
            switch(numMenu){
                case 1:
                    caso = true;
                    while(caso == true){
                        System.out.println("Selecionado Incluir pessoa");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        tipoPessoa = scanner.nextLine().toUpperCase();

                        switch(tipoPessoa){
                            case "F" -> {
                                id = sequenceManager.getValue("seqId");
                                pessoaFisica.setId(id);
                                SetPessoas.fisicas(pessoaFisica, scanner);
                                pessoaFisicaDAO.incluir(pessoaFisica);
                                caso = false;
                                break;
                            }
                            
                            case "J" -> {
                                id = sequenceManager.getValue("seqId");
                                pessoaJuridica.setId(id);
                                SetPessoas.juridicas(pessoaJuridica, scanner);
                                pessoaJuridicaDAO.incluir(pessoaJuridica);
                                caso = false;
                                break;
                            }
                                
                            default -> {
                                System.out.println("comando incorreto");
                                caso = true;                                
                            }
                        }
                        
                    }
                break;
                
                case 2:
                    caso = true;
                    while(caso == true){
                        System.out.println("Selecionado alterar pessoa");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        tipoPessoa = scanner.nextLine().toUpperCase();
                        
                        switch(tipoPessoa){
                            case "F" -> {
                                System.out.println("Digite o ID:");
                                id = scanner.nextInt();
                                scanner.nextLine();                        
                                PessoaFisica pessoaF = pessoaFisicaDAO.getPessoa(id);
                                pessoaF.exibir();
                                pessoaFisica.setId(id);
                                SetPessoas.fisicas(pessoaFisica, scanner);
                                pessoaFisicaDAO.alterar(pessoaFisica);
                                System.out.println("Pessoa alterada com sucesso!");
                                caso = false;
                                break;
                            }
                                
                            case "J" -> {
                                System.out.println("Digite o ID:");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                PessoaJuridica pessoaJ = pessoaJuridicaDAO.getPessoa(id);
                                pessoaJ.exibir();
                                pessoaJuridica.setId(id);
                                SetPessoas.juridicas(pessoaJuridica, scanner);
                                pessoaJuridicaDAO.alterar(pessoaJuridica);
                                System.out.println("Pessoa alterada com sucesso!");
                                caso = false;
                                break;
                            }
                            
                            default -> {
                                System.out.println("comando incorreto");
                                caso = true;    
                            }
                        }
                    } 
                break;
                
                case 3:
                    caso = true;
                    while(caso == true){
                        System.out.println("Selecionado excluir pessoa");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        tipoPessoa = scanner.nextLine().toUpperCase();
                        
                        switch(tipoPessoa){
                            case "F" -> {
                                System.out.println("Digite o ID:");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                PessoaFisica pessoaF = pessoaFisicaDAO.getPessoa(id);
                                pessoaF.exibir();
                                pessoaFisicaDAO.excluir(id);
                                caso = false;
                                break;
                            }
                            
                            case "J" -> {
                                System.out.println("Digite o ID:");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                PessoaJuridica pessoaj = pessoaJuridicaDAO.getPessoa(id);
                                pessoaj.exibir();
                                pessoaJuridicaDAO.excluir(id);
                                caso = false;
                                break;
                            }
                                
                            default -> {
                                System.out.println("comando incorreto");
                                caso = true;    
                            }
                                
                        }    
                           
                    }
                break;
                
                case 4:
                    caso = true;
                    while(caso == true){
                        System.out.println("Selecionado exibir pessoa");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        tipoPessoa = scanner.nextLine().toUpperCase();
                        
                        switch(tipoPessoa){
                            case "F" -> {
                                System.out.println("Digite o ID:");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                PessoaFisica pessoaF = pessoaFisicaDAO.getPessoa(id);
                                pessoaF.exibir();
                                caso = false;
                            }
                            
                            case "J" -> {
                                System.out.println("Digite o ID:");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                PessoaJuridica pessoaj = pessoaJuridicaDAO.getPessoa(id);
                                pessoaj.exibir();
                                caso = false;
                            }
                                
                            default -> {
                                System.out.println("comando incorreto");
                                caso = true;    
                            }
                                
                        }    
                           
                    }
                break;
                
                case 5:
                    caso = true;
                    while(caso == true){
                        System.out.println("Selecionado exibir todos");
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        tipoPessoa = scanner.nextLine().toUpperCase();
                        
                        switch(tipoPessoa){
                            case "F" -> {
                                List<PessoaFisica> pessoasF = pessoaFisicaDAO.getPessoas();
                                for (PessoaFisica pessoa : pessoasF) {
                                pessoa.exibir();
                                }
                                caso = false;
                                break;
                            }
                            
                            case "J" -> {
                                List<PessoaJuridica> pessoasJ = pessoaJuridicaDAO.getPessoas();
                                for (PessoaJuridica pessoa : pessoasJ) {
                                pessoa.exibir();
                                }
                                caso = false;
                                break;
                            }
                                
                            default -> {
                                System.out.println("comando incorreto");
                                caso = true;    
                            }
                                
                        }    
                           
                    }
                break;    
                    
                case 0:
                    System.out.println("Programa encerrado!!");
                    menu = false;
            
            }
            
        }    
            
    }    
}
