package web.employee.dao;

import java.util.List;

import web.employee.entity.EmployeeVO;

public interface EmployeeDAO_interface {
	public void insert(EmployeeVO employeeVO);
    public void update(EmployeeVO employeeVO);
//    public void delete(Integer emp_id);
    public EmployeeVO findByPrimaryKey(Integer emp_id);
    public List<EmployeeVO> getAll();


//  public List<MemVO> getAll(Map<String, String[]> map); 
}
	