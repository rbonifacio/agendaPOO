package br.unb.cic.agenda.ui;

import java.util.Scanner;

public class Agenda {

	private Scanner sc;
	
	public Agenda() {
		sc = new Scanner(System.in);
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
		System.out.println(" [1] Sair");
	
		int opcao = sc.nextInt();
		
		switch(opcao) {
		case 1: 
			System.out.println("Bye... \n \n"); 
			System.exit(0);
			break;
		
		default: 
			System.out.println("\n \nOpcao invalida! Tente novamente. \n"); 
			menuPrincipal();
		}
		
	}
}
