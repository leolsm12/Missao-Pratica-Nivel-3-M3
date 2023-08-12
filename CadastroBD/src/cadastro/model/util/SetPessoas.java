/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;

import cadastrobd.model.Pessoa;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.util.Scanner;

/**
 *
 * @author leosc
 */
public class SetPessoas {

    public static void setDadosGerais(Pessoa pessoa, Scanner scanner) {
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite a Rua:");
        String logradouro = scanner.nextLine();

        System.out.println("Digite a cidade:");
        String cidade = scanner.nextLine();

        System.out.println("Digite o estado:");
        String estado = scanner.nextLine();

        System.out.println("Digite o telefone:");
        String telefone = scanner.nextLine();

        System.out.println("Digite o E-mail:");
        String email = scanner.nextLine();
                
        
        pessoa.setNome(nome);
        pessoa.setLogradouro(logradouro);
        pessoa.setCidade(cidade);
        pessoa.setEstado(estado);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
    }

    public static void setDadosPessoaFisica(PessoaFisica pessoaFisica, Scanner scanner) {
        setDadosGerais(pessoaFisica, scanner);

        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();
        pessoaFisica.setCpf(cpf);
    }

    public static void setDadosPessoaJuridica(PessoaJuridica pessoaJuridica, Scanner scanner) {
        setDadosGerais(pessoaJuridica, scanner);

        System.out.println("Digite o CNPJ:");
        String cnpj = scanner.nextLine();
        pessoaJuridica.setCnpj(cnpj);
    }

    public static void juridicas(PessoaJuridica pessoaJuridica, Scanner scanner) {
        SetPessoas.setDadosPessoaJuridica(pessoaJuridica, scanner);
    }

    public static void fisicas(PessoaFisica pessoaFisica, Scanner scanner) {
        SetPessoas.setDadosPessoaFisica(pessoaFisica, scanner);
    }
    
}

    

