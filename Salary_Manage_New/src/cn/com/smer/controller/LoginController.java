package cn.com.smer.controller;

import cn.com.smer.dao.dto.Users;
import cn.com.smer.dao.inter.UsersMapper;
import cn.com.smer.dtoform.FixedSalarysForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.com.smer.dtoform.UsersForm;
import cn.com.smer.service.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 	登录控制
 * 		1.对于登录用户身份信息验证的功能
 */
@Controller
@SessionAttributes("userName")
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private UsersService userservice;
	@Autowired
	private UsersMapper usersMapper;

	@RequestMapping(value = "/list", params = { "form" })
	public String list(Model model) {
		model.addAttribute("flag", "");
		return "/login";
	}

	// home按钮返回main主界面
	@RequestMapping(value = "/list", params = { "main" })
	public String listMain(Model model, @ModelAttribute("userName") String userName) {
		model.addAttribute("userName", userName);
		model.addAttribute("userManageType", userservice.selectManageType(userName));
//		System.out.println(userservice.selectManageType(userName));
		return "/main";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listForm(UsersForm form, Model model) {
		if (userservice.select(form)) {
			model.addAttribute("userName", form.getUserName());
			String name = form.getUserName();
			model.addAttribute("userManageType", userservice.selectManageType(name));

			long userManageType = userservice.selectManageType(name);
//			System.out.println(userManageType);
			if (userManageType == 0) {
				return "redirect:/login/admin";
			} else if (userManageType == 1) {
				return "redirect:/login/manager";
			} else if(userManageType == 2){
				return "redirect:/login/employee";
			} else {
				model.addAttribute("flag", "flag");
				return "/login";
			}
		} else {
			model.addAttribute("flag", "flag");
			return "/login";
		}
	}

	@RequestMapping(value = "/admin")
	public String admin(UsersForm form, Model model) {
//		model.addAttribute("userName", form.getUserName());
//		String name = form.getUserName();
//		model.addAttribute("userManageType", userservice.selectManageType(name));
		if (userservice.select(form)) {
			model.addAttribute("userName", form.getUserName());
			String name = form.getUserName();
			model.addAttribute("userManageType", userservice.selectManageType(name));
//			System.out.println(userservice.selectManageType(name));

		}
		List<Users> userList = userservice.selectAll(); // Assuming User is the class representing a user
		model.addAttribute("userList", userList);
//		System.out.println(userList);

		return "/admin";
	}


	@RequestMapping(value = "/admin/update/{userId}")
	public String update(@PathVariable("userId") Long userId, Model model) {
		Users user = userservice.selectByPrimaryKey(userId);
		model.addAttribute("user", user);

		return "/admin_update";

	}

	@RequestMapping(value = "/admin/detail/{userId}")
	public String detail(@PathVariable("userId") Long userId, Model model, UsersForm form) {

		return  "/admin_detail";
	}

	@RequestMapping(value = "/admin_detail/{userId}")
	public String detail(@PathVariable("userId") Long userId, UsersForm form, @RequestParam("userId2") Long userId2, @RequestParam("userName") String userName,
						 @RequestParam("userPassword") String userPassword, @RequestParam("managetype") Long managetype) {

		Users user = new Users();
		user.setUserId(userId2);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setManagetype(managetype);
		form.setUserId(userId2);
		form.setUserName(userName);
		form.setUserPassword(userPassword);
		form.setManagetype(managetype);

		boolean result = userservice.insertNewUser(form);

		System.out.println("88888888888"+ result);
		return  "/admin_detail";
	}

	@RequestMapping(value = "/admin_update/{userId}")
	public String updateform(@PathVariable("userId") Long userId, Model model, @RequestParam("newManagetype") Long newManagetype){

		Map<String, Long> paramMap = new HashMap<>();
		paramMap.put("newManagetype", newManagetype);
		paramMap.put("userId", userId);
		boolean resultManagetype = userservice.updateManagetypeByUserId(paramMap);


		if(resultManagetype){
			return "/admin_update";
		}else {
			return "/admin_update";
		}
	}

//	@RequestMapping(value = "/update")
//	public String admin_update(@PathVariable("userId") Long userId, Model model) {
//		return "/admin_update";
//	}
//
	@RequestMapping(value = "/admin_detail")
	public String admin_detail(UsersForm form, Model model) {
//		model.addAttribute("");
		return "/admin_detail";
	}




	@RequestMapping(value = "/manager")
	public String manager(UsersForm form, Model model, @ModelAttribute("userName") String userName) {
		if (userservice.select(form)) {
			model.addAttribute("userName", form.getUserName());
			String name = form.getUserName();
			model.addAttribute("userManageType", userservice.selectManageType(name));
//			System.out.println(userservice.selectManageType(name));
		}
		return "/manager";
	}
	@RequestMapping(value = "/employee")
	public String employee(UsersForm form, Model model, @ModelAttribute("userName") String userName) {
		if (userservice.select(form)) {
			model.addAttribute("userName", form.getUserName());
			String name = form.getUserName();
			model.addAttribute("userManageType", userservice.selectManageType(name));
//			System.out.println(userservice.selectManageType(name));
		}
		return "/employee";
	}



//	@RequestMapping(value = "/list", method = RequestMethod.POST)
//	public String listForm(UsersForm form, Model model) {
//		if (userservice.select(form)) {
//			model.addAttribute("userName", form.getUserName());
//			String name = form.getUserName();
//			model.addAttribute("userManageType", userservice.selectManageType(name));
////			System.out.println(userservice.selectManageType(name));
//			return "redirect:/login/list?main";
//		} else {
//			model.addAttribute("flag", "flag");
//			return "/login";
//		}
//	}



//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String registerForm(UsersForm form, Model model) {
//		boolean registrationSuccess = userservice.register(form);
//		if (registrationSuccess) {
//			model.addAttribute("registrationSuccessMessage", "注册成功！");
//			return "/login"; // 重定向到主页面
//		} else {
//			model.addAttribute("registrationFailureMessage", "注册失败，请重试或联系管理员！");
//			return "/login";
//		}
//	}
}
