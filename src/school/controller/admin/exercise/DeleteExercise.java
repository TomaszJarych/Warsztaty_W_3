package school.controller.admin.exercise;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.Exercise;
import school.service.ExerciseService;

@WebServlet("/deleteExercise")
public class DeleteExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExerciseService exerciseService = new ExerciseService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			Exercise exercise = exerciseService.getExerciseById(id);
			exerciseService.deleteExercise(exercise);
			request.setAttribute("exercise", exerciseService.allExercises());
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/exerciseViews/allexercise.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
