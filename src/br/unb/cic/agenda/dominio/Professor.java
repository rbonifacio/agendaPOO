package br.unb.cic.agenda.dominio;

public class Professor extends Pessoa {

	private String telefone;
	private String endereco;
	
	public Professor(String n, String e) {
		super(n, e);
	}

	public Professor(String nome, String email, String telefone, String endereco) {
		super(nome, email);
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	
	
	
}
