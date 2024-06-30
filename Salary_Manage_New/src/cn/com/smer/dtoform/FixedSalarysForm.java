package cn.com.smer.dtoform;

import java.util.List;

public class FixedSalarysForm {
    private Long fixedSalaryId;

    private Long projectId;

    private Long employeeId;

    private Long departmentId;

    private Long salary;
    
    //checkbox数组
    private List<Long> checkbox;

    //新添加的薪资等级
    private Long employeeRank;


	public List<Long> getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(List<Long> checkbox) {
		this.checkbox = checkbox;
	}

	

	

	public Long getFixedSalaryId() {
        return fixedSalaryId;
    }

    public void setFixedSalaryId(Long fixedSalaryId) {
        this.fixedSalaryId = fixedSalaryId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getEmployeeRank() { return employeeRank; }

    public void setEmployeeRank(Long employeeRank) { this.employeeRank = employeeRank; }

	@Override
	public String toString() {
		return "FixedSalarysForm [fixedSalaryId=" + fixedSalaryId + ", projectId=" + projectId + ", employeeId="
				+ employeeId + ", departmentId=" + departmentId + ", salary=" + salary + ", checkbox=" + checkbox + "]";
	}
    
}