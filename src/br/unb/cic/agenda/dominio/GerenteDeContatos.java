package br.unb.cic.agenda.dominio;

/**
 * Classe responsavel por gerenciar contatos
 * em uma agenda simples de enderecos. 
 * 
 * @author rbonifacio
 */
public class GerenteDeContatos {

	private static final int MAX = 10;
	private int numeroContatos; 
	
	private Pessoa contatos[];
	
	public GerenteDeContatos() {
		numeroContatos = 0;
		contatos = new Pessoa[MAX];	
	}
	
	public void incluirContato(Pessoa pessoa) throws Exception {
		if(numeroContatos < MAX) {
			contatos[numeroContatos++] = pessoa;
		}
		else {
			throw new Exception("Numero maximo de contatos permitido");
		}
	}
	
	public Pessoa pesquisarContato(String nome) {
//		for(int i = 0; i < numeroContatos; i++) {
//			if(nome.equals(contatos[i].getNome())) {
//				return contatos[i];
//			}
//		}
		
		for(Pessoa p : contatos) {
			if(p != null && nome.equals(p.getNome())) {
				return p;
			}
		}
		
		return null;
	}
	
	public int quantidadeDeContatos() {
		return numeroContatos;
	}
}
