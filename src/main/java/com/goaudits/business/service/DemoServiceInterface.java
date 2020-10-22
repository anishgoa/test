package com.goaudits.business.service;

import java.util.List;

import com.goaudits.business.entity.DemoAudits;


public interface DemoServiceInterface {
	List<DemoAudits> getDemoAuditsList();

	List<DemoAudits> getManageAuditsList(DemoAudits audits);

	List<DemoAudits> getManageAuditsListv1(DemoAudits audits);
}
