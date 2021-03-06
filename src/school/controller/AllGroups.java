package school.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.Group;
import school.service.GroupService;

@WebServlet("/groups")
public class AllGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GroupService groupService = new GroupService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Group> groups = groupService.loadAllGroups();
			request.setAttribute("groups", groups);
			getServletContext().getRequestDispatcher("/WEB-INF/views/allGroups.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
