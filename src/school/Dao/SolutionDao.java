package school.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import school.DbUtil.DbUtil;
import school.domain.Solution;
import school.domain.User;

public class SolutionDao {
	private static final String ID_COLUMN_NAME = "id";
	private static final String INSERT_INTO_SOLUTION = "INSERT INTO warsztaty2.solutions(created, description, exercise_id, users_id)VALUES (?,?,?,?);";
	private static final String LOAD_SOLUTION_BY_ID = "SELECT * FROM warsztaty2.solutions where id =?";
	private static final String LOAD_ALL_SOLUTIONS = "SELECT * FROM warsztaty2.solutions;";
	private static final String LOAD_ALL_SOLUTIONS_LIMIT = "SELECT * FROM warsztaty2.solutions order by id desc Limit ?";
	private static final String LOAD_ALL_SOLUTIONS_FROM_USER = "SELECT * FROM warsztaty2.solutions inner join warsztaty2.users on warsztaty2.solutions.users_id = warsztaty2.users.id WHERE warsztaty2.users.id=? ;";
	private static final String LOADALL_BY_EXERCISE_ID = "SELECT * FROM warsztaty2.solutions where warsztaty2.solutions.exercise_id =? order by warsztaty2.solutions.updated asc;";
	private static final String UPDATE_SOLUTION = "UPDATE warsztaty2.solutions SET updated = ?, description =?  WHERE id =? ;";
	private static final String DELETE_SOLUTION = "DELETE FROM `warsztaty2`.`solutions` WHERE warsztaty2.solutions.id =?;";

	public void saveToDB(Solution solution) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			if (solution.getId() == 0) {
				String generatedColumns[] = { ID_COLUMN_NAME };
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_SOLUTION, generatedColumns);
				Timestamp timestamp = Timestamp.valueOf(solution.getCreated());
				preparedStatement.setTimestamp(1, timestamp);
				preparedStatement.setString(2, solution.getDescription());
				preparedStatement.setLong(3, solution.getExercise_id());
				preparedStatement.setLong(4, solution.getUsers_id());
				preparedStatement.executeUpdate();
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					solution.setId(resultSet.getLong(1));
				}
			} else {
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_SOLUTION);
				Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
				preparedStatement.setTimestamp(1, timestamp);
				preparedStatement.setString(2, solution.getDescription());
				preparedStatement.setLong(3, solution.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Solution loadSolutionById(long id) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_SOLUTION_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Solution solution = createSolution(resultSet);
				return solution;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Solution createSolution(ResultSet resultSet) throws SQLException {
		String description = resultSet.getString("description");
		long exerciseId = resultSet.getLong("exercise_id");
		long userID = resultSet.getLong("users_id");
		Solution solution = new Solution(description, exerciseId, userID);
		solution.setId(resultSet.getLong(ID_COLUMN_NAME));
		solution.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
		//solution.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
		return solution;
	}

	public static List<Solution> allSolutions() throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<Solution> solutions = new ArrayList<Solution>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_SOLUTIONS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				solutions.add(createSolution(resultSet));
			}
			return solutions;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	public static List<Solution> allSolutionsLimit(int limit) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			List<Solution> solutions = new ArrayList<Solution>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_SOLUTIONS_LIMIT);
			preparedStatement.setInt(1, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				solutions.add(createSolution(resultSet));
			}
			return solutions;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public void deleteSolution(Solution solution) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			if (solution.getId() != 0) {
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_SOLUTION);
				preparedStatement.setLong(1, solution.getId());
				preparedStatement.execute();
				solution.setId(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static List<Solution> loadAllByUserId(User user) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<Solution> solutions = new ArrayList<Solution>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_SOLUTIONS_FROM_USER);
			preparedStatement.setLong(1, user.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				solutions.add(createSolution(resultSet));
			}
			return solutions;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static List<Solution> loadAllByExerciseId(long exerciseId) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<Solution> solutions = new ArrayList<Solution>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOADALL_BY_EXERCISE_ID);
			preparedStatement.setLong(1, exerciseId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				solutions.add(createSolution(resultSet));
			}
			return solutions;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
