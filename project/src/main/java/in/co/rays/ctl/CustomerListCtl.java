package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.CustomerBean;
import in.co.rays.model.CustomerModel;

@WebServlet("/CustomerListCtl.do")
public class CustomerListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CustomerModel model = new CustomerModel();
		try {
			List list = model.search(null, 0, 0);

			req.setAttribute("list", list);
			System.out.println("list>>>>>> " + list.toString());
			RequestDispatcher rd = req.getRequestDispatcher("CustomerListView.jsp");
			rd.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CustomerBean bean = new CustomerBean();

		CustomerModel model = new CustomerModel();
		String op = req.getParameter("operation");

		if (op.equals("search")) {
			System.out.println("name>> " + req.getParameter("fname"));
			String name = req.getParameter("fname");
			bean.setName(name);
		}
		if (op.equals("add")) {

			resp.sendRedirect("CustomerView.jsp");

		}
		if (op.equals("delete")) {

			String[] ids = req.getParameterValues("ids");

			for (String id : ids) {

				try {
					model.delete(Integer.parseInt(id));
					resp.sendRedirect("CustomerListCtl.do");

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		try {
			List list = model.search(bean, 0, 0);
			System.out.println("list>>>>>> " + list.toString());
			req.setAttribute("list", list);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		RequestDispatcher rd = req.getRequestDispatcher("CustomerListView.jsp");
		rd.forward(req, resp);

	}

}
