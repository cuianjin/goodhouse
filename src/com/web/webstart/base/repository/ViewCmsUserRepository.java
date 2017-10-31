package com.web.webstart.base.repository;


import com.web.webstart.base.entity.ViewCmsUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface ViewCmsUserRepository extends
		PagingAndSortingRepository<ViewCmsUser, Long>,
		JpaSpecificationExecutor<ViewCmsUser> {

}
