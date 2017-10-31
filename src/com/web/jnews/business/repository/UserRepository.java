package com.web.jnews.business.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.jnews.business.entity.User;


public interface UserRepository extends
		PagingAndSortingRepository<User, Integer>,
		JpaSpecificationExecutor<User> {
	
	
	
	
	
    
	
}
