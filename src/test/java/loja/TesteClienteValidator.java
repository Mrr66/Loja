package loja;

import java.util.Date;
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

import br.unibh.loja.entidades.Cliente;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteClienteValidator {
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
	System.out.println("_Inicializando validador...");
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();}
	
	@Test
	public void testeValidacaoCliente1() {
		Cliente o = new Cliente((1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		System.out.println(o);
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate( o );
		for (ConstraintViolation<Cliente> c: constraintViolations) {
			System.out.println(" Erro de Validacao: "+c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size() );
	}
	@Test
	public void testeValidacaoCliente2() {
		Cliente o = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		System.out.println(o);
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate( o );
		for (ConstraintViolation<Cliente> c: constraintViolations) {
			System.out.println(" Erro de Validacao: "+c.getMessage());
		}
		Assert.assertEquals(4, constraintViolations.size() );
	}
}



