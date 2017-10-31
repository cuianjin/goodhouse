package com.web.webstart.base.security;

import java.util.ArrayList;
import java.util.List;

import com.web.webstart.base.service.XaCmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.entity.XaCmsUser;
import com.web.webstart.base.repository.XaCmsResourceRepository;
import com.web.webstart.base.repository.XaCmsUserRepository;

@Service("XaUserDetailsService")
@Transactional(readOnly = false)
public class XaUserDetailsService implements UserDetailsService {
	protected static final String ROLE_PREFIX = "ROLE_";
	protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(
			ROLE_PREFIX + "USER");

	@Autowired
	private XaCmsUserRepository xaCmsUserRepository;

	@Autowired
	private XaCmsUserService xaCmsUserService;

	@Autowired
	private XaCmsResourceRepository xaCmsResourceRepository;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		XaUserDetails xaUserDetails = new XaUserDetails();

		try {
			XaCmsUser user = xaCmsUserRepository.findByUserName(username,
					XaConstant.UserStatus.status_normal);

			xaCmsUserService.updateCmsUserLoginTime(user.getUserId());

			List<String> rList = xaCmsResourceRepository
					.findRoleNameByUserName(username);
			xaUserDetails.setUsername(user.getUserName());
			xaUserDetails.setPassword(user.getPassword());
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (String roleName : rList) {
				GrantedAuthority authority = new SimpleGrantedAuthority(
						roleName);
				authorities.add(authority);
			}
			xaUserDetails.setAuthorities(authorities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xaUserDetails;
	}
}
