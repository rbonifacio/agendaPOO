package br.unb.cic.agenda.integracao;
 
public class Fabrica {

	public static IAgendaDB instanciar() {
		return  br.unb.cic.agenda.integracao.memoria.AgendaDB.instance();
	}
}
