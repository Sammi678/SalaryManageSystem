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
			return true; // ���³ɹ�
		} else {
			return false; // Ա�������ڣ�����ʧ��
		}
	}

//	public boolean insertNewUser(Users user) {
//
//	}
	public boolean insertNewUser(UsersForm usersForm) {
		//���ݵĿ�����Ҫ��֤���������е�������һ��
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
//		int result = usersMapper.insert(user); // ����UsersMapper�е�insert�������û���Ϣ�������ݿ�
//		return result > 0;  // ��������¼�ɹ��򷵻�true
//	}

}
