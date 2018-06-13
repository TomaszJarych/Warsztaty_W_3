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

@WebServlet("/editExercise")
public class EditExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExerciseService exerciseService = new ExerciseService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			request.setAttribute("exercise", exerciseService.getExerciseById(id));
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/exerciseViews/editExercise.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		try {
			if (!name.isEmpty() && !description.isEmpty()) {
				Exercise exercise = new Exercise(name, description);
				exercise.setId(id);
				exerciseService.addExercise(exercise);
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminPanelIndex.jsp").forward(request,
					response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("<h1>Nie można dodać zadania</h1>");
		}
	}
}
