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

}
