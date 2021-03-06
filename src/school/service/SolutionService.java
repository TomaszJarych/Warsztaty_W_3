package school.service;

import java.sql.SQLException;
import java.util.List;

import school.Dao.SolutionDao;
import school.domain.Solution;
import school.domain.User;

public class SolutionService {
	SolutionDao dao = new SolutionDao();
	
	
	public List<Solution> allSolutionsLimit(int limit) throws SQLException{
		return dao.allSolutionsLimit(limit);
		
	}
	
	public void addSolution (Solution solution) throws SQLException{
		dao.saveToDB(solution);
	}
	
	public Solution getSolution (long id) throws SQLException {
		return dao.loadSolutionById(id);
	}
	public  List<Solution> loadAllByUserId(User user) throws SQLException {
		return dao.loadAllByUserId(user);
	}

}
