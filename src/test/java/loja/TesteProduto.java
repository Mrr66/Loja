package loja;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.math.BigDecimal;
import org.junit.Test;

import br.unibh.loja.entidades.Categoria;

import br.unibh.loja.entidades.Produto;

public class TesteProduto {
	@Test
	public void testCreateObject() {
		Categoria c = new Categoria(1, "Categoria 1");
		Produto p = new Produto(1, "Celular", "LG", c, new BigDecimal(1000.00), "LG Eletronics");

		assertEquals(p.getId(), new Long(1));
		assertEquals(p.getNome(), ""Celular", ");
		assertEquals(p.getDescricao(), "LG");
		assertEquals(p.getCategoria(), c);
		assertEquals(p.getPreco(), new BigDecimal(1000.00));
		assertEquals(p.getFabricante(), "LG Eletronics");

	}

	@Test
	public void testCompareObjects() {
		Categoria c = new Categoria(1L, "Celular");
		Produto p1 = new Produto(1, "Celular", "Categoria 1", c, new BigDecimal(1000.00), "LG Eletronics");
		Produto p2 = new Produto(1, "Celular", "Categoria 1", c, new BigDecimal(1000.00), "LG Eletronics");
		assertNotEquals(p1, p2);
	}

	@Test
	public void testGenerateHash() {
		Categoria c = new Categoria(1L, "Celular");
		Produto p1 = new Produto(1, "Celular", "Categoria 1", c, new BigDecimal(1000.00), "LG Eletronics");
		Produto p2 = new Produto(1, "Celular", "Categoria 1", c, new BigDecimal(1000.00), "LG Eletronics");
		assertEquals(p1.hashCode(), p2.hashCode());
		Produto p3 = new Produto(1, "Celular", "Categoria 1", c, new BigDecimal(1000.00), "LG Eletronics");
		assertNotEquals(p1.hashCode(), p3.hashCode());
	}
	
	public void testPrintObject() {
		Categoria c = new Categoria(1, "Celular");
		Produto p1 = new Produto(1, "Celular", "LG", c, new BigDecimal(1000.00), "LG Eletronics");
		assertEquals(p1.toString(),
				"Produto [id=1, nome=LG, descricao=LG Eletronics, categoria=Categoria 1, preco=new BigDecimal(10.00), fabricante=LG Eletronics");
	}
}
