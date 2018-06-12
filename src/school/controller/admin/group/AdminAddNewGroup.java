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

@WebServlet("/addNewGroup")
public class AdminAddNewGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GroupService groupService = new GroupService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("//WEB-INF/views/adminViews/newGroup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String groupName = request.getParameter("name");
		Group group = new Group(groupName);
		try {
			groupService.addGroup(group);
			getServletContext().getRequestDispatcher("/WEB-INF/views/adminViews/adminPanelIndex.jsp").forward(request,response);

		} catch (SQLException e) {
			response.getWriter().append("<h1>Blad. Grupa nie zostala dodana</h1>");
			e.printStackTrace();
		}
		
	}

}
