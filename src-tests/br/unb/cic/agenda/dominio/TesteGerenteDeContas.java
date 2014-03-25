package br.unb.cic.agenda.dominio;

import junit.framework.Assert;

import org.junit.Test;

public class TesteGerenteDeContas {

	@Test
	public void testePesquisarContato() {
		Pessoa p1 = new Pessoa("n1", "e1");
		Pessoa p2 = new Professor("n2", "e2", "222", "sqn");
		GerenteDeContatos gc = new GerenteDeContatos();

		Assert.assertNotNull(gc);

		
		try {
			Pessoa p = gc.pesquisarContato("n1");
			
			Assert.assertNull(p);
			
			gc.incluirContato(p1);
			gc.incluirContato(p2);
			p = gc.pesquisarContato("n1");
			Assert.assertNotNull(p);
			Assert.assertEquals("n1", p.getNome());
			Assert.assertEquals("e1", p.getEmail());
			
			p = gc.pesquisarContato("n2");
			
			Assert.assertEquals("n2", p.getNome());
			Assert.assertEquals("e2", p.getEmail());
			
			Assert.assertTrue(p instanceof Professor);
			
			if(p instanceof Professor) {
				Professor umProfessor = (Professor)p;
				Assert.assertEquals("222", umProfessor.getTelefone());
				Assert.assertEquals("sqn", umProfessor.getEndereco());
			}
					 
		}
		catch(Exception e){
			Assert.fail();
		}
		
		
	}
	@Test
	public void testeIncluirContato() {
		Pessoa p1 = new Pessoa("n1", "e1");
		Pessoa p2 = new Pessoa("n2", "e2");
		Pessoa p3 = new Pessoa("n3", "e3");
		Pessoa p4 = new Pessoa("n4", "e4");
		Pessoa p5 = new Pessoa("n5", "e5");
		Pessoa p6 = new Pessoa("n6", "e6");
		Pessoa p7 = new Pessoa("n7", "e7");
		Pessoa p8 = new Pessoa("n8", "e8");
		Professor professor1 = new Professor("n9", "e9");
		Professor professor2 = new Professor("n10", "e10", "telefone", "sqn");
		
		GerenteDeContatos gc = new GerenteDeContatos();
		
		Assert.assertNotNull(gc);
		
		try {
			gc.incluirContato(p1);
		
		
			Assert.assertEquals(1, gc.quantidadeDeContatos());
		
			gc.incluirContato(professor1);
			gc.incluirContato(professor2);
			
			Assert.assertEquals(3, gc.quantidadeDeContatos());
			
			gc.incluirContato(p2);
			gc.incluirContato(p3);
			gc.incluirContato(p4);
			gc.incluirContato(p5);
			gc.incluirContato(p6);
			gc.incluirContato(p7);
			gc.incluirContato(p8);
			
			Assert.assertEquals(10, gc.quantidadeDeContatos());
		}
		catch(Exception e) {
			System.out.println("Esse bloco nao deve ser executado");
			Assert.fail(e.getMessage());
		}
		try {
			gc.incluirContato(new Pessoa("n11", "e11" ));
			
			System.out.println("Nesse ponto, o fluxo de controle eh interrompido");

			Assert.assertEquals(11, gc.quantidadeDeContatos());
		}
		catch(Exception e) {
			System.out.println("Ocorreu excecao");

			Assert.assertTrue(true);
		}
	}
}
