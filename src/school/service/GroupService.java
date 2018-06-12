package school.service;

import java.sql.SQLException;
import java.util.List;

import school.Dao.GroupDao;
import school.domain.Group;

public class GroupService {
	GroupDao dao = new GroupDao();

	public List<Group> loadAllGroups() throws SQLException {
		return dao.loadAllGroups();
	}
	public Group loadGroupByID(long id) throws SQLException {
			return dao.loadGroupByID(id);
	}

}
