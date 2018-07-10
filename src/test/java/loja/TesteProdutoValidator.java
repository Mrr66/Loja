package loja;

import java.math.BigDecimal;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Produto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteProdutoValidator {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeValidacaoProduto1() {
		Categoria c = new Categoria(1, "Categoria 1");
		Produto p = new Produto(1, "Celular", "LG", c, new BigDecimal(1000.00), "LG Eletronics");

		System.out.println(p);
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testeValidacaoProduto2() {
		Categoria c1 = new Categoria(1, "Categoria 1");
		Produto p = new Produto(1, "Celular", "LG", c, new BigDecimal(1000.00), "LG Eletronics");
		System.out.println(p);
		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size());
	}
}