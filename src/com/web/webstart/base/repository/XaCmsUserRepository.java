package com.web.webstart.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.jnews.business.vo.SimpleVo;
import com.web.webstart.base.entity.XaCmsUser;


public interface XaCmsUserRepository extends PagingAndSortingRepository<XaCmsUser, Long>,JpaSpecificationExecutor<XaCmsUser> {

	@Query("select ro.roleName from XaCmsUserRole ur,XaCmsRole ro where ur.userId = ?1 and ur.roleId=ro.roleId")
	public List<String> findRoleNameByUserId(Long userId);

	@Query("from XaCmsUser xcu where xcu.userName=?1 and xcu.status=?2")
	public XaCmsUser findByUserName(String userName, int status);
	
	@Query(value="select user_id as id,real_name as name from tb_cms_user where status <> 9", nativeQuery = true)
	public List<SimpleVo> findSimpleUser();
}
