package school.service;

import java.sql.SQLException;
import java.util.List;

import school.Dao.ExerciseDao;
import school.domain.Exercise;

public class ExerciseService {
	ExerciseDao dao = new ExerciseDao();
	
	public List<Exercise> allExercisesLimit(int limit) throws SQLException {
		return dao.allExercisesLimit(limit);
	}

	public List<Exercise> allExercises() throws SQLException {
		return dao.allExercises();
	}
	
	public void addExercise(Exercise exercise) throws SQLException{
		dao.saveToDB(exercise);
	}
	public Exercise getExerciseById (long id) throws SQLException{
		return dao.exerciseLoadById(id);
	}
	public void deleteExercise (Exercise exercise) throws SQLException {
		dao.deleteExercise(exercise);
	}
}
