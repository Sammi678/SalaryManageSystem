package cn.com.smer.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import cn.com.smer.dao.dto.Employees;
import cn.com.smer.dao.dto.ImportProjects;
import cn.com.smer.dtoform.ImportProjectsForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.smer.dao.dto.Users;
import cn.com.smer.dao.inter.UsersMapper;
import cn.com.smer.dtoform.UsersForm;

@Service
public class UsersService {
	@Autowired
	private UsersMapper usersMapper;

	public boolean select(UsersForm form){
		Users user = new Users();
		BeanUtils.copyProperties(form,user);
		//System.out.println(user);
		if (usersMapper.selectBySelective(user) != null) {
			return true;
		} else {
			return false;
		}
	}

	public long selectManageType(String Username){
		return usersMapper.selectManageTypeByUserName(Username);

	}

	public List<Users> selectAll() {
		return usersMapper.selectAll();
	}

	public Users selectByPrimaryKey(long userId) {
		return usersMapper.selectByPrimaryKey(userId);
	}

	public boolean updateManagetypeByUserId(Map<String, Long> paramMap) {

		Users users = usersMapper.selectByPrimaryKey(paramMap.get("userId"));
		if (users != null) {
			users.setManagetype(paramMap.get("newManagetype"));
			usersMapper.updateManagetypeByUserId(paramMap);
			return true; // 更新成功
		} else {
			return false; // 员工不存在，更新失败
		}
	}

//	public boolean insertNewUser(Users user) {
//
//	}
	public boolean insertNewUser(UsersForm usersForm) {
		//数据的拷贝，要保证两个对象中的属性名一致
		Users users = new Users();
		BeanUtils.copyProperties(usersForm, users);
		int count = usersMapper.insert(users);
		if(count == 1){
			return true;
		}else {
			return false;
		}
	}

//	public boolean register(UsersForm form) {
//		Users user = new Users();
//		BeanUtils.copyProperties(form, user);
//		int result = usersMapper.insert(user); // 调用UsersMapper中的insert方法将用户信息插入数据库
//		return result > 0;  // 如果插入记录成功则返回true
//	}

}
