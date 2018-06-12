package school.controller.admin.users;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.User;
import school.service.UserService;
@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			User user = userService.loadUserById(id);
			userService.deleteUser(user);
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminPanelIndex.jsp").forward(request,response);

		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("<h1>Nie mozna usunac uzytkownika</h1>");
		}
		
	}

}
