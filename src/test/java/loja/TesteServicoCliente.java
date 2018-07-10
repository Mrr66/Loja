package loja;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Produto;
import br.unibh.loja.negocio.DAO;
import br.unibh.loja.negocio.ServicoCategoria;
import br.unibh.loja.negocio.ServicoCliente;
import br.unibh.loja.negocio.ServicoProduto;
import br.unibh.loja.util.Resources;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteServicoCliente {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "testeseguro.war")
				.addClasses(Categoria.class, Cliente.class, Produto.class, Resources.class, DAO.class,
						ServicoCategoria.class, ServicoCliente.class, ServicoProduto.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	// Realiza as injecoes com CDI
	@Inject
	private Logger log;
	@Inject
	private ServicoCliente ss;

	@Test
	public void teste01_inserirSemErro() throws Exception {
		log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente o = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
		ss.insert(o);
		Cliente aux = (Cliente) ss.findByName("Marcos").get(0);
		assertNotNull(aux);
		log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	public void teste02_inserirComErro() throws Exception {
	log.info("============> Iniciando o teste " +
	Thread.currentThread().getStackTrace()[1].getMethodName());
	try {
		Cliente o = new Cliente(1, "Marcos, "Rafael", "senha", "teste", "12345678901", "12 9 11111111",
				"marcos@hotmail.com", new Date(), new Date());
	ss.insert(o);
	} catch (Exception e){
		assertTrue(checkString(e, "telefone invalido"));
	}
	log.info("============> Finalizando o teste " +
	Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	public void teste03_atualizar() throws Exception {
		log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente o = (Cliente) ss.findByName("Marcos").get(0);
		o.setNome("Gabriel G");
		ss.update(o);
		Cliente aux = (Cliente) ss.findByName("Marcos R").get(0);
		assertNotNull(aux);
		log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	public void teste04_excluir() throws Exception {
		log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Cliente o = (Cliente) ss.findByName("Marcosl").get(0);
		ss.delete(o);
		assertEquals(0, ss.findByName("Marcos Rafael").size());
		log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	private boolean checkString(Throwable e, String str) {
		if (e.getMessage().contains(str)) {
			return true;
		} else if (e.getCause() != null) {
			return checkString(e.getCause(), str);
		}
		return false;
	}
}