package br.unb.cic.agenda.integracao;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import br.unb.cic.agenda.integracao.memoria.AgendaDB;

public class Fabrica {

	public static IAgendaDB instanciar() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File("persistencia.properties")));
			String tipo = properties.getProperty("persistencia");
			
			Class clazz = Class.forName(tipo);
			
			List<String> args  = new ArrayList<String>();
			List<Class>  types = new ArrayList<Class>();
			
			/* ---------------------------------------------------------------
			 * Entendimento do codigo. 
			 * ---------------------------------------------------------------
			 * Com base nas informacoes do arquivo de persistencia.properties, 
			 * obtemos as informacoes de configuracao que vao alem da classe que 
			 * implementa IAgendaDB. Essas propriedades devem ser do tipo String, 
			 * e deve existir um metodo "instance()" que recebe todas 
			 * essas propriedades adicionais como String
			 */
			for(Object k : properties.keySet()) {
				if(!k.equals("persistencia")) {
					args.add(properties.getProperty(k.toString()));
					types.add(String.class);
				}
			}
			
			
			IAgendaDB db = null;
			
			//Note que a operacao clazz.getMethod espera o nome do metodo 
			//e um array com os tipos passados como argumentos. 
			//assumimos que os argumentos, quando existirem, serao do 
			//tipo String. Bom, esse codigo poderia ser melhorado com 
			//injecao de dependencia, sendo util apenas para discutir 
			//reflection. 
			
			if(args.size() == 0) {
				Method m = clazz.getMethod("instance",types.toArray(new Class[types.size()]));
				return (IAgendaDB)m.invoke(null, null);
			}
			else { 
				Method m = clazz.getMethod("instance",types.toArray(new Class[types.size()]));
				return (IAgendaDB)m.invoke(null, args.toArray(new String[args.size()]));
			}
			
//			if(tipo.equals("couch")) {
//				String database = properties.getProperty("database");
//				return br.unb.cic.agenda.integracao.couchdb.AgendaDB.instance(database);
//			}
//			else {
//				return br.unb.cic.agenda.integracao.memoria.AgendaDB.instance();
//			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return br.unb.cic.agenda.integracao.memoria.AgendaDB.instance();
		}
		
		//return  AgendaDB.instance("db-agenda-teste"); //couchdb
		//return AgendaDB.instance(); //memoria
	}
}
