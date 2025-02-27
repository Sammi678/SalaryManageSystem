package cn.com.smer.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Calculations;
import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.FixedSalarys;
import cn.com.smer.dao.dto.ImportProjects;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dao.dto.SalarySettlementDetails;
import cn.com.smer.dao.dto.SalarySettlements;
import cn.com.smer.dao.inter.CalculationsMapper;
import cn.com.smer.dao.inter.DepartmentsMapper;
import cn.com.smer.dao.inter.EmployeesMapper;
import cn.com.smer.dao.inter.FixedSalarysMapper;
import cn.com.smer.dao.inter.ImportProjectsMapper;
import cn.com.smer.dao.inter.ProjectsMapper;
import cn.com.smer.dao.inter.SalarySettlementDetailsMapper;
import cn.com.smer.dao.inter.SalarySettlementsMapper;
import cn.com.smer.dtoform.ProjectsForm;
import cn.com.smer.dtoform.SalarySettlementsForm;

@Service
public class SalarySettlementService {
	@Autowired
	private SalarySettlementsMapper salarySettlementMapper;
	@Autowired
	private EmployeesMapper employeesMapper;
	@Autowired
	private FixedSalarysMapper fixedSalarysMapper;
	@Autowired
	private ProjectsMapper projectsMapper;
	@Autowired
	private CalculationsMapper calculationsMapper;
	@Autowired
	private ImportProjectsMapper importProjectsMapper;
	@Autowired
	private SalarySettlementDetailsMapper salarySettlementDetailsMapper;
	@Autowired
	private DepartmentsMapper departmentsMapper;

