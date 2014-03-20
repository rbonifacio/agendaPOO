package br.unb.cic.agenda.ui;

import java.util.Scanner;

import br.unb.cic.agenda.dominio.GerenteDeContatos;
import br.unb.cic.agenda.dominio.Pessoa;

public class Agenda {

	private Scanner sc;
	private GerenteDeContatos gc; 
	
	public Agenda() {
		sc = new Scanner(System.in);
		gc = new GerenteDeContatos();
	}
	
	public static void main(String args[]) {
		System.out.println("==========================================");
		System.out.println(" Bem vindo ao sistema de agendas da UnB");
		System.out.println("==========================================");
		
		Agenda agenda = new Agenda();
		
		agenda.menuPrincipal();
	}
	
	public void menuPrincipal() {
		System.out.println("\n");	
		System.out.println(" Selecione uma das opcoes: \n ");
		System.out.println(" [1] Pesquisar Contato");
		System.out.println(" [2] Incluir Pessoa");
		System.out.println(" [3] Incluir Professor");
		System.out.println(" [4] Sair");
		
		int opcao = sc.nextInt();
		
		switch(opcao) {
		case 1: 
			menuPesquisarContato();
			break;
			
		case 2: 
			menuIncluirPessoa();
			break;
		
		case 3: System.out.println("Ainda nao implementado");
				System.exit(1);
				//break;
		case 4: 
			System.out.println("Bye... \n \n"); 
			System.exit(0);
			break;
		
		default: 
			System.out.println("\n \nOpcao invalida! Tente novamente. \n"); 
			menuPrincipal();
		}
		
	}

	private void menuIncluirPessoa() {
		System.out.println("Inform o nome: ");
		String nome = sc.next();
		System.out.println("Informe o email: ");
		String email = sc.next();
		
		try {
			gc.incluirContato(new Pessoa(nome, email));
			System.out.println("Contato adicionado com sucesso");
		}
		catch(Exception e) {
			System.out.println("Erro na inclusao da pessoa");
			System.out.println(e.getMessage());
		}
		menuPrincipal();
	}

	private void menuPesquisarContato() {
		System.out.println("Informe o nome do contato: ");
		String nome = sc.next();
		
		Pessoa pessoa = gc.pesquisarContato(nome);
		if(pessoa == null){
			System.out.println("Pessoa nao encontrada.");
		}
		else {
			System.out.println("Nome: " + pessoa.getNome());
			System.out.println("email:" + pessoa.getEmail());
		}
		System.out.println("Pressione uma tecla para retornar");
		sc.nextLine();
		menuPrincipal();
	}
}
