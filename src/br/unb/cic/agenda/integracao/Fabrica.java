package br.unb.cic.agenda.integracao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

//import br.unb.cic.agenda.integracao.memoria.AgendaDB;

public class Fabrica {

	public static IAgendaDB instanciar() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File("persistencia.properties")));
			String tipo = properties.getProperty("persistencia");
			
			if(tipo.equals("couch")) {
				String database = properties.getProperty("database");
				return br.unb.cic.agenda.integracao.couchdb.AgendaDB.instance(database);
			}
			else {
				return br.unb.cic.agenda.integracao.memoria.AgendaDB.instance();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return br.unb.cic.agenda.integracao.memoria.AgendaDB.instance();
		}
		
		//return  AgendaDB.instance("db-agenda-teste"); //couchdb
		//return AgendaDB.instance(); //memoria
	}
}