	public List<SalarySettlementsForm> selctAll(SalarySettlementsForm form) {
		// 储存工资结算主表信息
		List<SalarySettlements> listSalarySettlements = new ArrayList<SalarySettlements>();
		SalarySettlements salarySettlementsSelect = new SalarySettlements();
		salarySettlementsSelect.setDepartmentId(form.getDepartmentId());
		salarySettlementsSelect.setImportDate(form.getImportDate());
		// System.out.println(salarySettlementsSelect);
		if (salarySettlementMapper.selectBySelective(salarySettlementsSelect).size() <= 0) {
			Employees employee = new Employees();
			employee.setDepartmentId(form.getDepartmentId());
			// 按部门号查询员工信息
			List<Employees> listEmployee = employeesMapper.selectBySelective(employee);
			// 按部门号查询员工固定工资信息
			List<FixedSalarys> listFixedSalarys = fixedSalarysMapper.selectByDeptIdSelective(form.getDepartmentId());
			for (Employees employee1 : listEmployee) {
				SalarySettlements salarySettlements = new SalarySettlements();
				// 计算员工的固定工资
				Long fixedSalarysum = (long) 0;
				Long basicSalary = (long) 0;
				for (FixedSalarys fixedSalary : listFixedSalarys) {
					if (fixedSalary.getEmployeeId() == employee1.getEmployeeId()) {
						// 工资项目详细表的某项信息
						ProjectsForm newproject = new ProjectsForm();
						newproject.setProjectId(fixedSalary.getProjectId());
						List<Projects> listProject = projectsMapper.selectBySelective(newproject);
						for (Projects project : listProject) {
							if (project.getProjectId() == 1) {
								basicSalary = fixedSalary.getSalary();
								// System.out.println("basicSalary"+basicSalary);
							}
							if (project.getAddorsubtract() == 0) {
								fixedSalarysum += fixedSalary.getSalary();
							} else if (project.getAddorsubtract() == 1) {
								fixedSalarysum -= fixedSalary.getSalary();
							}
						}
					}
				}
				// System.out.println("固定工资" + fixedSalarysum);
				// 计算员工的计算项目
				BigDecimal calculationtypesum = new BigDecimal(0);
				ProjectsForm newproject = new ProjectsForm();
				newproject.setProjectType((short) 2);
				// 所有计算项目
				List<Projects> listCalculationTypeProjects = projectsMapper.selectBySelective(newproject);
				for (Projects project : listCalculationTypeProjects) {
					Calculations calculations = calculationsMapper.selectByProjectId(project.getProjectId());
					// 某一项计算项目的结果
					BigDecimal calculation = new BigDecimal(0);
					if (calculations.getOperator().equals("1")) {
						calculation = new BigDecimal(basicSalary).add(calculations.getOperandtwo());
					} else if (calculations.getOperator().equals("2")) {
						calculation = new BigDecimal(basicSalary).subtract(calculations.getOperandtwo());
					} else if (calculations.getOperator().equals("3")) {
						calculation = new BigDecimal(basicSalary).multiply(calculations.getOperandtwo());
					} else {
						calculation = new BigDecimal(basicSalary).divide(calculations.getOperandtwo());
					}
					// System.out.println("calculation"+" "+project.getProjectId()+" "+calculation);
					// 所有计算项目的结果
					if (project.getAddorsubtract() == 0) {
						calculationtypesum = calculationtypesum.add(calculation);
					} else if (project.getAddorsubtract() == 1) {
						calculationtypesum = calculationtypesum.subtract(calculation);
					}
				}
				// System.out.println("计算项目" + calculationtypesum);
				// 所有导入项目
				ImportProjects importPriject = new ImportProjects();
				importPriject.setEmployeeId(employee1.getEmployeeId());
				importPriject.setImportDate(form.getImportDate());
				List<ImportProjects> listImportProjects = importProjectsMapper.selectByInfo(importPriject);
				Long importProjectsum = (long) 0;
				for (ImportProjects importProjects : listImportProjects) {
					// 工资项目详细表的某项信息
					ProjectsForm newproject1 = new ProjectsForm();
					newproject1.setProjectId(importProjects.getProjectId());
					Calculations calculations = calculationsMapper.selectByProjectId(importProjects.getProjectId());
					List<Projects> listCalculationTypeProjects1 = projectsMapper.selectBySelective(newproject1);
					for (Projects project1 : listCalculationTypeProjects1) {
						// 某一项导入项目的结果
						BigDecimal calculation = new BigDecimal(0);
						if (calculations.getOperator().equals("1")) {
							calculation = new BigDecimal(importProjects.getImportInfo())
									.add(calculations.getOperandtwo());
						} else if (calculations.getOperator().equals("2")) {
							calculation = new BigDecimal(importProjects.getImportInfo())
									.subtract(calculations.getOperandtwo());
						} else if (calculations.getOperator().equals("3")) {
							calculation = new BigDecimal(importProjects.getImportInfo())
									.multiply(calculations.getOperandtwo());
						} else {
							calculation = new BigDecimal(importProjects.getImportInfo())
									.divide(calculations.getOperandtwo());
						}
						// 所有项目的结果
						if (project1.getAddorsubtract() == 0) {
							importProjectsum = importProjectsum + calculation.longValue();
						} else if (project1.getAddorsubtract() == 1) {
							importProjectsum = importProjectsum - calculation.longValue();
						}
					}

				}
				// System.out.println("导入项目" + importProjectsum);
				salarySettlements.setEmployeeId(employee1.getEmployeeId());
				salarySettlements.setDepartmentId(employee1.getDepartmentId());
				salarySettlements.setState((long) 0);
				salarySettlements.setImportDate(form.getImportDate());
				salarySettlements.setPayDate(new Date());
				salarySettlements.setTrueSalary(fixedSalarysum + calculationtypesum.longValue() + importProjectsum);
				// System.out.println("工资详细表主表" + salarySettlements);
				listSalarySettlements.add(salarySettlements);
				if (salarySettlementMapper.insertSelective(salarySettlements) == 1) {// 构建工资结算表主表
					// 构建工资结算详细表
					// 添加固定工资
					for (FixedSalarys fixedSalary : listFixedSalarys) {
						SalarySettlementDetails salarySettlementDetails = new SalarySettlementDetails();
						salarySettlementDetails.setSalarySettlementId(salarySettlements.getSalarySettlementId());
						if (fixedSalary.getEmployeeId() == employee1.getEmployeeId()) {
							// System.out.println(employee1.getEmployeeId() + ":::" + fixedSalary);
							// 工资项目详细表的某项信息
							ProjectsForm newproject1 = new ProjectsForm();
							newproject1.setProjectId(fixedSalary.getProjectId());
							List<Projects> listProject = projectsMapper.selectBySelective(newproject1);
							salarySettlementDetails.setProjectId(fixedSalary.getProjectId());
							salarySettlementDetails.setMoney(fixedSalary.getSalary());
							for (Projects project : listProject) {
								salarySettlementDetails.setAddorsubtract(project.getAddorsubtract());
							}
							if (salarySettlementDetailsMapper.insertSelective(salarySettlementDetails) != 1) {
								// System.out.println("固定项目失败");
							}
						}
					}
					// System.out.println("固定项目成功");
					// 添加计算项目
					for (Projects project : listCalculationTypeProjects) {
						SalarySettlementDetails salarySettlementDetails = new SalarySettlementDetails();
						salarySettlementDetails.setSalarySettlementId(salarySettlements.getSalarySettlementId());
						salarySettlementDetails.setProjectId(project.getProjectId());
						salarySettlementDetails.setAddorsubtract(project.getAddorsubtract());
						Calculations calculations = calculationsMapper.selectByProjectId(project.getProjectId());
						// 某一项计算项目的结果
						BigDecimal calculation = new BigDecimal(0);
						if (calculations.getOperator().equals("1")) {
							calculation = new BigDecimal(basicSalary).add(calculations.getOperandtwo());
						} else if (calculations.getOperator().equals("2")) {
							calculation = new BigDecimal(basicSalary).subtract(calculations.getOperandtwo());
						} else if (calculations.getOperator().equals("3")) {
							calculation = new BigDecimal(basicSalary).multiply(calculations.getOperandtwo());
						} else {
							calculation = new BigDecimal(basicSalary).divide(calculations.getOperandtwo());
						}
						salarySettlementDetails.setMoney(calculation.longValue());
						if (salarySettlementDetailsMapper.insertSelective(salarySettlementDetails) != 1) {
							// System.out.println("计算项目失败");
						}
					}
					// System.out.println("计算项目成功");
					// 添加导入项目
					for (ImportProjects importProjects : listImportProjects) {
						SalarySettlementDetails salarySettlementDetails = new SalarySettlementDetails();
						salarySettlementDetails.setSalarySettlementId(salarySettlements.getSalarySettlementId());
						// 工资项目详细表的某项信息
						ProjectsForm newproject1 = new ProjectsForm();
						newproject1.setProjectId(importProjects.getProjectId());
						Calculations calculations = calculationsMapper.selectByProjectId(importProjects.getProjectId());
						List<Projects> listCalculationTypeProjects1 = projectsMapper.selectBySelective(newproject1);
						salarySettlementDetails.setProjectId(importProjects.getProjectId());
						for (Projects project1 : listCalculationTypeProjects1) {
							// 某一项导入项目的结果
							BigDecimal calculation = new BigDecimal(0);
							if (calculations.getOperator().equals("1")) {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.add(calculations.getOperandtwo());
							} else if (calculations.getOperator().equals("2")) {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.subtract(calculations.getOperandtwo());
							} else if (calculations.getOperator().equals("3")) {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.multiply(calculations.getOperandtwo());
							} else {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.divide(calculations.getOperandtwo());
							}
							salarySettlementDetails.setAddorsubtract(project1.getAddorsubtract());
							salarySettlementDetails.setMoney(calculation.longValue());
						}
						if (salarySettlementDetailsMapper.insertSelective(salarySettlementDetails) != 1) {
							// System.out.println("导入项目失败");
						}
					}
					// System.out.println("导入项目成功");
				}
			}
		} else {
			// 未处理，可修改
			if (salarySettlementMapper.selectBySelective(salarySettlementsSelect).get(0).getState() == 0) {
				List<SalarySettlements> listTemp = salarySettlementMapper.selectBySelective(salarySettlementsSelect);
				for (SalarySettlements temp : listTemp) {
					System.out.println("temp---" + temp);
					SalarySettlements salarySettlements = new SalarySettlements();
					// 计算员工的固定工资
					// 按部门号查询员工固定工资信息
					List<FixedSalarys> listFixedSalarys = fixedSalarysMapper
							.selectByDeptIdSelective(temp.getDepartmentId());
					Long fixedSalarysum = (long) 0;
					Long basicSalary = (long) 0;
					for (FixedSalarys fixedSalary : listFixedSalarys) {
						if (fixedSalary.getEmployeeId() == temp.getEmployeeId()) {
							// 工资项目详细表的某项信息
							ProjectsForm newproject = new ProjectsForm();
							newproject.setProjectId(fixedSalary.getProjectId());
							List<Projects> listProject = projectsMapper.selectBySelective(newproject);
							for (Projects project : listProject) {
								if (project.getProjectId() == 1) {
									basicSalary = fixedSalary.getSalary();
									// System.out.println("basicSalary"+basicSalary);
								}
								if (project.getAddorsubtract() == 0) {
									fixedSalarysum += fixedSalary.getSalary();
								} else if (project.getAddorsubtract() == 1) {
									fixedSalarysum -= fixedSalary.getSalary();
								}
							}
						}
					}
					// 计算员工的计算项目
					BigDecimal calculationtypesum = new BigDecimal(0);
					ProjectsForm newproject = new ProjectsForm();
					newproject.setProjectType((short) 2);
					// 所有计算项目
					List<Projects> listCalculationTypeProjects = projectsMapper.selectBySelective(newproject);
					for (Projects project : listCalculationTypeProjects) {
						Calculations calculations = calculationsMapper.selectByProjectId(project.getProjectId());
						// 某一项计算项目的结果
						BigDecimal calculation = new BigDecimal(0);
						if (calculations.getOperator().equals("1")) {
							calculation = new BigDecimal(basicSalary).add(calculations.getOperandtwo());
						} else if (calculations.getOperator().equals("2")) {
							calculation = new BigDecimal(basicSalary).subtract(calculations.getOperandtwo());
						} else if (calculations.getOperator().equals("3")) {
							calculation = new BigDecimal(basicSalary).multiply(calculations.getOperandtwo());
						} else {
							calculation = new BigDecimal(basicSalary).divide(calculations.getOperandtwo());
						}
						// System.out.println("calculation"+" "+project.getProjectId()+" "+calculation);
						// 所有计算项目的结果
						if (project.getAddorsubtract() == 0) {
							calculationtypesum = calculationtypesum.add(calculation);
						} else if (project.getAddorsubtract() == 1) {
							calculationtypesum = calculationtypesum.subtract(calculation);
						}
					}
					// 所有导入项目
					ImportProjects importPriject = new ImportProjects();
					importPriject.setEmployeeId(temp.getEmployeeId());
					importPriject.setImportDate(temp.getImportDate());
					List<ImportProjects> listImportProjects = importProjectsMapper.selectByInfo(importPriject);
					Long importProjectsum = (long) 0;
					for (ImportProjects importProjects : listImportProjects) {
						// 工资项目详细表的某项信息
						ProjectsForm newproject1 = new ProjectsForm();
						newproject1.setProjectId(importProjects.getProjectId());
						Calculations calculations = calculationsMapper.selectByProjectId(importProjects.getProjectId());
						List<Projects> listCalculationTypeProjects1 = projectsMapper.selectBySelective(newproject1);
						for (Projects project1 : listCalculationTypeProjects1) {
							// 某一项导入项目的结果
							BigDecimal calculation = new BigDecimal(0);
							if (calculations.getOperator().equals("1")) {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.add(calculations.getOperandtwo());
							} else if (calculations.getOperator().equals("2")) {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.subtract(calculations.getOperandtwo());
							} else if (calculations.getOperator().equals("3")) {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.multiply(calculations.getOperandtwo());
							} else {
								calculation = new BigDecimal(importProjects.getImportInfo())
										.divide(calculations.getOperandtwo());
							}
							// 所有项目的结果
							if (project1.getAddorsubtract() == 0) {
								importProjectsum = importProjectsum + calculation.longValue();
							} else if (project1.getAddorsubtract() == 1) {
								importProjectsum = importProjectsum - calculation.longValue();
							}
						}
					}
					salarySettlements.setSalarySettlementId(temp.getSalarySettlementId());
					salarySettlements.setEmployeeId(temp.getEmployeeId());
					salarySettlements.setDepartmentId(temp.getDepartmentId());
					salarySettlements.setState((long) 0);
					salarySettlements.setImportDate(temp.getImportDate());
					salarySettlements.setPayDate(new Date());
					salarySettlements.setTrueSalary(fixedSalarysum + calculationtypesum.longValue() + importProjectsum);
					// System.out.println("工资详细表主表" + salarySettlements);
					listSalarySettlements.add(salarySettlements);
					if (salarySettlementMapper.updateByPrimaryKeySelective(salarySettlements) == 1 && salarySettlementDetailsMapper.deleteByPrimaryKey(temp.getSalarySettlementId()) >=0) {// 构建工资结算表主表
						// 添加固定工资
						for (FixedSalarys fixedSalary : listFixedSalarys) {
							SalarySettlementDetails salarySettlementDetails = new SalarySettlementDetails();
							salarySettlementDetails.setSalarySettlementId(salarySettlements.getSalarySettlementId());
							if (fixedSalary.getEmployeeId() == temp.getEmployeeId()) {
								// System.out.println(employee1.getEmployeeId() + ":::" + fixedSalary);
								// 工资项目详细表的某项信息
								ProjectsForm newproject1 = new ProjectsForm();
								newproject1.setProjectId(fixedSalary.getProjectId());
								List<Projects> listProject = projectsMapper.selectBySelective(newproject1);
								salarySettlementDetails.setProjectId(fixedSalary.getProjectId());
								salarySettlementDetails.setMoney(fixedSalary.getSalary());
								for (Projects project : listProject) {
									salarySettlementDetails.setAddorsubtract(project.getAddorsubtract());
								}
								if (salarySettlementDetailsMapper.insertSelective(salarySettlementDetails) != 1) {
									// System.out.println("固定项目失败");
								}
							}
						}
						// 添加计算项目
						for (Projects project : listCalculationTypeProjects) {
							SalarySettlementDetails salarySettlementDetails = new SalarySettlementDetails();
							salarySettlementDetails.setSalarySettlementId(salarySettlements.getSalarySettlementId());
							salarySettlementDetails.setProjectId(project.getProjectId());
							salarySettlementDetails.setAddorsubtract(project.getAddorsubtract());
							Calculations calculations = calculationsMapper.selectByProjectId(project.getProjectId());
							// 某一项计算项目的结果
							BigDecimal calculation = new BigDecimal(0);
							if (calculations.getOperator().equals("1")) {
								calculation = new BigDecimal(basicSalary).add(calculations.getOperandtwo());
							} else if (calculations.getOperator().equals("2")) {
								calculation = new BigDecimal(basicSalary).subtract(calculations.getOperandtwo());
							} else if (calculations.getOperator().equals("3")) {
								calculation = new BigDecimal(basicSalary).multiply(calculations.getOperandtwo());
							} else {
								calculation = new BigDecimal(basicSalary).divide(calculations.getOperandtwo());
							}
							salarySettlementDetails.setMoney(calculation.longValue());
							if (salarySettlementDetailsMapper.insertSelective(salarySettlementDetails) != 1) {
								// System.out.println("计算项目失败");
							}
						}
						// System.out.println("计算项目成功");
						// 添加导入项目
						for (ImportProjects importProjects : listImportProjects) {
							SalarySettlementDetails salarySettlementDetails = new SalarySettlementDetails();
							salarySettlementDetails.setSalarySettlementId(salarySettlements.getSalarySettlementId());
							// 工资项目详细表的某项信息
							ProjectsForm newproject1 = new ProjectsForm();
							newproject1.setProjectId(importProjects.getProjectId());
							Calculations calculations = calculationsMapper
									.selectByProjectId(importProjects.getProjectId());
							List<Projects> listCalculationTypeProjects1 = projectsMapper.selectBySelective(newproject1);
							salarySettlementDetails.setProjectId(importProjects.getProjectId());
							for (Projects project1 : listCalculationTypeProjects1) {
								// 某一项导入项目的结果
								BigDecimal calculation = new BigDecimal(0);
								if (calculations.getOperator().equals("1")) {
									calculation = new BigDecimal(importProjects.getImportInfo())
											.add(calculations.getOperandtwo());
								} else if (calculations.getOperator().equals("2")) {
									calculation = new BigDecimal(importProjects.getImportInfo())
											.subtract(calculations.getOperandtwo());
								} else if (calculations.getOperator().equals("3")) {
									calculation = new BigDecimal(importProjects.getImportInfo())
											.multiply(calculations.getOperandtwo());
								} else {
									calculation = new BigDecimal(importProjects.getImportInfo())
											.divide(calculations.getOperandtwo());
								}
								salarySettlementDetails.setAddorsubtract(project1.getAddorsubtract());
								salarySettlementDetails.setMoney(calculation.longValue());
							}
							if (salarySettlementDetailsMapper.insertSelective(salarySettlementDetails) != 1) {
								// System.out.println("导入项目失败");
							}
						}

					}
				}
				System.out.println("修改");
			} else {
				// 已发放，不可修改
				listSalarySettlements = salarySettlementMapper.selectBySelective(salarySettlementsSelect);
			}
		}
		List<SalarySettlementsForm> listSalarySettlementsForm = new ArrayList<SalarySettlementsForm>();
		for (SalarySettlements salarySettlements : listSalarySettlements) {
			SalarySettlementsForm salarySettlementsForm = new SalarySettlementsForm();
			salarySettlementsForm.setSalarySettlementId(salarySettlements.getSalarySettlementId());
			salarySettlementsForm.setEmployeeId(salarySettlements.getEmployeeId());
			salarySettlementsForm.setEmployeeName(
					employeesMapper.selectByPrimaryKey(salarySettlements.getEmployeeId()).getEmployeeName());
			salarySettlementsForm.setDepartmentId(salarySettlements.getDepartmentId());
			salarySettlementsForm.setDepartmentName(
					departmentsMapper.selectByPrimaryKey(salarySettlements.getDepartmentId()).getDepartmentName());
			salarySettlementsForm.setState(salarySettlements.getState());
			if (salarySettlements.getState() == 0) {
				salarySettlementsForm.setStateName("未处理");
			} else {
				salarySettlementsForm.setStateName("已发放");
			}
			salarySettlementsForm.setImportDate(salarySettlements.getImportDate());
			salarySettlementsForm.setPayDate(salarySettlements.getPayDate());
			salarySettlementsForm.setTrueSalary(salarySettlements.getTrueSalary());
			listSalarySettlementsForm.add(salarySettlementsForm);
		}
		return listSalarySettlementsForm;
	}

