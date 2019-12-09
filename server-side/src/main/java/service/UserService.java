package service;

import java.util.List;

import javax.persistence.Persistence;

import dao.UserDAO;
import model.User;


public class UserService {
	private UserDAO userDAO;

	public UserService() {
		try {
			userDAO = new UserDAO(Persistence.createEntityManagerFactory("WordCount"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void addFile(User c) {
		userDAO.create(c);
	}

	public void updateFile(User u) {
		userDAO.update(u);
	}

	public List<User> getAllCategories() {
		return userDAO.findAll();
	}
}