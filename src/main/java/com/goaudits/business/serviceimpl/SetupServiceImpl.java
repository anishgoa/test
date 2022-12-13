package com.goaudits.business.serviceimpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.goaudits.business.entity.ActionPlanAssignee;
import com.goaudits.business.entity.AuditName;
import com.goaudits.business.entity.Choice;
import com.goaudits.business.entity.Company;
import com.goaudits.business.entity.EmailMessage;
import com.goaudits.business.entity.EmailSubject;
import com.goaudits.business.entity.EmailTemplate;
import com.goaudits.business.entity.Group;
import com.goaudits.business.entity.GuidedSetup;
import com.goaudits.business.entity.Help;
import com.goaudits.business.entity.Location;
import com.goaudits.business.entity.LocationTags;
import com.goaudits.business.entity.Menu;
import com.goaudits.business.entity.PreTemplates;
import com.goaudits.business.entity.Questactimage;
import com.goaudits.business.entity.Question;
import com.goaudits.business.entity.Report;
import com.goaudits.business.entity.ReportImage;
import com.goaudits.business.entity.Reportref;
import com.goaudits.business.entity.ScheduleDefTim;
import com.goaudits.business.entity.ScoreRange;
import com.goaudits.business.entity.Section;
import com.goaudits.business.entity.User;
import com.goaudits.business.mapper.SetupMapper;
import com.goaudits.business.service.SetupService;
import com.goaudits.business.util.Constants;
import com.goaudits.business.util.Utils;

@Service
public class SetupServiceImpl implements SetupService {
	private final Logger log = LogManager.getLogger(getClass().getName());

	@Autowired
	SetupMapper setupmapper;

	@Override
	public List<Company> getCompanyList(Company company) {

		List<Company> CompanyList = setupmapper.getCompanyList(company);

		return CompanyList;

	}

	@Override
	public List<Location> getLocationsBasedonCompany(Location location) {

		return setupmapper.getLocationsBasedonCompany(location);
	}

	@Override
	public List<AuditName> getAuditNamesByCompany(AuditName auditname) {

		return setupmapper.getAuditNamesByCompany(auditname);
	}

	@Override
	public boolean isCompanyExists(Company company) {

		return (setupmapper.getAddCompanyCount(company)) == 0 ? false : true;
	}

