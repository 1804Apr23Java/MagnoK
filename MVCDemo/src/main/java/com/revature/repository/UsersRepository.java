package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Users;

@Repository(value="usersRepository")
@Transactional
@EnableTransactionManagement
public class UsersRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public int addUser(Users u) {
		Session s = sessionFactory.getCurrentSession();
		int result = (int) s.save(u);
		return result;
	}

	public Users getUserById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Users u = (Users) s.get(Users.class, id);
		return u;
	}

	public List<Users> getUsers() {
		List<Users> users = new ArrayList<Users>();
		Session s = sessionFactory.getCurrentSession();
		users = s.createQuery("from Users").list();
		return users;
	}

	public void updateUser(Users u) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.merge(u);
		tx.commit();
		s.close();
	}

	public void deleteUser(Users u) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();
		s.delete(u);
		tx.commit();
		s.close();
	}

}
