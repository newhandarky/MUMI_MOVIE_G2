package web.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.member.entity.MemVO;
import web.member.service.MemService;


@WebServlet(urlPatterns = {"/view/mem/MemServlet"})
@MultipartConfig()
public class MemServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
if ("getOne_For_Display".equals(action)) { // 來自system_mem_list.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("mem_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/system_mem_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer mem_id = null;
				try {
					mem_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/system_mem_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/system_mem_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/view/mem/system_mem_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/system_mem_list.jsp");
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
				Integer mem_id = new Integer(req.getParameter("mem_id"));

				
				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
				String url = "/view/mem/mem_revise.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
							
				successView.forward(req, res);			
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/mem_revise.jsp");
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
				
				
				
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
			
				String mem_name = req.getParameter("mem_name");
				
				
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String mem_phone = req.getParameter("mem_phone").trim();

				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if(mem_phone.trim().length() < 10) {
					errorMsgs.add("手機號碼請輸入完整");
				}
				
				String mem_address = req.getParameter("mem_address").trim();
				
				
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				} 
				
				String mem_password = req.getParameter("mem_password").trim();
				if (mem_password == null || mem_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if(mem_password.trim().length() < 8) {
					errorMsgs.add("密碼長度請大於8個字元");
				}
				
				String mem_nickname = req.getParameter("mem_nickname").trim();
				
			
				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				} 
				
				// 圖片與修改日期

				Date mem_birthday ;
				try {
					mem_birthday = Date.valueOf(req.getParameter("mem_birthday").trim());
					
				} catch (IllegalArgumentException e) {
					mem_birthday = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
			
				byte[] mem_pic = ((req.getPart("mem_pic")).getInputStream()).readAllBytes();						
				
				
				Integer mem_gender = new Integer(req.getParameter("mem_gender").trim());
				System.out.println(mem_gender);
				
				MemVO memVO = new MemVO();

				memVO.setMem_name(mem_name);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_password(mem_password);
				memVO.setMem_nickname(mem_nickname);
				memVO.setMem_address(mem_address);
				memVO.setMem_birthday(mem_birthday);
				
				if(mem_pic.length == 0) {
					MemService memSvc = new MemService();
					mem_pic = memSvc.getOneMem(mem_id).getMem_pic();					
				}else {					
					memVO.setMem_pic(mem_pic);
				}
				
				memVO.setMem_gender(mem_gender);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					
					
					RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/mem_revise.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				
				
				memSvc.updateMem(mem_id, mem_name, mem_phone, mem_address, mem_password, mem_nickname, mem_birthday, 
						mem_pic, mem_gender);
				
				memVO = memSvc.getOneMem(mem_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				
				
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的memVO物件,存入req
				
				
				String url = "/view/mem/mem_revise.jsp";
				
				
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				
				
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/mem_revise.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		
		
if("updateState".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
			
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				Integer mem_state = new Integer(req.getParameter("mem_state").trim());
				

				MemService memSvc = new MemService();
				
				MemVO memVO = memSvc.getOneMem(mem_id);
				
				memSvc.updateState(memVO.getMem_id());	
				
//				System.out.println(memSvc.updateState(memVO.getMem_id()));
				
					
		
				String url = "/view/mem/system_mem_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
			} catch (Exception e) {
				// TODO: handle exception
				
				e.printStackTrace();
			}
		}				
		

if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.


			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_account = req.getParameter("mem_account").trim();
				if (mem_account == null || mem_account.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				} 
				
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if (!mem_name.trim().matches(mem_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String mem_phone = req.getParameter("mem_phone").trim();
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if(mem_phone.trim().length() < 10) {
					errorMsgs.add("手機號碼請輸入完整");
				}
				
				
				String mem_password = req.getParameter("mem_password").trim();
				if (mem_password == null || mem_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				} else if(mem_password.trim().length() < 8) {
					errorMsgs.add("密碼長度請大於8個字元");
				}
				
				String ConfirmPassword = req.getParameter("ConfirmPassword").trim();
				
				if(!mem_password.equals(ConfirmPassword)) {
					errorMsgs.add("密碼輸入不一致");
				}
				
				String mem_nickname = req.getParameter("mem_nickname").trim();
				if (mem_nickname == null || mem_nickname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}

				Date mem_register;
				try {
					mem_register = new Date(System.currentTimeMillis());					
				} catch (IllegalArgumentException e) {
					mem_register = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				InputStream defaultPic = getServletContext().getResourceAsStream("/view/mem/image/icons/user.png");

				byte[] mem_pic = defaultPic.readAllBytes();

				MemVO memVO = new MemVO();
				memVO.setMem_address(mem_account);
				memVO.setMem_name(mem_name);
				memVO.setMem_phone(mem_phone);
				memVO.setMem_password(mem_password);
				memVO.setMem_nickname(mem_nickname);
				memVO.setMem_register(mem_register);
				memVO.setMem_pic(mem_pic);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/mem_register.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemService memSvc = new MemService();
				
				memVO = memSvc.addMem(mem_account, mem_name, mem_phone, mem_password, mem_nickname, mem_register, mem_pic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/index/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				
				successView.forward(req, res);
			

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/mem_register.jsp");

				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer mem_id = new Integer(req.getParameter("mem_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(mem_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/mem/system_mem_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/mem/system_mem_list .jsp");
				failureView.forward(req, res);
			}
		}
	}

}
