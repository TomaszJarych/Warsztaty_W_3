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
@WebServlet("/editUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private GroupService groupService = new GroupService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			request.setAttribute("user", userService.loadUserById(id));
			request.setAttribute("groups", groupService.loadAllGroups());
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/userViews/editUser.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			long groupId = Long.parseLong(request.getParameter("userGroupId"));
			long id = Long.parseLong(request.getParameter("id"));
			if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
				User user = userService.loadUserById(id);
				user.setUserName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setUserGroupId(groupId);
				userService.save(user);
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminPanelIndex.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
