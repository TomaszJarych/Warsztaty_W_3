package school.service;

import java.sql.SQLException;
import java.util.List;

import school.Dao.UserDao;
import school.domain.User;

public class UserService {
	UserDao dao = new UserDao();

	public List<User> loadAllUsersLimit(int limit) throws SQLException {
		return dao.loadAllUsersLimit(limit);
	}
	public List<User> loadAllUsers() throws SQLException {
		return dao.loadAllUsers();
	}
	public User loadUserById(long id) throws SQLException {
		return dao.loadUserById(id);
	}
	public List<User> loadAllByGrupId(long userGroupId) throws SQLException {
		return dao.loadAllByGrupId(userGroupId);
	}
	public void save(User user) throws SQLException {
		dao.save(user);
	}
	public void deleteUser (User user) throws SQLException{
		dao.delete(user);
	}
}
