package web.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.employee.entity.EmployeeVO;
import web.employee.service.EmployeeService;


@WebServlet(urlPatterns = {"/view/employee/EmployeeServlet"})
@MultipartConfig()
public class EmployeeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("emp_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer emp_id = null;
				try {
					emp_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(emp_id);
				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/view/employee/system_employee_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_list.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer emp_id = new Integer(req.getParameter("emp_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(emp_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/view/employee/system_employee_revise.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_data.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer emp_id = new Integer(req.getParameter("emp_id").trim());
				String emp_nickname = new String(req.getParameter("emp_nickname").trim());
				
				String emp_account = req.getParameter("emp_account");
				String emp_account_reg = "^[A-Za-z0-9_]+$";
				if (emp_account == null || emp_account.trim().length() == 0) {
					errorMsgs.add("員工帳號: 請勿空白");
				} else if (!emp_account.trim().matches(emp_account_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工帳號: 只能是英文字母、數字和_");
				}
				
				String emp_name = req.getParameter("emp_name");
				String emp_name_reg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_name_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String emp_password = req.getParameter("emp_password");
				String emp_password_reg = "^[a-zA-Z0-9]+$";
				if (emp_password == null || emp_password.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!emp_password.trim().matches(emp_password_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字");
				}
				
				String emp_phone = req.getParameter("emp_phone");
				String emp_phone_reg = "^[0-9]{10}$";
				if (emp_phone == null || emp_phone.trim().length() == 0) {
					errorMsgs.add("手機: 請勿空白");
				} else if (!emp_phone.trim().matches(emp_phone_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機: 只能是數字，且長度必需為10");
				}

				java.sql.Date emp_hiredate = null;
				try {
					emp_hiredate = java.sql.Date.valueOf(req.getParameter("emp_hiredate").trim());
				} catch (IllegalArgumentException e) {
					emp_hiredate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Integer emp_state = new Integer(req.getParameter("emp_state").trim());

				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmp_id(emp_id);
				employeeVO.setEmp_account(emp_account);
				employeeVO.setEmp_name(emp_name);
				employeeVO.setEmp_password(emp_password);
				employeeVO.setEmp_nickname(emp_nickname);
				employeeVO.setEmp_phone(emp_phone);
				employeeVO.setEmp_hiredate(emp_hiredate);
				employeeVO.setEmp_state(emp_state);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_data.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				
//				employeeVO = employeeSvc.getOneEmployee(emp_id);
				
				employeeVO = employeeSvc.updateEmp(emp_id, emp_account, emp_name, emp_password, emp_nickname, emp_phone, emp_hiredate, emp_state);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/view/employee/system_employee_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_data.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String emp_nickname = new String(req.getParameter("emp_nickname").trim());
				
				String emp_account = req.getParameter("emp_account");
				String emp_account_reg = "^[A-Za-z0-9_]+$";
				if (emp_account == null || emp_account.trim().length() == 0) {
					errorMsgs.add("員工帳號: 請勿空白");
				} else if (!emp_account.trim().matches(emp_account_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工帳號: 只能是英文字母、數字和_");
				}
				
				String emp_name = req.getParameter("emp_name");
				String emp_name_reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_name_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String emp_password = req.getParameter("emp_password");
				String emp_password_reg = "^[a-zA-Z0-9]+$";
				if (emp_password == null || emp_password.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!emp_password.trim().matches(emp_password_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字");
				}
//				String confirmPassword = req.getParameter("confirmPassword").trim();
//				if(!emp_password.equals(confirmPassword)) {
//					errorMsgs.add("密碼輸入不一致");
//				}
				
				String emp_phone = req.getParameter("emp_phone");
				String emp_phone_reg = "^[0-9]{10}$";
				if (emp_phone == null || emp_phone.trim().length() == 0) {
					errorMsgs.add("手機: 請勿空白");
				} else if (!emp_phone.trim().matches(emp_phone_reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機: 只能是數字，且長度必需為10");
				}
								
				java.sql.Date emp_hiredate = null;
				try {
					emp_hiredate = java.sql.Date.valueOf(req.getParameter("emp_hiredate").trim());
				} catch (IllegalArgumentException e) {
					emp_hiredate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Integer emp_state = new Integer(req.getParameter("emp_state").trim());

				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmp_account(emp_account);
				employeeVO.setEmp_name(emp_name);
				employeeVO.setEmp_password(emp_password);
				employeeVO.setEmp_nickname(emp_nickname);
				employeeVO.setEmp_phone(emp_phone);
				employeeVO.setEmp_hiredate(emp_hiredate);
				employeeVO.setEmp_state(emp_state);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.addEmp(emp_account, emp_name, emp_password, emp_nickname, emp_phone, emp_hiredate, emp_state);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/employee/system_employee_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/employee/system_employee_add.jsp");
				failureView.forward(req, res);
			}
		}

//
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				Integer mem_id = new Integer(req.getParameter("mem_id"));
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				MemService memSvc = new MemService();
//				memSvc.deleteMem(mem_id);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/view/mem/system_mem_list.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/system_mem_list .jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

}