	@Override
	public Company addCompany(Company company) {

		byte[] clientimage = null;
		String[] imagesrc = null;

		if (company.getLogo() != null && !(company.getLogo().trim().isEmpty())) {
			imagesrc = company.getLogo().split(",");
			try {
				clientimage = Utils.Base64ToBytes(imagesrc[1]);
				company.setLogo_binary(clientimage);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("Error catched", e);
			}
		}

		Company compy = setupmapper.addOrUpdateCompany(company);
		List<ActionPlanAssignee> actionlist = company.getActionlist();

		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(company.getGuid());
			act.setClient_id(compy.getClient_id());
			setupmapper.insertActionemailforCompany(act);
		}
		return compy;
	}

	@Override
	public boolean isCompanyExistInDB(Company company) {

		return (setupmapper.getEditCompanyCount(company)) == 0 ? false : true;

	}

	@Override
	public Company updateCompany(Company company) {

		byte[] clientimage = null;
		String[] imagesrc = null;

		if (company.getLogo() != null && !(company.getLogo().trim().isEmpty())) {
			imagesrc = company.getLogo().split(",");
			try {
				clientimage = Utils.Base64ToBytes(imagesrc[1]);
				company.setLogo_binary(clientimage);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("Error catched", e);
			}
		}

		Company compy = setupmapper.addOrUpdateCompany(company);

		if (!company.isIsdelete()) {
			List<ActionPlanAssignee> actionlist = company.getActionlist();
			setupmapper.deleteActionemailCompany(company.getGuid(), company.getClient_id());
			for (ActionPlanAssignee act : actionlist) {

				act.setGuid(company.getGuid());
				act.setClient_id(compy.getClient_id());
				setupmapper.insertActionemailforCompany(act);
			}
		}
		return compy;
	}

	@Override
	public void companyReOrder(Company company) {
		setupmapper.companyReorder(company);
	}

	@Override
	public boolean isLocationExist(Location location) {

		return (setupmapper.getLocationExits(location) > 0) ? true : false;
	}

	@Override
	public String addLocation(Location location) {

		String store_id = setupmapper.addOrUpdateLocation(location);

		List<ActionPlanAssignee> actionlist = location.getActionlist();
		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(location.getGuid());
			act.setClient_id(location.getClient_id());
			act.setStore_id(store_id);
			setupmapper.insertLoationActionemail(act);
		}

		List<LocationTags> tagslist = location.getTagslist();

		for (LocationTags t : tagslist) {

			t.setGuid(location.getGuid());
			t.setClient_id(location.getClient_id());
			t.setStore_id(store_id);
			setupmapper.addLocationTags(t);

		}
		return store_id;
	}

	@Override
	public boolean isLocationExistInDB(Location location) {

		return (setupmapper.getLocationExitsInDb(location) > 0) ? true : false;
	}

	@Override
	public String updateLocation(Location location) {
		String store_id = setupmapper.addOrUpdateLocation(location);

		List<LocationTags> tagslist = location.getTagslist();

		if (!location.isIsdelete()) {
			setupmapper.deleteLocationTags(location.getGuid(), location.getClient_id(), store_id);
			for (LocationTags t : tagslist) {
				t.setGuid(location.getGuid());
				t.setClient_id(location.getClient_id());
				t.setStore_id(store_id);
				setupmapper.addLocationTags(t);

			}
			List<ActionPlanAssignee> actionlist = location.getActionlist();
			setupmapper.deleteLocationActionemail(location.getGuid(), location.getClient_id(), store_id);
			for (ActionPlanAssignee act : actionlist) {

				act.setGuid(location.getGuid());
				act.setClient_id(location.getClient_id());
				act.setStore_id(store_id);
				setupmapper.insertLoationActionemail(act);
			}
		}
		return store_id;
	}

	@Override
	public List<LocationTags> getTagsBasedonLocation(String guid, String uid, String clientid, String store_id) {
		return setupmapper.getTagsBasedonLocation(guid, uid, clientid, store_id);
	}

	@Override
	public boolean isAuditNameExist(AuditName auditname) {
		return (setupmapper.getAuditnameDetailsByCompany(auditname.getGuid(), auditname.getClient_id(),
				auditname.getAudit_type_name())) != null ? true : false;
	}

	@Override
	public AuditName addAuditName(AuditName auditname) {
		AuditName AudName = setupmapper.insertUpdateAuditName(auditname);

		List<ActionPlanAssignee> actionlist = auditname.getActionlist();
		for (ActionPlanAssignee act : actionlist) {

			act.setGuid(auditname.getGuid());
			act.setClient_id(auditname.getClient_id());
			act.setAudit_type_id(AudName.getAudit_type_id());
			setupmapper.insertActionemailAuditName(act);
		}
		return AudName;
	}

	@Override
	public void addAuditNameOrder(AuditName auditname) {
		setupmapper.AuditNameReorder(auditname);
	}

	@Override
	public boolean isAuditNameExistInDB(AuditName auditname) {
		return (setupmapper.getAuditnameDetailsByCompanyEdit(auditname)) > 0 ? true : false;
	}

	@Override
	public AuditName updateAuditName(AuditName auditname) {

		AuditName AudName = setupmapper.insertUpdateAuditName(auditname);
		List<ActionPlanAssignee> actionlist = auditname.getActionlist();
		if (!auditname.isIsdelete()) {
			setupmapper.deleteActionemailAuditName(auditname);
			for (ActionPlanAssignee act : actionlist) {
				act.setGuid(auditname.getGuid());
				act.setClient_id(auditname.getClient_id());
				act.setAudit_type_id(AudName.getAudit_type_id());
				setupmapper.insertActionemailAuditName(act);
			}
		}
		return AudName;
	}

	@Override
	public List<ActionPlanAssignee> getCompanyAssigneeList(Company company) {
		return setupmapper.getActionPlanAssigneeByCompany(company);

	}

	@Override
	public List<ActionPlanAssignee> getLocationAssigneeList(Location location) {
		return setupmapper.getActionemailByLocation(location);
	}

	@Override
	public List<ActionPlanAssignee> getAuditNameAssigneeList(AuditName auditname) {
		return setupmapper.getActionemailByAuditname(auditname);
	}

