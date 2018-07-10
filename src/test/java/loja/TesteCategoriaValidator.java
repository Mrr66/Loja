package loja;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteCategoriaValidator {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
	System.out.println("_Inicializando validador...");
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();}
	@Test
	public void testeValidacaoCategoria1() {
		Categoria o = new Categoria(1, "Categoria 1");
		System.out.println(o);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate( o );
		for (ConstraintViolation<Categoria> c: constraintViolations) {
			System.out.println(" Erro de Validacao: "+c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size() );
	}
	@Test
	public void testeValidacaoCategoria2() {
		Categoria o = new Categoria(1, "");
		System.out.println(o);
		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate( o );
		for (ConstraintViolation<Categoria> c: constraintViolations) {
			System.out.println(" Erro de Validacao: "+c.getMessage());
		}
		Assert.assertEquals(1, constraintViolations.size() );
	}
}


