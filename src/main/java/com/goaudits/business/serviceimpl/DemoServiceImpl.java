package com.goaudits.business.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaudits.business.entity.DemoAudits;
import com.goaudits.business.mapper.DemoAuditsMapper;
import com.goaudits.business.service.DemoServiceInterface;

@Service
public class DemoServiceImpl implements DemoServiceInterface {
	
	private final Logger log = LogManager.getLogger(getClass().getName());

	@Autowired
	DemoAuditsMapper demoMapper;
	


	@Override
	public List<DemoAudits> getDemoAuditsList() {
		
		return demoMapper.getDemoAuditsList();
	}



	@Override
	public List<DemoAudits> getManageAuditsList(DemoAudits audits) {
		List<DemoAudits> auditslist = demoMapper.getManageAuditsList(audits);

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleDateFormat2;

		for (DemoAudits a : auditslist) {

			Date date = null;
			try {
				date = simpleDateFormat.parse(a.getAudit_date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Error catched", e);
			}

			simpleDateFormat2 = new SimpleDateFormat("dd, MMM yyyy");
			String newFormatttedDate = simpleDateFormat2.format(date);
			a.setDisplay_audit_date(newFormatttedDate);
		}

		return auditslist;
	}



	@Override
	public List<DemoAudits> getManageAuditsListv1(DemoAudits audits) {
		List<DemoAudits> auditslist = demoMapper.getManageAuditsListv1(audits);

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleDateFormat2;

		for (DemoAudits a : auditslist) {

			Date date = null;
			try {
				date = simpleDateFormat.parse(a.getAudit_date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Error catched", e);
			}

			simpleDateFormat2 = new SimpleDateFormat("dd, MMM yyyy");
			String newFormatttedDate = simpleDateFormat2.format(date);
			a.setDisplay_audit_date(newFormatttedDate);
		}

		return auditslist;

	}

}
