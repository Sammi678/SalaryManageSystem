package cn.com.smer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.inter.EmployeesMapper;

@Service
public class EmployeesService {
	@Autowired
	private EmployeesMapper employeesDao;
	
	public List<Employees> selectByDepartmentId(Long deptid){
		List<Employees> empAll = employeesDao.selectByDepartmentId(deptid);
		
		return empAll;
	}
	public List<Employees> getAll(){
		return employeesDao.selectAll();
	}
	//��ѯ�Ƿ������Ա��
	public boolean selectEmployee(Long employeeId) {
		List<Employees> emp = employeesDao.selectEmployees(employeeId);
		if(emp.size()==0) {
			return true;
		}else {
			return false;
		}
	}
	//sxb
	public String selectNameByEmployeeId(Long EmployeeId) {
		Employees a = new Employees();
		a = employeesDao.selectEmployeeById(EmployeeId);
		if(a != null)
		return a.getEmployeeName();
		else return null;
	}

	public Long selectRankByEmployeeId(Long employeeId) {
		Long rank = employeesDao.selectRankByEmployeeId(employeeId);
		return rank; // 返回员工的薪资等级信息
	}

//	public boolean updateEmployeeRank(Long employeeId, Long newRank) {
//		Employees employee = employeesDao.selectEmployeeById(employeeId);
//		if (employee != null) {
//			employee.setEmployeerank(newRank);
//			employeesDao.updateEmployeeRank(employeeId, newRank);
//			return true; // 更新成功
//		} else {
//			return false; // 员工不存在，更新失败
//		}
//	}


	public boolean updateEmployeeRank(Map<String, Long> paramMap) {
		Employees employee = employeesDao.selectEmployeeById(paramMap.get("employeeId"));
		if (employee != null) {
			employee.setEmployeerank(paramMap.get("newRank"));
			employeesDao.updateEmployeeRank(paramMap);
			return true; // 更新成功
		} else {
			return false; // 员工不存在，更新失败
		}
	}
}
