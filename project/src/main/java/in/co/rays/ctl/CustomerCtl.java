package in.co.rays.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.CustomerBean;
import in.co.rays.model.CustomerModel;

@WebServlet("/CustomerCtl.do")
public class CustomerCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		System.out.println("id =====> " + id);

		if (id != null) {

			CustomerModel model = new CustomerModel();

			try {
				CustomerBean bean = model.findByPk(Integer.parseInt(id));
				req.setAttribute("bean", bean);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = req.getRequestDispatcher("CustomerView.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String op = req.getParameter("operation");

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String amount = req.getParameter("amount");
		String accoountNumber = req.getParameter("accoountNumber");
		String dob = req.getParameter("dob");
		String cid = req.getParameter("customerId");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		CustomerBean bean = new CustomerBean();
		bean.setName(name);
		bean.setAmount(amount);
		bean.setAccoountNumber(accoountNumber);
		try {
			bean.setDob(sdf.parse(dob));
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		bean.setCustomerId(cid);

		CustomerModel model = new CustomerModel();

		if (op.equals("save")) {
			try {
				model.add(bean);
				req.setAttribute("msg", "Customer added Successfully..!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (op.equals("update")) {
			bean.setId(Integer.parseInt(id));
			try {
				model.update(bean);
				bean = model.findByPk(bean.getId());
				req.setAttribute("msg", "Customer updated Successfully..!!");
				req.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = req.getRequestDispatcher("CustomerView.jsp");
		rd.forward(req, resp);

	}

}
