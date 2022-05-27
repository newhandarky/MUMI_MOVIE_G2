package web.employee.dao;

import java.util.List;

import web.employee.entity.EmployeeVO;
import web.member.entity.MemVO;

public interface EmployeeDAO_interface {
	public void insert(EmployeeVO employeeVO);
    public void update(EmployeeVO employeeVO);
    public EmployeeVO findByPrimaryKey(Integer emp_id);
    public List<EmployeeVO> getAll();
    
    public EmployeeVO findByAccount(String emp_account);
    
    public EmployeeVO login(String emp_account, String emp_password);
    
}
	