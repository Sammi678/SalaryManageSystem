package cn.com.smer.dao.inter;

import java.util.List;

import cn.com.smer.dao.dto.FixedSalarys;

public interface FixedSalarysMapper {
	int deleteByPrimaryKey(Long fixedSalaryId);

    int insert(FixedSalarys record);

    int insertSelective(FixedSalarys record);

    FixedSalarys selectByPrimaryKey(Long fixedSalaryId);
    /*已使用*/
    int updateByPrimaryKeySelective(FixedSalarys record);
    
    int updateByPrimaryKey(FixedSalarys record);

    int updateByEmployeeId(Long employeeId);
    
	/*新建查询方法*/
    /*<!-- 新增按部门编号查询 -->*/List<FixedSalarys> selectByDeptIdSelective(Long deptId);
  
    /*<!-- 新增按员工编号查询 -->*/List<FixedSalarys> selectByEmpIdSelective(Long empId);
    
    /*<!-- 新增查询全部工资项目编号 -->*/List<Long> selectProjectIdByDepartmentId(Long deptId);
    
	/*<!-- 通过员工编号和项目编号修改工资值 -->*/int batchUpdate(FixedSalarys record);
	
	/*<!-- 查看某名员工的固定工资是否存在 -->*/FixedSalarys selectBySelective(FixedSalarys record);
    
}