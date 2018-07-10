package br.unibh.loja.negocio;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.loja.entidades.Cliente;
@Stateless
@LocalBean
public class ServicoCliente implements DAO<Cliente, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	public Cliente insert(Cliente t) throws Exception {
		t.setPerfil("Standard");
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	public Cliente update(Cliente t) throws Exception {
		LocalDate now = LocalDate.now();
		LocalDate dataCadastro = t.getDataCadastro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		  long numberOfYears = Period.between(now, dataCadastro).getYears();
		if (numberOfYears < 1 && !( t.getPerfil().equals("Standard") )) {
			throw new Exception("So é permitido os perfis Standard para clientes com menos de 1 ano");
	}
		else if (numberOfYears >= 1 &&
				numberOfYears < 5 && 
				!( t.getPerfil().equals("Standard") || t.getPerfil().equals("Premium") )) {
				throw new Exception("So é permitido os perfis Standard e Premium entre 1 e 5 anos");
		} 
		else if (numberOfYears > 5 &&
				!( t.getPerfil().equals("Standard")||
				 t.getPerfil().equals("Gold")||
				 t.getPerfil().equals("Premium")))
			throw new Exception("So é permitido os perfis Standard, Premium e Gold para clientes acima de 5 anos");


		log.info("Atualizando " + t);
		return em.merge(t);
	}

	public void delete(Cliente t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	public Cliente find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Cliente.class, k);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Cliente").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Cliente.findByName").setParameter("nome", "%" + name + "%").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findByPerfil(String perfil) throws Exception {
		log.info("Encontrando o " + perfil);
		return em.createNamedQuery("Cliente.findByPerfil").setParameter("perfil", "%" + perfil + "%").getResultList();
	}
}
