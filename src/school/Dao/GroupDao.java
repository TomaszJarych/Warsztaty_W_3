package school.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import school.DbUtil.DbUtil;
import school.domain.Group;

public class GroupDao {
	private static final String ID_COLUMN_NAME = "id";
	private static final String INSERT_INTO_GROUP = "INSERT INTO warsztaty2.user_groups (name)VALUES(?);";
	private static final String LOAD_GROUP_BY_ID = "select * from warsztaty2.user_groups  where warsztaty2.user_groups.id =?;";
	private static final String LOAD_ALL_GROUPS = "select * from warsztaty2.user_groups ;";
	private static final String LOAD_ALL_GROUPS_LIMIT = "select * from warsztaty2.user_groups order by id desc Limit ?;";
	private static final String UPDATE_GROUP = "UPDATE warsztaty2.user_groups SET name = ? where id = ?";
	private static final String DELETE_GROUP = "DELETE FROM warsztaty2.user_groups WHERE warsztaty2.user_groups.id=?;";

	public void saveToDB(Group group) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			if (group.getId() == 0) {
				String generatedColumns[] = { ID_COLUMN_NAME };
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_GROUP, generatedColumns);
				preparedStatement.setString(1, group.getName());
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					group.setId(resultSet.getLong(1));
				}
			} else {
				PreparedStatement preparedStatement2 = conn.prepareStatement(UPDATE_GROUP);
				preparedStatement2.setString(1, group.getName());
				preparedStatement2.setLong(2, group.getId());
				preparedStatement2.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Group loadGroupByID(long id) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_GROUP_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Group loadedGroup = createGroup(resultSet);
				return loadedGroup;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Group createGroup(ResultSet resultSet) throws SQLException {
		String name = resultSet.getString("name");
		Group loadedGroup = new Group(name);
		loadedGroup.setId(resultSet.getLong(ID_COLUMN_NAME));
		return loadedGroup;
	}

	public static List<Group> loadAllGroups() throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<Group> allGroups = new ArrayList<>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_GROUPS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				allGroups.add(createGroup(resultSet));
			}
			return allGroups;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	public static List<Group> loadAllGroupsLimit(int limit) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<Group> allGroups = new ArrayList<>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_GROUPS_LIMIT);
			preparedStatement.setInt(1, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				allGroups.add(createGroup(resultSet));
			}
			return allGroups;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public void deleteGroup(Group group) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			if (group.getId() != 0) {
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_GROUP);
				preparedStatement.setLong(1, group.getId());
				preparedStatement.executeUpdate();
				group.setId(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}