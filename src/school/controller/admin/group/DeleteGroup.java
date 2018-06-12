package school.controller.admin.group;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import school.domain.Group;
import school.service.GroupService;

@WebServlet("/deleteGroup")
public class DeleteGroup extends HttpServlet {
	private GroupService groupService = new GroupService();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			Group group = groupService.loadGroupByID(id);
			groupService.deleteGroup(group);
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminPanelIndex.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

}
