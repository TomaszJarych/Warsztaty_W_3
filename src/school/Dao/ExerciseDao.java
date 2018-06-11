package school.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import school.DbUtil.DbUtil;
import school.domain.Exercise;

public class ExerciseDao {
	private static final String ID_COLUMN_NAME = "id";
	private static final String INSERT_INTO_EXERCISE = "INSERT INTO warsztaty2.exercises(title, description) VALUES (?,?);";
	private static final String LOAD_EXERCISE_BY_ID = "SELECT * FROM warsztaty2.exercises where warsztaty2.exercises.id =?;";
	private static final String LOAD_ALL_EXERCISES = "SELECT * FROM warsztaty2.exercises;";
	private static final String LOAD_ALL_EXERCISES_LIMIT = "SELECT * FROM warsztaty2.exercises order by id desc Limit ?;";
	private static final String UPDATE_EXERCISE = "UPDATE warsztaty2.exercises SET title = ?, description =? WHERE id = ?;";
	private static final String DELETE_EXERCISE = "DELETE FROM warsztaty2.exercises WHERE warsztaty2.exercises.id =?;";

	public void saveToDB(Exercise exercise) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			if (exercise.getId() == 0) {
				String generatedColumns[] = { ID_COLUMN_NAME };
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_EXERCISE, generatedColumns);
				preparedStatement.setString(1, exercise.getTitle());
				preparedStatement.setString(2, exercise.getDescription());
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					exercise.setId(resultSet.getLong(1));
				}
			} else {
				PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_EXERCISE);
				preparedStatement.setString(1, exercise.getTitle());
				preparedStatement.setString(2, exercise.getDescription());
				preparedStatement.setLong(3, exercise.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Exercise exerciseLoadById(long id) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_EXERCISE_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Exercise exercise = creatExercise(resultSet);
				return exercise;
			}
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Exercise> allExercises() throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<Exercise> exercises = new ArrayList<Exercise>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_EXERCISES);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				exercises.add(creatExercise(resultSet));
			}
			return exercises;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	public static List<Exercise> allExercisesLimit(int limit) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			ArrayList<Exercise> exercises = new ArrayList<Exercise>();
			PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL_EXERCISES_LIMIT);
			preparedStatement.setInt(1, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				exercises.add(creatExercise(resultSet));
			}
			return exercises;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static Exercise creatExercise(ResultSet resultSet) throws SQLException {
		String title = resultSet.getString("title");
		String description = resultSet.getString("description");
		Exercise exercise = new Exercise(title, description);
		exercise.setId(resultSet.getLong(ID_COLUMN_NAME));
		return exercise;
	}

	public void deleteExercise(Exercise exercise) throws SQLException {
		try (Connection conn = DbUtil.getConn()) {
			if (exercise.getId() != 0) {
				PreparedStatement preparedStatement = conn.prepareStatement(DELETE_EXERCISE);
				preparedStatement.setLong(1, exercise.getId());
				preparedStatement.execute();
				exercise.setId(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
