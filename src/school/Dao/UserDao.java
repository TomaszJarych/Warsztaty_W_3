package school.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import school.DbUtil.DbUtil;
import school.domain.User;

public class UserDao {
	private static final String DELETE_FROM_USERS_WHERE_ID = "DELETE FROM warsztaty2.users	WHERE	id=	?";
	private static final String SELECT_FROM_USERS_WHERE_ID = "SELECT * FROM	warsztaty2.users	where	id=?";
	private static final String ID_COLUMN_NAME = "ID";
	private static final String LOAD_ALL_BY_GRUP_ID = "SELECT * FROM warsztaty2.users where warsztaty2.users.user_group_id = ?;";
	private static final String INSERT_INTO_USERS = "INSERT	INTO warsztaty2.users (username,	email,	password, user_group_id)	VALUES	(?,	?,	?,?)";
	private static final String UPDATE_USERS = "UPDATE warsztaty2.users SET username=?, email=?, password=?, user_group_id = ? where id = ?";

	public void saveToDB(User user) {
		try (Connection conn = DbUtil.getConn()) {
			if (user.getId() == 0) {
				String generatedColumns[] = { ID_COLUMN_NAME };
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_USERS, generatedColumns);
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setLong(4, user.getUserGroupId());
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(User user) {
		try (Connection conn = DbUtil.getConn()) {
			if (user.getId() == 0) {
				String generatedColumns[] = { ID_COLUMN_NAME };
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_USERS, generatedColumns);
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setLong(4, user.getUserGroupId());
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
				}
			} else {
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USERS);
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setLong(4, user.getUserGroupId());
				preparedStatement.setLong(5, user.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(User user) {
		try (Connection conn = DbUtil.getConn()) {
			if (user.getId() != 0) {
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_FROM_USERS_WHERE_ID);
				preparedStatement.setLong(1, user.getId());
				preparedStatement.executeUpdate();
				user.setId(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User loadUserById(long id) {
		try (Connection conn = DbUtil.getConn()) {
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_FROM_USERS_WHERE_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User loadedUser = createUser(resultSet);
				return loadedUser;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static User createUser(ResultSet resultSet) throws SQLException {
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		String email = resultSet.getString("email");
		long userGroupId = resultSet.getLong("user_group_id");
		User loadedUser = new User(username, email, password, userGroupId);
		loadedUser.setId(resultSet.getLong(ID_COLUMN_NAME));
		return loadedUser;
	}

	public static List<User> loadAllUsers() throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<User> users = new ArrayList<User>();
			String sql = "SELECT * FROM warsztaty2.users";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}
	public static List<User> loadAllUsersLimit(int limit) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			List<User> users = new ArrayList<User>();
			String sql = "SELECT * FROM warsztaty2.users order by id desc Limit ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}

	public static List<User> loadAllByGrupId(long userGroupId) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			List<User> users = new ArrayList<User>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_BY_GRUP_ID);
			preparedStatement.setLong(1, userGroupId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
}
