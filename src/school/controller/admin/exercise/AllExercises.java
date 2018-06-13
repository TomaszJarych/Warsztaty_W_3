package school.controller.admin.exercise;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.service.ExerciseService;

@WebServlet("/allExercises")
public class AllExercises extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExerciseService exerciseService = new ExerciseService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("exercise", exerciseService.allExercises());
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/exerciseViews/allexercise.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
