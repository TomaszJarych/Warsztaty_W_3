package school.service;

import java.sql.SQLException;
import java.util.List;

import school.Dao.SolutionDao;
import school.domain.Solution;

public class SolutionService {
	SolutionDao dao = new SolutionDao();
	
	
	public List<Solution> allSolutionsLimit(long limit) throws SQLException{
		return dao.allSolutionsLimit(limit);
		
	}
	

}
