package web.employee.service;

import java.util.List;

import web.employee.dao.EmployeeDAO;
import web.employee.dao.EmployeeDAO_interface;
import web.employee.entity.EmployeeVO;

public class EmployeeService {

	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeDAO();
	}
	
	public EmployeeVO addEmp(String emp_account, String emp_name, String emp_password, String emp_nickname,
			String emp_phone, java.sql.Date emp_hiredate) {

		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setEmp_account(emp_account);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_password(emp_password);
		employeeVO.setEmp_nickname(emp_nickname);
		employeeVO.setEmp_phone(emp_phone);
		employeeVO.setEmp_hiredate(emp_hiredate);
		dao.insert(employeeVO);

		return employeeVO;
	}
	
	public EmployeeVO updateEmp(Integer emp_id, String emp_account, String emp_name, String emp_password, String emp_nickname,
			String emp_phone, java.sql.Date emp_hiredate) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmp_id(emp_id);
		employeeVO.setEmp_account(emp_account);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_password(emp_password);
		employeeVO.setEmp_nickname(emp_nickname);
		employeeVO.setEmp_phone(emp_phone);
		employeeVO.setEmp_hiredate(emp_hiredate);
		dao.update(employeeVO);

		return employeeVO;
	}
	
//	public void deleteMem(Integer mem_id) {
//		dao.delete(mem_id);
//	}
//
	public EmployeeVO getOneEmployee(Integer emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}
	
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}

}
