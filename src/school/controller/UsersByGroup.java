package school.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.User;
import school.service.UserService;

@WebServlet("/usersByGroup")
public class UsersByGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			List<User> users = userService.loadAllByGrupId(id);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/WEB-INF/views/usersByGroup.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}

}
