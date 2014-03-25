package br.unb.cic.agenda.integracao.couchdb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lightcouch.CouchDbClient;
import org.lightcouch.NoDocumentException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.unb.cic.agenda.dominio.Pessoa;
import br.unb.cic.agenda.dominio.Professor;
import br.unb.cic.agenda.integracao.IAgendaDB;

/**
 * Uma implementacao de camada de persistencia usando 
 * o CouchDB. Essa implementacao eh com finalidade puramente 
 * pedagogica para expor o conceito de polimorfismo. Com isso, nao existe 
 * a preocupacao na implementacao de uma solucao elegante para  
 * comunicacao com o couchDB. 
 * 
 * @author rbonifacio
 */
public class AgendaDB implements IAgendaDB {

	private CouchDbClient dbCliente;
	
	private static AgendaDB instance; 
	
	/*
	 * Construtor privado de acordo com o padrao de projeto 
	 * singleton. 
	 */
	private AgendaDB(String db) { 
		dbCliente = new CouchDbClient(db, true, "http", "127.0.0.1", 5984, "", "");
	}
	
	/** 
	 * Seguindo o padrao singleton, facilita o acesso a 
	 * instancia unica da classe AgendaDB. 
	 */
	public static AgendaDB instance(String db) {
		if(instance == null) {
			instance = new AgendaDB(db);
		}
		return instance;
	}
	
	/**
	 * Adiciona um contato na base de dados. 
	 * 
	 * @param contato a ser adicionado.
	 */
	public void incluirContato(Pessoa contato) {
		incluirContato(null, contato);
	}
	
	/*
	 * Adiciona um contato informando uma String. Note que 
	 * esse metodo eh protected, sendo acessivel apenas para 
	 * as subclasses / classes presentes no mesmo pacote. 
	 * Estrategia util para testar o codigo (ver a classe de testes). 
	 */
	protected void incluirContato(String id, Pessoa contato) {
		if(pesquisaPorId(id) != null) {
			throw new RuntimeException("documento com id " + id + " ja cadastrado");
		}
		
		String tipoContato = "pessoa";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(id != null) {
			map.put("_id", id);
		}
		
		map.put("nome", contato.getNome());
		map.put("email", contato.getEmail());
		
		if(contato instanceof Professor) {
			Professor professor = (Professor)contato;
			map.put("telefone", professor.getTelefone());
			map.put("endereco", professor.getTelefone());
			tipoContato = "professor";
		}
		map.put("docType", "contato");
		map.put("tipoContato", tipoContato);
	
		dbCliente.save(map);
	}
	
	/**
	 * Pesquisa um contato pelo nome. 
	 * @param nome criterio usado na pesquisa 
	 * 
	 * @return contato cujo nome foi informado como argumento.
	 */
	public Pessoa pesquisaPorNome(String nome) {
		List<JsonObject> allDocs = dbCliente.view("_all_docs").query(JsonObject.class);
		
		for (JsonObject jsonObject : allDocs) {
			Pessoa pessoa = pesquisaPorId(jsonObject.get("id").getAsString());
			if(pessoa != null && pessoa.getNome().equals(nome)) {
				return pessoa;
			}
		}
		return null;
	}
	
	
	
	/**
	 * Pesquisa um contato pelo identificador. 
	 * 
	 * @param id identificador usado como criterio de pesquisa
	 * 
	 * @return contato recuperado da base de dados e que possui o 
	 * identificador passado como argumento. 
	 */
	public Pessoa pesquisaPorId(String id) {
		
		if(id == null) {
			return null;
		}
		
		JsonObject json = null;
		
		try {
			json = dbCliente.find(JsonObject.class, id);
		}
		catch(NoDocumentException e) {
			return null; //caso o documento nao exista, retorna nulo. 
		}
		 
		return transformaJsonPessoa(json);
	}
	
	/**
	 * Remove um contato da base de dados. 
	 * 
	 * @param id identificador do contato a ser removido.
	 */
	public void removerContato(String id) {
		JsonObject json = dbCliente.find(JsonObject.class, id);
		JsonElement element = json.get("_rev");
		dbCliente.remove(id, element.getAsString());
	}
	
	/*
	 * Transforma um objeto Json em uma Pessoa
	 */
	private Pessoa transformaJsonPessoa(JsonObject json) {
		//TODO: esse codigo pode ser reestruturado. 
		//um possivel caminho eh criar metodos toJSON e 
		//fromJSON na hierarquia de contatos... ou algo 
		//parecido.
		
		if(json.get("docType") == null || (!json.get("docType").getAsString().equals("contato"))) {
			return null;
		}
		
		Pessoa pessoa = null;
		
		String nome = json.get("nome").getAsString();
		String email = json.get("email").getAsString();
		
		if(json.get("tipoContato").getAsString().equals("professor")) {
			String telefone = json.get("telefone") != null ? json.get("telefone").getAsString() : null;
			String endereco = json.get("endereco") != null ? json.get("endereco").getAsString() : null;
			
			Professor professor = new Professor(nome, email);
			
			if(telefone != null) {
				professor.setTelefone(telefone);
			}
			if(endereco != null) {
				professor.setEndereco(endereco);
			}
			pessoa = professor;
		}
		else {
			pessoa = new Pessoa(nome, email);
		}
		
		return pessoa;
	}

	@Override
	public int quantidadeDeContatos() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
