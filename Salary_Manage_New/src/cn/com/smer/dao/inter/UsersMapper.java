package cn.com.smer.dao.inter;

import cn.com.smer.dao.dto.Users;

import java.util.List;
import java.util.Map;

public interface UsersMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

	Object selectBySelective(Users user);

    Long selectManageTypeByUserName(String Username);

    List<Users> selectAll();

    int updateManagetypeByUserId(Map<String, Long> paramMap);

}