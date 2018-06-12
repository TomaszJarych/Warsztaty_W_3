package school.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.Solution;
import school.domain.User;
import school.service.SolutionService;
import school.service.UserService;

@WebServlet("/solutionById")
public class SolutionById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SolutionService solutionService = new SolutionService();
	UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			Solution solution= solutionService.getSolution(id);
			String solutionDescription = solution.toString();
			User user = userService.loadUserById(solution.getUsers_id());
			request.setAttribute("solution", solution);
			request.setAttribute("user", user);
			getServletContext().getRequestDispatcher("/WEB-INF/views/SolutionDetails.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}  
		

	}

}
