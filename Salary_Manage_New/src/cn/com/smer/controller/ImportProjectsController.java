package cn.com.smer.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.com.smer.dao.dto.Departments;
import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.ImportProjects;
import cn.com.smer.dao.dto.Projects;
import cn.com.smer.dtoform.ImportProjectsForm;
import cn.com.smer.service.DepartmentsService;
import cn.com.smer.service.EmployeesService;
import cn.com.smer.service.ImportProjectsService;
import cn.com.smer.service.ProjectsService;
/*
 * 	 导入项目数据录入
 *		1.导入项目数据维护：对某个部门某个月的导入类型的工资项目的数据值进行维护，如考勤信息和补发工资的批量录入或者修改。
 *		2.查询导入项目数据:对导入类型的工资项目的数据值进行查询，如查询某个部门某个月的考勤信息和补发工资等。
 *		
 */


@Controller
@RequestMapping(value = "/import_project")
public class ImportProjectsController {
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private ImportProjectsService importProjectsService;
	@Autowired
	private DepartmentsService departmentsService;
	@Autowired
	private EmployeesService employeesService;
	//导入项目录入
	@RequestMapping(value="/create", params = {"form"})
	public String createForm(Model model) {
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		return "/import_project/insertImport";
	}
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(ImportProjectsForm form,Model model) {
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		Long departmentId = form.getDepartmentId();
		Long employeeId = form.getEmployeeId();
		Date importDate = form.getImportDate();
		Long projectId = form.getProjectId();
		if(employeesService.selectEmployee(employeeId)) {
			model.addAttribute("selectEmployeeResultTwo", "无此员工!");
			return "/import_project/insertImport";
		}
		//查询部门里是否有录入信息中的员工
		boolean resultTwo = importProjectsService.selectemployee(departmentId, employeeId);
		if(resultTwo) {
			//判断录入信息是否已经存在
			boolean resultThree = importProjectsService.selectImportProjects(importDate, employeeId,projectId);
			if(resultThree) {
				//是否插入成功
				boolean result = importProjectsService.insertImport(form);
				if(result) {
					return "redirect:/import_project/create?complete";
				}else {
					model.addAttribute("importProjectsResult", "导入项目失败！");
					return "/import_project/insertImport";
				}	
			}else {
				model.addAttribute("selectImportsResult", "该信息已存在！");
				return "/import_project/insertImport";
			}
			
		}else {
			model.addAttribute("selectEmployeeResult", "部门没有该员工");
			return "/import_project/insertImport";
		}
	}
	@RequestMapping(value="/create", method=RequestMethod.GET,params= {"complete"})
	public String createComplete(Model model) {
		model.addAttribute("importProjectsResult","导入项目成功！");
		return "/import_project/insertImport";
	}
	
	
	//查询导入项目
	@RequestMapping(value="/listTwo", params= {"form"})
	public String listTwoForm(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		return "/import_project/selectImport";
	}
	@RequestMapping(value="/listTwo")
	public String listTwo(Model model,ImportProjectsForm form) {
		List<ImportProjects> importProjectsList = importProjectsService.selectImportProjectsTwo(form);
		model.addAttribute("importProjectsList", importProjectsList);
		List<Employees> employeesList = employeesService.getAll();
		model.addAttribute("employeesList",employeesList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		return "/import_project/selectImport";
	}
	
	
	//修改导入项目
	@RequestMapping(value="/{id}/update", params= {"form"})
	public String updateForm(@PathVariable("id") Long importProjectId,Model model) {
		ImportProjects importProjectsList = importProjectsService.selectImportProjectsById(importProjectId);
		model.addAttribute("importProjectsList", importProjectsList);
		List<Employees> employeesList = employeesService.getAll();
		model.addAttribute("employeesList",employeesList);
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		List<Projects> projectsList = projectsService.getAllProjects();
		model.addAttribute("projectsList", projectsList);
		return "/import_project/updateImport";
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST, params= {"update"})
	public String update(@PathVariable("id") Long importProjectId , ImportProjectsForm improtProjectsForm , Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		improtProjectsForm.setImportProjectId(importProjectId);
		boolean result = importProjectsService.updateImportProjects(improtProjectsForm);
		if(result) {
			return "redirect:/import_project/" + importProjectId + "/update?complete";
		}else {
			model.addAttribute("updateImportProjectResult", "更改导入信息失败！");
			return "/import_project/selectImport";
		}
	}
	@RequestMapping(value="/{id}/update", method=RequestMethod.GET, params= {"complete"})
	public String updateComplete(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		model.addAttribute("updateImportProjectResult", "更新导入信息成功!");
		return "/import_project/selectImport";
	}
	//删除
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long importProjectId,Model model) {
		boolean result = importProjectsService.deleteImportProjects(importProjectId);
		if(result) {
			return "redirect:/import_project/" + importProjectId + "/delete?complete";
		}else {
			List<Departments> departmentsList = departmentsService.getAllDepartments();
			model.addAttribute("departmentsList", departmentsList);
			model.addAttribute("deleteImportResult", "删除导入信息失败！");
			return "/import_project/selectImport";
		}
	}
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET, params= {"complete"})
	public String deleteComplete(Model model) {
		List<Departments> departmentsList = departmentsService.getAllDepartments();
		model.addAttribute("departmentsList", departmentsList);
		model.addAttribute("deleteImportResult", "删除导入信息成功!");
		return "/import_project/selectImport";
	}

}
