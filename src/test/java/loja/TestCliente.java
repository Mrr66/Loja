package loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.unibh.loja.entidades.Cliente;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCliente {
	
	@Test
	public void testCreateObject() {
		Cliente c = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());

		assertEquals(c.getId(), new Long(1));
		assertEquals(c.getNome(), "Marcos");
		assertEquals(c.getLogin(), "Rafael");
		assertEquals(c.getSenha(), "senha");
		assertEquals(c.getPerfil(), "teste");
		assertEquals(c.getCpf(), "12345678901");
		assertEquals(c.getTelefone(), "12 9 11111111");
		assertEquals(c.getEmail(), "marcos@hotmail.com");
		assertEquals(c.getDataNascimento(), new Date());
		assertEquals(c.getDataCadastro(), new Date());

	}

	@Test
	public void testCompareObjects() {
		Date d = new Date();
		Cliente c1 = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		Cliente c2 = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		assertNotEquals(c1, c2);
	}

	@Test
	public void testGenerateHash() {
		Cliente c1 = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		Cliente c2 = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		assertEquals(c1.hashCode(), c2.hashCode());
		Cliente c3 = Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		assertNotEquals(c1.hashCode(), c3.hashCode());
	}

	public void testPrintObject() {
		Date d = new Date();
		Cliente c1 = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", d, d);
		assertEquals(c1.toString(),
				"Cliente [id= 1, nome=Marcos, login=Rafael, senha=senha, perfil=teste, cpf=12345678901, telefone=12 9 11111111, email=marcos@hotmail.com, dataNascimento=new Date(), dataCadastro=new Date()");
	}
}
