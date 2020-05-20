package com.goaudits.business.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Personseen;
import com.goaudits.business.entity.Tag;

public interface AdvancedService {

	List<Personseen> getPersonseen(String guid);

	List<LinkedHashMap> validateaddPersonSeen(Personseen pseen);

	int addoredPersonseen(Personseen pseen);

	List<LinkedHashMap> validateeditPersonSeen(Personseen pseen);

	int editPersonseen(Personseen pseen);

	boolean validateTag(LocationTags loct);

	boolean validateEdTag(LocationTags loct);

	int addTag(LocationTags loct);

	boolean validateCategory(LocationTags loct);

	boolean validateEdCategory(LocationTags loct);

	int addTagCategory(LocationTags loct);

	List<LocationTags> getReportTag(LocationTags tag);

}
