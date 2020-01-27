package com.app.dao;

import java.util.List;

import com.app.pojos.ServicePojo;
import com.app.pojos.SubServicePojo;

public interface ISubServiceDao {

	List<SubServicePojo> getAllSubServices(int Id);
	
	ServicePojo getMainService(int Id);
	
	SubServicePojo getSubService(int Id);
	
	Integer deleteSubService(SubServicePojo serv);
	
	Integer updateSubService(int id,SubServicePojo serv);
	
	Integer addSubService(SubServicePojo ser);
}
