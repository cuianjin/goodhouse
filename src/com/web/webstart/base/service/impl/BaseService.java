package com.web.webstart.base.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.webstart.base.controller.BaseController;

/**
 * @Title: BaseService.java
 * @Package com.web.xxx.business.service.impl
 * @Description:
 * @author eason.zt
 * @date 2014年8月20日 下午6:55:24
 * @version V1.0
 */
public abstract class BaseService<T> {


    protected static final Logger LOGGER = Logger.getLogger(BaseService.class);
    
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@SuppressWarnings("unchecked")
	public List<T> query(String sql,String resultSetMapping){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query= em.createNativeQuery(sql,resultSetMapping);
		List<T> list = query.getResultList();
		em.close();
		return list;
	}
	
	public Integer insert(String sql){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query= em.createNativeQuery(sql);
		Integer r = query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> query(String sql,Integer begin,Integer count){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query= em.createNativeQuery(sql);
		if(begin != null && count != null){
			query.setFirstResult(begin);
			query.setMaxResults(count);
		}
		List<Object[]> list = query.getResultList();
		em.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> queryString(String sql,Integer begin,Integer count){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query= em.createNativeQuery(sql);
		if(begin != null && count != null){
			query.setFirstResult(begin);
			query.setMaxResults(count);
		}
		List<String> list = query.getResultList();
		em.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> queryParams(String sql,Integer begin,Integer count,List<Object> params){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query= em.createNativeQuery(sql);
		for(int i=0;i<params.size();i++){
			query.setParameter(i+1, params.get(i));
		}
		if(begin != null && count != null){
			query.setFirstResult(begin);
			query.setMaxResults(count);
		}
		List<Object[]> list = query.getResultList();
		em.close();
		return list;
	}
	
	public Integer delete(String sql,List<Object> params){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query= em.createNativeQuery(sql);
		for(int i=0;i<params.size();i++){
			query.setParameter(i+1, params.get(i));
		}
		Integer r = query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		return r;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> queryCall(String procString,List<Object> params){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query= em.createNativeQuery(procString);
		for(int i=0;i<params.size();i++){
			query.setParameter(i+1, params.get(i));
		}
		List<Object[]> list = query.getResultList();
		em.close();
	    return list;
	}
	
	protected List<T> iterable2List(Iterable<T> t){
		List<T> list = new ArrayList<T>();
		Iterator<T> it = t.iterator();
		while(it.hasNext()){
			list.add(it.next());
		}
		return list;
	}
	
}

