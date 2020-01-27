package com.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.ServicePojo;
import com.app.pojos.SubServicePojo;


@Repository
@Transactional
public class AdminDao implements IAdminDao
{	
	@Autowired
	private SessionFactory sf;

	@Override
	public List<ServicePojo> getAllMainServices() 
	{
		String jpql = "select s from ServicePojo s";
		return sf.getCurrentSession().createQuery(jpql, ServicePojo.class).getResultList();
	}

	@Override
	public ServicePojo getMainService(int Id)
	{
		String jpql="select s from ServicePojo s where s.id=:id";
		return sf.getCurrentSession().createQuery(jpql, ServicePojo.class).setParameter("id", Id).getSingleResult();
	}

	@Override
	public Integer addMainService(ServicePojo MainService)
	{
		System.out.println(MainService.getId());
		sf.getCurrentSession().save(MainService);
		return MainService.getId();
	}

	@Override
	public ServicePojo updateMainService(ServicePojo MainService)
	{
		System.out.println("inside update service"+MainService);
		sf.getCurrentSession().update(MainService);
		//hs.update(MainService);
		return MainService;
	}
	
	@Override
	public Integer deleteMainService(ServicePojo MainService)
	{
		System.out.println(MainService.getId());
		sf.getCurrentSession().delete(MainService);
		return MainService.getId();
	}

	
	

}
