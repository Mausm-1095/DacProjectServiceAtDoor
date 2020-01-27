package com.app.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.dao.ISubServiceDao;
import com.app.pojos.ServicePojo;
import com.app.pojos.SubServicePojo;

@CrossOrigin(allowedHeaders ="*")
@RestController
@RequestMapping("/subservice")
public class SubServiceController 
{
	@Autowired
	ISubServiceDao subser;
	
	public SubServiceController() {
		System.out.println("in side subservice ctor");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSubServices(@PathVariable int id)
	{
		System.out.println("in getSubService  " + id);
		try {
			// conform if id exist
			ServicePojo service =subser.getMainService(id);
			System.out.println(service);
			if (service == null)
				throw new RuntimeException("service ID invalid");
			return new ResponseEntity<List<SubServicePojo>>(subser.getAllSubServices(id), OK);
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<Integer>(0, NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delSubService(@PathVariable int id) {
	System.out.println("in del sub-service " + id);
		try {
		if (id == 0)
				throw new RuntimeException("service ID invalid");
			else
			{
				SubServicePojo service = subser.getSubService(id);
				return new ResponseEntity<Integer>(subser.deleteSubService(service), OK);
			}
	} catch (RuntimeException e1) {
			e1.printStackTrace();
			return new ResponseEntity<Integer>(0, NOT_FOUND);
		}
	}	
	
	// request handling method to update service details
		@PutMapping("/{id}")
		public ResponseEntity<Integer> updateSubService(@PathVariable int id, @RequestBody SubServicePojo service) {
			System.out.println("in update " + id + " " + service);
			// invoke service's method
			try {
				return new ResponseEntity<Integer>(subser.updateSubService(id, service), OK);
			} catch (RuntimeException e1) {
				e1.printStackTrace();
				return new ResponseEntity<Integer>(0, INTERNAL_SERVER_ERROR);
			}
		}
		
		@PostMapping
		public ResponseEntity<Integer> addNewSubService(@RequestBody SubServicePojo ser) {
			System.out.println("in add service " + ser);
			try {
				return new ResponseEntity<Integer>(subser.addSubService(ser), CREATED);
			} catch (RuntimeException e1) {
				return new ResponseEntity<Integer>(0, INTERNAL_SERVER_ERROR);
			}
		}
	
}
