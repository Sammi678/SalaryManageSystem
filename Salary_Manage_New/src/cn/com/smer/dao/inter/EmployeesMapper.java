package cn.com.smer.dao.inter;

import java.util.List;
import java.util.Map;

import cn.com.smer.dao.dto.Employees;

public interface EmployeesMapper {
    int deleteByPrimaryKey(Long employeeId);

    int insert(Employees record);

    int insertSelective(Employees record);

    Employees selectByPrimaryKey(Long employeeId);

    int updateByPrimaryKeySelective(Employees record);

    int updateByPrimaryKey(Employees record);

    int updateEmployeeRank(Map<String, Long> paramMap);
    
	/*<!-- sxb使用：通过部门编号查询全体员工姓名编号 -->*/
    List<Employees> selectByDepartmentId(Long deptid);
    /*<!-- sxb使用： -->*/
    List<Employees> selectBySelective(Employees employee);
	
	Long selectDepartmentIdByEmployeeId(Long employeeId);
	
	List<Employees> selectAll();
	//zx通过ID查询部门//sxb借用
	Employees selectEmployeeById(Long employeeId);
	List<Employees> selectEmployees(Long employeeId);

    Long selectRankByEmployeeId(Long employeeId);

	
}