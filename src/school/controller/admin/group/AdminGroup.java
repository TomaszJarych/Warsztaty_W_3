package school.controller.admin.group;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.service.GroupService;

@WebServlet("/AdminGroup")
public class AdminGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupService groupService = new GroupService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("groups", groupService.loadAllGroups());
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminGroups.jsp").forward(request,
					response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
