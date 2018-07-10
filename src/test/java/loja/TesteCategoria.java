package loja;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import br.unibh.loja.entidades.Categoria;


public class TesteCategoria {
	@Test
	public void testCreateObject() {
		Categoria c = new Categoria(1L, "lavabo");
		assertEquals(c.getId(), new Long(1L));
		assertEquals(c.getDescricao(), "lavabo");
	}

	@Test
	public void testCompareObjects() {
		Categoria c1 = new Categoria(1, "Categoria 1");
		Categoria c2 = new Categoria(2, "Categoria 2");
		assertNotEquals(c1, c2);
	}

	@Test
	public void testGenerateHash() {
		Categoria c1 = new Categoria(1, "Categoria 1");
		Categoria c2 = new Categoria(2, "Categoria 2");
		assertEquals(c1.hashCode(), c2.hashCode());
		Categoria c3 = new Categoria(1, "Categoria 1");
		assertNotEquals(c1.hashCode(), c3.hashCode());
	}

	public void testPrintObject() {
		Categoria c1 = new Categoria(1, "Categoria 1");
		assertEquals(c1.toString(),
				"Categoria [id= 1, descricao=Teste");
	}
}


