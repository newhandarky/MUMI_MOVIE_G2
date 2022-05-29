package web.info.controller;

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

import web.info.entity.InfoVO;
import web.info.service.InfoService;

@WebServlet(urlPatterns = {"/view/info/InfoServlet"})
@MultipartConfig()
public class InfoServlet extends HttpServlet{
	
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
				String str = req.getParameter("info_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入公告標題");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_info_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer info_id = null;
				try {
					info_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("公告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_info_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				InfoService infoSvc = new InfoService();
				InfoVO infoVO = infoSvc.getOneInfo(info_id);
				if (infoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_info_list.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("infoVO", infoVO); // 資料庫取出的infoVO物件,存入req
				String url = "/view/info/system_editinfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_info_list.jsp");
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
				Integer info_id = new Integer(req.getParameter("info_id"));
		
				
				/*************************** 2.開始查詢資料 ****************************************/
				InfoService infoSvc = new InfoService();
				InfoVO infoVO = infoSvc.getOneInfo(info_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("infoVO", infoVO); // 資料庫取出的infoVO物件,存入req
				String url = "/view/info/system_editinfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
							
				successView.forward(req, res);			
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_info_list.jsp");
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
				
				
				Integer info_id = new Integer(req.getParameter("info_id").trim());
				
			
				String info_title = req.getParameter("info_title");
				if (info_title == null || info_title.trim().length() == 0) {
					errorMsgs.add("公告標題請勿空白");
				} 
				
		
				byte[] info_pic = ((req.getPart("info_pic")).getInputStream()).readAllBytes();	
				
				
				String info_des = req.getParameter("info_des").trim();
				if (info_des == null || info_des.trim().length() == 0) {
					errorMsgs.add("公告內容請勿空白");
				} 
				
				
				Integer info_state = new Integer(req.getParameter("info_state").trim());
				
				
				InfoVO infoVO = new InfoVO();
		
				infoVO.setInfo_title(info_title);
				
				if(info_pic.length == 0) {
					InfoService infoSvc = new InfoService();
					info_pic = infoSvc.getOneInfo(info_id).getInfo_pic();					
				}else {					
					infoVO.setInfo_pic(info_pic);
				}
				
				infoVO.setInfo_des(info_des);
				
				infoVO.setInfo_state(info_state);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infoVO", infoVO); // 含有輸入格式錯誤的infoVO物件,也存入req	
					
					RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_editinfo.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
		
				/*************************** 2.開始修改資料 *****************************************/
				System.out.println("準備修改資料");
				InfoService infoSvc = new InfoService();
				infoSvc.updateInfo(info_id, info_title, info_pic, info_des, info_state);	
				System.out.println("為啥呢");
				infoVO = infoSvc.getOneInfo(info_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				
				req.setAttribute("infoVO", infoVO); // 資料庫update成功後,正確的的infoVO物件,存入req
				String url = "/view/info/system_info_list.jsp";			
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				
				
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_editinfo.jsp");
				
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
				String info_title = req.getParameter("info_title").trim();
				if (info_title == null || info_title.trim().length() == 0) {
					errorMsgs.add("公告標題 : 請勿空白");
				} 
				
				byte[] info_pic = ((req.getPart("info_pic")).getInputStream()).readAllBytes();	
				
				String info_des = req.getParameter("info_des");
				if (info_des == null || info_des.trim().length() == 0) {
					errorMsgs.add("公告內容 : 請勿空白");
				} 
		
				
				InfoVO infoVO = new InfoVO();
				
				infoVO.setInfo_title(info_title);
				infoVO.setInfo_pic(info_pic);
				infoVO.setInfo_des(info_des);
				
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infoVO", infoVO); // 含有輸入格式錯誤的infoVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_addinfo.jsp");
					failureView.forward(req, res);
					return;
				}
		
				/*************************** 2.開始新增資料 ***************************************/
				
				InfoService infoSvc = new InfoService();
				
				infoVO = infoSvc.addInfo(info_title, info_pic, info_des);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				
				
				String url = "/view/info/system_info_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				
				successView.forward(req, res);
			
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/info/system_addinfo.jsp");
		
				failureView.forward(req, res);
			}
		}
	}	
}
