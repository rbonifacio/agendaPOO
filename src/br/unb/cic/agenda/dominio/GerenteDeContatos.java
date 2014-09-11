package br.unb.cic.agenda.dominio;

import br.unb.cic.agenda.integracao.Fabrica;
import br.unb.cic.agenda.integracao.IAgendaDB;

/**
 * Classe responsavel por gerenciar contatos
 * em uma agenda simples de enderecos. 
 * 
 * @author rbonifacio
 */
public class GerenteDeContatos {

	private IAgendaDB db; 
	
	public GerenteDeContatos() {
		db =  Fabrica.instanciar();
	}
	
	public void incluirContato(Pessoa pessoa) throws Exception {
		db.incluirContato(pessoa);
	}
	
	public Pessoa pesquisarContato(String nome) throws Exception {
		return db.pesquisaPorNome(nome);
	}
	
	public int quantidadeDeContatos() throws Exception {
		return db.quantidadeDeContatos();
	}
}