//	@Override
//	public int updateCompanyAssignee(List<ActionPlanAssignee> assignees) {
//		setupmapper.deleteActionemailCompany(assignees.get(0).getGuid(), assignees.get(0).getClient_id());
//
//		for (ActionPlanAssignee act : assignees) {
//
//			setupmapper.insertActionemailforCompany(act);
//		}
//		return 1;
//	}

//	@Override
//	public int updateLocationAssignee(List<ActionPlanAssignee> assignees) {
//		setupmapper.deleteLocationActionemail(assignees.get(0).getGuid(), assignees.get(0).getClient_id(),
//				assignees.get(0).getStore_id());
//
//		for (ActionPlanAssignee act : assignees) {
//			setupmapper.insertLoationActionemail(act);
//		}
//		return 1;
//	}

//	@Override
//	public int updateAuditNameUpdateAssignee(List<ActionPlanAssignee> assignees) {
//
//		AuditName adn = new AuditName();
//		adn.setGuid(assignees.get(0).getGuid());
//		adn.setClient_id(assignees.get(0).getClient_id());
//		adn.setAudit_type_id(assignees.get(0).getAudit_type_id());
//		setupmapper.deleteActionemailAuditName(adn);
//		for (ActionPlanAssignee act : assignees) {
//			setupmapper.insertActionemailAuditName(act);
//		}
//
//		return 1;
//	}

	@Override
	public List<LocationTags> getTagsList(String guid, int type) {
		List<LocationTags> tgsCaList = setupmapper.getTagCategories(guid, type);

		for (LocationTags lt : tgsCaList) {

			List<LocationTags> tgsList = setupmapper.getTagList(guid, lt.getCategory_id());

			lt.getTagsList().addAll(tgsList);

		}

		return tgsCaList;
	}

	@Override
	public List<ScoreRange> getScoreRange(String guid, String client_id, String audit_type_id) {
		return setupmapper.getScoreRange(guid, client_id, audit_type_id);
	}

	@Override
	public int addScoreRange(List<ScoreRange> scorerange) {
		setupmapper.deleteScoreRange(scorerange.get(0));

		for (ScoreRange sc : scorerange) {
			if (sc.getMax_value() != 0) {
				setupmapper.addScoreRange(sc);
			}

		}

		return 1;
	}

	@Override
	public List<EmailTemplate> getEmailTemplates(EmailTemplate emailtemplate) {

		List<EmailTemplate> emailList = setupmapper.getEmailTemplates(emailtemplate);
		for (EmailTemplate e : emailList) {
			List<EmailSubject> emailSubList = setupmapper.getEmailSubject(emailtemplate);
			e.getEmailSubjectList().addAll(emailSubList);
			List<EmailMessage> emailMsgList = setupmapper.getEmailMessage(emailtemplate);
			e.getEmailMessageList().addAll(emailMsgList);
		}

		return emailList;
	}

	@Override
	public void updateEmailTemplate(EmailTemplate emailTemplate) {

		setupmapper.updateEmailTemplate(emailTemplate);

	}

	@Override
	public String getCompanyCloneFlag(String guid) {

		return setupmapper.getCompanyCloneFlag(guid);
	}

	@Override
	public String checkCompanydata(PreTemplates preTemplates) {
		String validate = null;

		validate = setupmapper.ispreTemplateExistCompany(preTemplates) + "";
		String clients = setupmapper.getClientnamesExistingCompany(preTemplates);
		validate = validate + "---@%" + clients;
		return validate;
	}

	@Override
	public int cloneCompanies(PreTemplates preTemplates) {
		String clientIds[] = preTemplates.getClient_id().split(",");

		for (int i = 0; i < clientIds.length; i++) {

			preTemplates.setClient_id(clientIds[i]);
			setupmapper.cloneCompanies(preTemplates);

		}

		return 1;
	}

	@Override
	public boolean getGpsFlag(String guid) {
		return setupmapper.getGpsFlag(guid);
	}

	@Override
	public List<Report> getReports(Report report) {

		List<Report> reportlist = setupmapper.getReports(report);
		byte[] images = null;
		byte[] images1 = null;
		for (Report rt : reportlist) {

			images = rt.getReport_logo();
			// System.out.println(images);
			if (images == null) {
				rt.setReportlogo(null);
				rt.setReport_logo(null);
			} else {
				rt.setReport_logo(null);

				rt.setReportlogo("data:image/png;base64," + Utils.ConvertToBase64(images));
				// System.out.println(rt.getReportlogo());
			}

			images1 = rt.getLeftlogobi();
			// System.out.println(images);
			if (images1 == null) {
				rt.setLeftlogo(null);
				rt.setLeftlogobi(null);
			} else {
				rt.setLeftlogobi(null);

				rt.setLeftlogo("data:image/png;base64," + Utils.ConvertToBase64(images1));
				// System.out.println(rt.getReportlogo());
			}

		}

		return reportlist;
	}

	@Override
	public int updateReport(Report report) {
		byte[] byteimage = null;
		String[] imagesrc = null;

		if (report.getReportlogo() != null && !(report.getReportlogo().trim().isEmpty())) {
			imagesrc = report.getReportlogo().split(",");
			try {
				byteimage = Utils.Base64ToBytes(imagesrc[1]);
				report.setReport_logo(byteimage);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("Error catched", e);
			}
		}

		if (report.getLeftlogo() != null && !(report.getLeftlogo().trim().isEmpty())) {
			imagesrc = report.getLeftlogo().split(",");
			try {
				byteimage = Utils.Base64ToBytes(imagesrc[1]);
				report.setLeftlogobi(byteimage);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("Error catched", e);
			}
		}

		return setupmapper.UpdateReport(report);
	}

	@Override
	public List<Report> getReporttemplates(String guid) {

		List<Report> ReportList = setupmapper.getReportTemplates(guid);
		for (Report r : ReportList) {
			List<ReportImage> imageList = setupmapper.getReportImages(r.getTemplate_id());
			r.getReportImageList().addAll(imageList);

		}

		return ReportList;
	}

	@Override
	public List<Company> getPreexistingTemplates() {
		return setupmapper.getPreexistingTemplates();
	}

	@Override
	public List<AuditName> getPreAuditnames(int client_id) {
		return setupmapper.getPreAuditnames(client_id);
	}

	@Override
	public List<AuditName> getAllPreAuditnames() {
		return setupmapper.getPreAuditnamesList();
	}

	@Override
	public int getPretempletQuestionscount(int client_id, int audit_type_id) {
		return setupmapper.getPretempletQuestionscount(client_id, audit_type_id);
	}

	@Override
	public List<Section> getPretempletQuestions(Section section) {
		List<Section> Sectionlist = setupmapper.getPreSections(section);
		Group group = new Group();
		for (Section sec : Sectionlist) {

			group.setGuid(sec.getGuid());
			group.setClient_id(sec.getClient_id());
			group.setAudit_group_id(1);
			group.setAudit_type_id(sec.getAudit_type_id());
			group.setSection_id(sec.getSection_id());
			group.setActive(true);
			List<Group> Grouplist = setupmapper.getallGroups(group);

			for (Group grp : Grouplist) {
				group.setGroup_id(grp.getGroup_id());
				List<Question> QuestionList = setupmapper.getallQuestions(group);

				for (Question ques : QuestionList) {
					List<Choice> choicelist = setupmapper.getchoicesforquestion(group.getGuid(), ques.getClient_id(),
							ques.getAudit_group_id(), ques.getAudit_type_id(), ques.getQuestion_no(),
							ques.getChoice_pat_id());
					ques.getChoiceList().addAll(choicelist);

				}
				grp.getQuestionList().addAll(QuestionList);
			}

			sec.getGroupList().addAll(Grouplist);
		}

		return Sectionlist;

	}

	@Override
	public String PreTemplates(PreTemplates preTemplates) {
		String validate = null;

		validate = setupmapper.ispreTemplateExist(preTemplates) + "";
		String clients = setupmapper.getClientnamesExisting(preTemplates);
		validate = validate + "---@%" + clients;
		return validate;
	}

	@Override
	public int createPreTemplate(PreTemplates preTemplates) {

		return setupmapper.createPreTemplate(preTemplates);

	}

	@Override
	public int cloneAuditName(PreTemplates preTemplates) {
		String clientIds[] = preTemplates.getClient_id().split(",");

		for (int i = 0; i < clientIds.length; i++) {

			preTemplates.setClient_id(clientIds[i]);
			int audit_type_id = setupmapper.cloneAuditName(preTemplates);
			if (cloudinary(preTemplates.getGuid())) {
				List<Questactimage> questionList = setupmapper.getQuestionPhotos(preTemplates);
				for (Questactimage q : questionList) {

					if (q.getCloud_image_path() == null) {

					} else {
						try {

							String folderpath = "Companies/" + preTemplates.getGuid() + "/"
									+ preTemplates.getClient_id() + "/Branding/" + audit_type_id + "/Question";

							Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.cloudname,
									"api_key", Constants.apikey, "api_secret", Constants.apiSecret));
							String quesimage = "";
							quesimage = q.getCloud_image_path();
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							String timestamps = Long.toString(timestamp.getTime());
							String filename = q.getClient_id() + "-" + q.getAudit_type_id() + "_" + timestamps;
							@SuppressWarnings("rawtypes")
							Map result = cloudinary.uploader().upload(quesimage.replaceAll("\\r\\n|\\r|\\n", ""),
									ObjectUtils.asMap("folder", folderpath, "use_filename", "true", "unique_filename",
											"false", "public_id", filename));

							String scaleDownProperty = "w_1000,c_fill,g_auto";
							String delimiterStr = "/";
							int replaceIndex = 5;

							String reportImgUrl = Utils.splitJoinStringsAtIndex(result.get("secure_url").toString(),
									scaleDownProperty, replaceIndex, delimiterStr);

							q.setCloud_image_thumbnail(reportImgUrl);
							q.setCloud_image_path(result.get("secure_url").toString());
							q.setCloud_image_public_id(result.get("public_id").toString());

							q.setClient_id(Integer.parseInt(preTemplates.getClient_id()));
							q.setAudit_type_id(audit_type_id);
							setupmapper.addOrUpDatequestionimg(q);

						} catch (IOException e) {

							e.printStackTrace();
							log.error("Error catched", e);
						}

					}
				}
			}
		}
		return 1;
	}

	private boolean cloudinary(String guid) {
		return setupmapper.getCloudinary(guid);
	}

	@Override
	public List<GuidedSetup> getGuidedSetupdata() {
		return setupmapper.getGuidedSetupdata();
	}

	@Override
	public int getCompanyCount(String guid) {
		return setupmapper.getCompanyCount(guid);
	}

	@Override
	public int createGuided(GuidedSetup gudsetp) {
		return setupmapper.createGuided(gudsetp);
	}

	@Override
	public List<Location> getLocationsBasedonCompanyv2(Location location) {
		return setupmapper.getLocationsBasedonCompanyv2(location);
	}

	@Override
	public boolean getEditFlag(String guid) {
		return setupmapper.getEditFlag(guid);
	}

	@Override
	public boolean getCompletedFlag(String guid) {
		// TODO Auto-generated method stub
		return setupmapper.getCompletedFlag(guid);
	}

	@Override
	public List<Location> getLocationsBasedonCompanys(Location location) {
		// TODO Auto-generated method stub
		return setupmapper.getLocationsBasedonCompanys(location);
	}

	@Override
	public List<AuditName> getAuditNamesByCompanys(AuditName auditname) {
		// TODO Auto-generated method stub
		return setupmapper.getAuditNamesByCompanys(auditname);
	}

	@Override
	public List<Menu> getMenulist(String guid) {
		// TODO Auto-generated method stub
		return setupmapper.getMenulist(guid);
	}

	@Override
	public List<Help> getHelplist(String id) {
		return setupmapper.getHelplist(id);
	}

	@Override
	public List<Reportref> getReportRef(Reportref Reportref) {
		return setupmapper.getReportRef(Reportref);
	}

	@Override
	public List<ScheduleDefTim> getScheduleDefTime(ScheduleDefTim scheduleDefTim) {
		// TODO Auto-generated method stub
		return setupmapper.getScheduleDefTime(scheduleDefTim);
	}

	@Override
	public List<User> getUsersList(String guid) {
		// TODO Auto-generated method stub
		return setupmapper.getUsersList(guid);
	}

}
