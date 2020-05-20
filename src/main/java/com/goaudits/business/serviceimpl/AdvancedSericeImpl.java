package com.goaudits.business.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Personseen;
import com.goaudits.business.entity.Tag;
import com.goaudits.business.mapper.AdvancedMapper;
import com.goaudits.business.service.AdvancedService;

@Service
public class AdvancedSericeImpl implements AdvancedService {

	@Autowired
	AdvancedMapper advancedmapper;

	public List<Personseen> getPersonseen(String guid) {

		List<Personseen> pseenlist = advancedmapper.getPersonseenList(guid);

		for (Personseen psen : pseenlist) {

			List<Company> cllist = advancedmapper.getpersonseenClients(guid, psen.getPerson_email());
			psen.getClientarr().addAll(cllist);

			for (Company c : cllist) {

				List<AuditName> atlist = advancedmapper.getPersonseenAudits(guid, psen.getPerson_email(),
						c.getClient_id());
				c.getAudarry().addAll(atlist);

				for (AuditName a : atlist) {
					List<Location> starr = advancedmapper.getPersonseenLocations(guid, psen.getPerson_email(),
							c.getClient_id(), a.getAudit_type_id());
					a.getStrarry().addAll(starr);

				}

			}

		}

		return pseenlist;
	}

	@Override
	public List<LinkedHashMap> validateaddPersonSeen(Personseen pseen) {
		return advancedmapper.validateaddPersonSeen(pseen);
	}

	@Override
	public int addoredPersonseen(Personseen pseen) {
		int storelength = 0;
		int auditlength = 0;
		String storeids[] = pseen.getStore_ids().split(",");
		String auditids[] = pseen.getAudit_type_ids().split(",");
		if (pseen.getStore_ids().equals("")) {
			storelength = 0;
		} else {
			storelength = storeids.length;
		}

		if (pseen.getAudit_type_ids().equals("")) {
			auditlength = 0;

		} else {
			auditlength = auditids.length;

		}

		if (storelength > 0) {

			for (int i = 0; i < storelength; i++) {

				if (auditlength > 0) {

					for (int j = 0; j < auditlength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[j]));
						pseen.setStore_id(Integer.parseInt(storeids[i]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(0);
					pseen.setStore_id(Integer.parseInt(storeids[i]));
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else if (auditlength > 0) {

			for (int i = 0; i < auditlength; i++) {

				if (storelength > 0) {

					for (int j = 0; j < storelength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
						pseen.setStore_id(Integer.parseInt(storeids[j]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
					pseen.setStore_id(0);
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else {
			pseen.setAudit_type_id(0);
			pseen.setStore_id(0);
			advancedmapper.addorEditPersonSeen(pseen);
		}

		return 0;
	}

	@Override
	public List<LinkedHashMap> validateeditPersonSeen(Personseen pseen) {
		return advancedmapper.validateeditPersonSeen(pseen);
	}

	@Override
	public int editPersonseen(Personseen pseen) {
		advancedmapper.deletePersonSeen(pseen);

		int storelength = 0;
		int auditlength = 0;
		String storeids[] = pseen.getStore_ids().split(",");
		String auditids[] = pseen.getAudit_type_ids().split(",");
		if (pseen.getStore_ids().equals("")) {
			storelength = 0;
		} else {
			storelength = storeids.length;
		}

		if (pseen.getAudit_type_ids().equals("")) {
			auditlength = 0;

		} else {
			auditlength = auditids.length;

		}

		if (storelength > 0) {

			for (int i = 0; i < storelength; i++) {

				if (auditlength > 0) {

					for (int j = 0; j < auditlength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[j]));
						pseen.setStore_id(Integer.parseInt(storeids[i]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(0);
					pseen.setStore_id(Integer.parseInt(storeids[i]));
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else if (auditlength > 0) {

			for (int i = 0; i < auditlength; i++) {

				if (storelength > 0) {

					for (int j = 0; j < storelength; j++) {

						pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
						pseen.setStore_id(Integer.parseInt(storeids[j]));
						advancedmapper.addorEditPersonSeen(pseen);

					}

				} else {
					pseen.setAudit_type_id(Integer.parseInt(auditids[i]));
					pseen.setStore_id(0);
					advancedmapper.addorEditPersonSeen(pseen);

				}

			}

		} else {
			pseen.setAudit_type_id(0);
			pseen.setStore_id(0);
			advancedmapper.addorEditPersonSeen(pseen);

		}
		return 0;
	}

	@Override
	public boolean validateTag(LocationTags loct) {
		return advancedmapper.validateTags(loct) > 0 ? true : false;
	}

	@Override
	public boolean validateEdTag(LocationTags loct) {
		return advancedmapper.validateedTags(loct) > 0 ? true : false;
	}

	@Override
	public int addTag(LocationTags loct) {
		return advancedmapper.addTags(loct);
	}

	@Override
	public boolean validateCategory(LocationTags tgs) {

		return advancedmapper.validateCategory(tgs) > 0 ? true : false;
	}

	@Override
	public boolean validateEdCategory(LocationTags tgs) {
		return advancedmapper.validateEdCategory(tgs) > 0 ? true : false;
	}

	@Override
	public int addTagCategory(LocationTags tgs) {
		return advancedmapper.addTagCategory(tgs);
	}

	@Override
	public List<LocationTags> getReportTag(LocationTags tag) {
		List<LocationTags> tgsCaList = advancedmapper.getTagCategories(tag.getGuid());

		for (LocationTags lt : tgsCaList) {

			List<LocationTags> tgsList = advancedmapper.getTagList(tag.getGuid(), lt.getCategory_id());

			lt.getTagsList().addAll(tgsList);

		}

		return tgsCaList;

	}

}
