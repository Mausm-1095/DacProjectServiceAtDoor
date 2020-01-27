package com.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.BookingPojo;
import com.app.pojos.ServicePojo;

@Service
@Transactional
public class BookingDao implements IBookingDao 
{
	@Autowired
	SessionFactory sf;

	public BookingDao() {
		System.out.println("in booking dao");
	}
	
	@Override
	public Integer registerBooking(BookingPojo book)
	{
		
		System.out.println("in booking dao"+book);
		sf.getCurrentSession().save(book);
		return book.getId();
	}
	
	@Override
	public List<BookingPojo> getUserBookedServices(int id) 
	{
		System.out.println("in dao of get user booked services "+id);

		String jpql="select b from BookingPojo b where b.user.id= :id";
		List<BookingPojo>  bookings = sf.getCurrentSession().createQuery(jpql, BookingPojo.class).setParameter("id", id).getResultList();
		System.out.println(bookings);
		return bookings;
	}

	
	@Override
	public Integer deleteBooking(int id) 
	{
		System.out.println("in delete boking dao " +id);
		BookingPojo b=getsubserviceId(id);
		sf.getCurrentSession().delete(b);
		return 1;
	}

	@Override
	public BookingPojo getsubserviceId(int id) {
		System.out.println("inside getsubservice id "+id);
		String jpql = "select s from BookingPojo s where s.id=:Id";
		return sf.getCurrentSession().createQuery(jpql, BookingPojo.class).setParameter("Id", id).getSingleResult();
	}
}
