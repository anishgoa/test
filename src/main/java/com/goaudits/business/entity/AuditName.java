package com.goaudits.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

@Repository
public class AuditName implements Serializable {

    private static final long serialVersionUID = 1L;

    private String guid;
    private String client_id;
    private String client_name;
    private String audit_group_id;
    private String audit_group_name;
    private String audit_type_id;
    private String audit_type_name;
    private String inputLogo;
    private boolean active;
    private String logo;
    private String uid;
    private String signature1_label;
    private String signature2_label;
    private String signature3_label;
    private boolean sign1_flag;
    private boolean sign2_flag;
    private boolean sign3_flag;
    private boolean is_man_sign1;
    private boolean is_man_sign2;
    private boolean is_man_sign3;
    private String group_id;
    private int images_peraudit;
    private int images_perquestion;
    private int pl_code;
    private String person_seen;
    private boolean person_seen_mandatory;
    private boolean showif_optional;
    private String audit_type_title;
    private int sort_order;
    private int drag_index;
    private int drop_index;
    private boolean image_required = true;
    private boolean hide_signature_app;
    private int count;
	private List<ActionPlanAssignee> actionlist = new ArrayList<ActionPlanAssignee>();
	private List<Location> strarry = new ArrayList<Location>();


	public int getDrag_index() {
		return drag_index;
	}

	public void setDrag_index(int drag_index) {
		this.drag_index = drag_index;
	}

	public int getDrop_index() {
		return drop_index;
	}

	public void setDrop_index(int drop_index) {
		this.drop_index = drop_index;
	}

	public int getSort_order() {
		return sort_order;
	}

	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}

	public List<ActionPlanAssignee> getActionlist() {
		return actionlist;
	}

	public void setActionlist(List<ActionPlanAssignee> actionlist) {
		this.actionlist = actionlist;
	}

	public boolean isShowif_optional() {
		return showif_optional;
	}

	public void setShowif_optional(boolean showif_optional) {
		this.showif_optional = showif_optional;
	}

	public String getPerson_seen() {
		return person_seen;
	}

	public void setPerson_seen(String person_seen) {
		this.person_seen = person_seen;
	}

	public boolean isPerson_seen_mandatory() {
		return person_seen_mandatory;
	}

	public void setPerson_seen_mandatory(boolean person_seen_mandatory) {
		this.person_seen_mandatory = person_seen_mandatory;
	}

	public int getPl_code() {
		return pl_code;
	}

	public void setPl_code(int pl_code) {
		this.pl_code = pl_code;
	}

	public int getImages_peraudit() {
		return images_peraudit;
	}

	public void setImages_peraudit(int images_peraudit) {
		this.images_peraudit = images_peraudit;
	}

	public int getImages_perquestion() {
		return images_perquestion;
	}

	public void setImages_perquestion(int images_perquestion) {
		this.images_perquestion = images_perquestion;
	}

	public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSignature1_label() {
        return signature1_label;
    }

    public void setSignature1_label(String signature1_label) {
        this.signature1_label = signature1_label;
    }

    public String getSignature2_label() {
        return signature2_label;
    }

    public void setSignature2_label(String signature2_label) {
        this.signature2_label = signature2_label;
    }

    public String getSignature3_label() {
        return signature3_label;
    }

    public void setSignature3_label(String signature3_label) {
        this.signature3_label = signature3_label;
    }

    public boolean isSign1_flag() {
        return sign1_flag;
    }

    public void setSign1_flag(boolean sign1_flag) {
        this.sign1_flag = sign1_flag;
    }

    public boolean isSign2_flag() {
        return sign2_flag;
    }

    public void setSign2_flag(boolean sign2_flag) {
        this.sign2_flag = sign2_flag;
    }

    public boolean isSign3_flag() {
        return sign3_flag;
    }

    public void setSign3_flag(boolean sign3_flag) {
        this.sign3_flag = sign3_flag;
    }

    public boolean isIs_man_sign1() {
        return is_man_sign1;
    }

    public void setIs_man_sign1(boolean is_man_sign1) {
        this.is_man_sign1 = is_man_sign1;
    }

    public boolean isIs_man_sign2() {
        return is_man_sign2;
    }

    public void setIs_man_sign2(boolean is_man_sign2) {
        this.is_man_sign2 = is_man_sign2;
    }

    public boolean isIs_man_sign3() {
        return is_man_sign3;
    }

    public void setIs_man_sign3(boolean is_man_sign3) {
        this.is_man_sign3 = is_man_sign3;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getAudit_group_id() {
        return audit_group_id;
    }

    public void setAudit_group_id(String audit_group_id) {
        this.audit_group_id = audit_group_id;
    }

    public String getAudit_group_name() {
        return audit_group_name;
    }

    public void setAudit_group_name(String audit_group_name) {
        this.audit_group_name = audit_group_name;
    }

    public String getAudit_type_id() {
        return audit_type_id;
    }

    public void setAudit_type_id(String audit_type_id) {
        this.audit_type_id = audit_type_id;
    }

    public String getAudit_type_name() {
        return audit_type_name;
    }

    public void setAudit_type_name(String audit_type_name) {
        this.audit_type_name = audit_type_name;
    }

    public String getInputLogo() {
        return inputLogo;
    }

    public void setInputLogo(String inputLogo) {
        this.inputLogo = inputLogo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

	public String getAudit_type_title() {
		return audit_type_title;
	}

	public void setAudit_type_title(String audit_type_title) {
		this.audit_type_title = audit_type_title;
	}

	public boolean isHide_signature_app() {
		return hide_signature_app;
	}

	public void setHide_signature_app(boolean hide_signature_app) {
		this.hide_signature_app = hide_signature_app;
	}

	public boolean isImage_required() {
		return image_required;
	}

	public void setImage_required(boolean image_required) {
		this.image_required = image_required;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Location> getStrarry() {
		return strarry;
	}

	public void setStrarry(List<Location> strarry) {
		this.strarry = strarry;
	}
	
	

}
