package br.unb.cic.agenda.integracao.couchdb;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.unb.cic.agenda.dominio.Pessoa;
import br.unb.cic.agenda.dominio.Professor;

public class TesteAgendaDB {

	@Before
	public void configurarTestes() {
		AgendaDB db = AgendaDB.instance("db-agenda-teste");

		if(db.pesquisaPorId("123") != null) {
			db.removerContato("123");
		}
	}
	
	@Test
	public void salvarAgenda() {
		AgendaDB db = AgendaDB.instance("db-agenda-teste");
		
		db.incluirContato("123", new Professor("rodrigo", "teste"));
	}
	
	@Test
	public void pesquisarProfessorPorId() {
		AgendaDB db = AgendaDB.instance("db-agenda-teste");

		Pessoa p = db.pesquisaPorId("123");
		
		Assert.assertNull(p);
		
		db.incluirContato("123", new Professor("rodrigo", "teste"));
		
		p = db.pesquisaPorId("123");
		
		Assert.assertNotNull(p);
	}
	
	@Test
	public void pesquisarProfessorPorNome() {
		AgendaDB db = AgendaDB.instance("db-agenda-teste");

		Pessoa p = db.pesquisaPorId("123");
		
		Assert.assertNull(p);
		
		db.incluirContato("123", new Professor("rodrigo", "teste"));
		
		p = db.pesquisaPorNome("rodrigo");
		
		Assert.assertNotNull(p);
	}
}