	public boolean updateByParmaryKey(Long salarySettlementId, Long trueSalary) {
		SalarySettlements salarySettlements = new SalarySettlements();
		salarySettlements.setSalarySettlementId(salarySettlementId);
		salarySettlements.setTrueSalary(trueSalary);
		if (salarySettlementMapper.updateByPrimaryKeySelective(salarySettlements) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateAll(List<Long> arrSalarySettlementsId) {
		boolean result = true;
		for (Long l : arrSalarySettlementsId) {
			SalarySettlements record = new SalarySettlements();
			record.setSalarySettlementId(l);
			record.setState((long) 1);
			if (salarySettlementMapper.updateByPrimaryKeySelective(record) != 1) {
				result = false;
			}
		}
		return result;
	}

	public boolean updateTrueMoneyByEmployeeId(Map<String, Long> paramMap) {
//		Users users = usersMapper.selectByPrimaryKey(paramMap.get("userId"));
		List<SalarySettlements> list = salarySettlementMapper.selectByEmployeeId(paramMap.get("employeeId"));

		if (list != null) {
			salarySettlementMapper.updateTrueSalaryByEmployeeId(paramMap);
			for (SalarySettlements salarySettlement : list) {
				 salarySettlement.setTrueSalary(paramMap.get("newTrueSalary"));
				 System.out.println("00000000000000");
			}

			System.out.println("99999999999999999999");
			return true; // 更新成功
		} else {
			return false; // 员工不存在，更新失败
		}

	}

}
