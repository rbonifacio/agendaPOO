package br.unb.cic.agenda.integracao;

import br.unb.cic.agenda.dominio.Pessoa;

/**
 * Interface usada para abstrair a tecnologia de 
 * persistencia. 
 *  
 * @author rbonifacio
 *
 */
public interface IAgendaDB {
	public void incluirContato(Pessoa pessoa) throws Exception;
	public Pessoa pesquisaPorId(String id) throws Exception;
	public Pessoa pesquisaPorNome(String nome) throws Exception;
	public void removerContato(String id) throws Exception;
	public int quantidadeDeContatos() throws Exception;
}
