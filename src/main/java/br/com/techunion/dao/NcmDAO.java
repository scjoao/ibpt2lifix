package br.com.techunion.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.techunion.config.DatabaseConfig;
import br.com.techunion.modelo.NCM;

public class NcmDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NcmDAO.class);

	@PersistenceContext(unitName = "lifixPDVPU")
	private final EntityManager em;

	public NcmDAO() throws Exception {
		em = DatabaseConfig.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<NCM> findAll() {
		return em.createNamedQuery("NCM.findall").getResultList();
	}

	public NCM findById(Long id) {
		return em.find(NCM.class, id);
	}
	
	public NCM findByCodNcm (String cod){ 
		NCM ncm = null;
		try {
			ncm = (NCM) em.createNamedQuery("NCM.findByCodNcm").setParameter("valor",cod).getSingleResult();
		}
		catch (NoResultException e) {			
			LOGGER.warn("Nenhum registro encontrado com o codigo {}", cod);
		}
		catch (Exception e) {	
			LOGGER.warn("Erro ao executar a pesquisa com o codigo {}", cod, e);
		}
		return ncm;
	}
	
	public void save(NCM entity) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			//close();
		}
	}

	public void update(NCM entity) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			//close();
		}

	}
	public void close() {
		if (em.isOpen()) {
			em.close();
		}
	}


}
