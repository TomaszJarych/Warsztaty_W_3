package school.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.Solution;
import school.service.SolutionService;
import school.service.UserService;

@WebServlet("/")
public class SolutionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SolutionService service = new SolutionService();
	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Solution solution = new Solution("Desc", 1, 1);
//		try {
//		
//			service.addSolution(solution);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		int limit = Integer.parseInt(getServletContext().getInitParameter("numberOfUsers"));
		try {
			request.setAttribute("solutions", service.allSolutionsLimit(limit));
			getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
