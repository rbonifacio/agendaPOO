package br.unb.cic.agenda.integracao.memoria;

import br.unb.cic.agenda.dominio.Pessoa;
import br.unb.cic.agenda.integracao.IAgendaDB;

public class AgendaDB implements IAgendaDB {

	private static final int MAX = 10;
	private int numeroContatos; 
	
	private Pessoa contatos[];
	
	private static AgendaDB instance;
	
	/*
	 * construtor privado de acordo com o padrao 
	 * de projeto singleton. 
	 */
	public AgendaDB() { 
		numeroContatos = 0;
		contatos = new Pessoa[MAX];	
	}
	
	public static AgendaDB instance() {
		if(instance == null) {
			instance = new AgendaDB();
		}
		return instance; 
	}
	
	@Override
	public void incluirContato(Pessoa pessoa) throws Exception {
		if(numeroContatos < MAX) {
			contatos[numeroContatos++] = pessoa;
		}
		else {
			throw new Exception("Numero maximo de contatos permitido");
		}	
	}

	public Pessoa pesquisaPorId(String id) throws Exception {
		throw new Exception("metodo ainda nao implementado");
	}
	
	@Override
	public Pessoa pesquisaPorNome(String nome) throws Exception {
		for(Pessoa p : contatos) {
			if(p != null && nome.equals(p.getNome())) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void removerContato(String id) throws Exception {
		throw new Exception("metodo ainda nao implementado");
	}
	
	@Override
	public int quantidadeDeContatos() {
		return numeroContatos;
	}

}
