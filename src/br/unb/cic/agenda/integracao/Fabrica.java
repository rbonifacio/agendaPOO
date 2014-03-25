package br.unb.cic.agenda.integracao;
 
public class Fabrica {

	public static IAgendaDB instanciar() {
		return  br.unb.cic.agenda.integracao.couchdb.AgendaDB.instance("db-agenda-teste");
	}
}
