package br.fa7.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BaseService<T> {

	private Class<T> entityClass;

	public BaseService(){		
	}
	
    protected BaseService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

	@PersistenceContext(unitName="biblioteca")
	protected EntityManager em;
	
	public T find(Integer id){
		if(id == null) return null;
		return em.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(){
		return em.createQuery(
		        String.format("from %s", this.entityClass.getName()))
		        .getResultList();
	}

	public T insert(T entity) {
    	if(entity == null) return null;
    	em.persist(entity);
    	return entity;
    }

	public T update(T entity) {
    	if(entity == null) return null;
        return em.merge(entity);
    }
	
	public T remove(T entity){
		if(entity == null) return null;
		em.remove(entity);
		return entity;
	}
}

