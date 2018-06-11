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
}
