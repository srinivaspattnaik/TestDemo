package com.mobitel.MobitelBackend.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobitel.MobitelBackend.model.Cart;

@Repository("cartDAO")
public class CartDAO 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public CartDAO(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean addToCart(Cart cartitem)
	{
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(cartitem);
		return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	
	public List<Cart> getCartItems(String username)
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Cart where username=:username and status='N'");
		query.setParameter("username",username);
		@SuppressWarnings("unchecked")
		List<Cart> list=query.list();
		return list;
	}
	
	@Transactional
	public void deleteCartItem(Cart cart)
	{
		sessionFactory.getCurrentSession().delete(cart);
	}
	
	public Cart getCartItem(int citemid)
	{
		Session session=sessionFactory.openSession();
		Cart cart=(Cart)session.get(Cart.class,citemid);
		return cart;
	}
	
	@Transactional
	public void updateCartItem(Cart cart)
	{
		sessionFactory.getCurrentSession().update(cart);;
	}
}
