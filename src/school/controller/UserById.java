package school.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.User;
import school.service.GroupService;
import school.service.SolutionService;
import school.service.UserService;

@WebServlet("/UserById")
public class UserById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
	GroupService groupService = new GroupService();
	SolutionService solutionService = new SolutionService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			User user = userService.loadUserById(id);
			request.setAttribute("user", user);
			request.setAttribute("group", groupService.loadGroupByID(user.getUserGroupId()));
			request.setAttribute("solutions", solutionService.loadAllByUserId(user));
			getServletContext().getRequestDispatcher("/WEB-INF/views/userDetails.jsp").forward(request, response);

		} catch (SQLException e) {
		}
	}
}
