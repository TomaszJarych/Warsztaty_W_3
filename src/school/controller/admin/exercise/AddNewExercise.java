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

@WebServlet("/addNewExercise")
public class AddNewExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExerciseService exerciseService = new ExerciseService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/exerciseViews/addNewExercise.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		try {
			if (!name.isEmpty() && !description.isEmpty()) {
				Exercise exercise = new Exercise(name, description);
				exerciseService.addExercise(exercise);
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminPanelIndex.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("<h1>Nie można dodać zadania</h1>");
		}
	}

}
