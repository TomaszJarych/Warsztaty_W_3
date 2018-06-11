package school.service;

import java.sql.SQLException;
import java.util.List;

import school.Dao.GroupDao;
import school.domain.Group;

public class GroupService {
	GroupDao dao = new GroupDao();

	public List<Group> loadAllGroupsLimit(int limit) throws SQLException {
		return dao.loadAllGroupsLimit(limit);
	}

}
