package br.unb.cic.agenda.dominio;

import org.junit.Test;

import junit.framework.Assert;

public class TesteContato {
	
	@Test
	public void testaConstrutor() {
		Pessoa contatoRodrigo = new Pessoa("Rodrigo Bonifacio", "rbonifacio@");
		Pessoa contatoRebeca = new Pessoa("Rebeca", "rebeca");
		Professor hilmer = new Professor("h", "h@");
		
		Assert.assertEquals("Rodrigo Bonifacio", contatoRodrigo.getNome());
		Assert.assertEquals("Rebeca", contatoRebeca.getNome());
		Assert.assertEquals("h",hilmer.getNome());
		Assert.assertNull(hilmer.getEndereco());
	}

}
