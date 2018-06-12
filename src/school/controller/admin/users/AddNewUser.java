package school.controller.admin.users;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.User;
import school.service.GroupService;
import school.service.UserService;

@WebServlet("/addNewUser")
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupService groupService = new GroupService();
	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("groups", groupService.loadAllGroups());
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/userViews/addNewUser.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			long groupId = Long.parseLong(request.getParameter("userGroupId"));
			if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
				User user = new User(name, email, password, groupId);
				userService.save(user);
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminPanelIndex.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
