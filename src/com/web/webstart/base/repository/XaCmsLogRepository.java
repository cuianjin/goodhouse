package com.web.webstart.base.repository;

import com.web.webstart.base.entity.XaCmsLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface XaCmsLogRepository extends
		PagingAndSortingRepository<XaCmsLog, Long>,
		JpaSpecificationExecutor<XaCmsLog> {


}
