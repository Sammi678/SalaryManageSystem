package cn.com.smer.dao.dto;

public class Employees {
    private Long employeeId;

    private String employeeName;

    private Long departmentId;

    private Long employeerank;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getEmployeerank() { return employeerank; }

    public void setEmployeerank(Long employeerank) { this.employeerank = employeerank; }
}