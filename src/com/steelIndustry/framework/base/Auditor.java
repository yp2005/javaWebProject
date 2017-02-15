package com.steelIndustry.framework.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@SuppressWarnings("serial")
@MappedSuperclass
public class Auditor extends BasePojo {

	private String createBy;
	private Date createDt;
	private String modifyBy;
	private Date modifyDt;

	@CreatedBy
	@Column(name = "create_by")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@CreatedDate
	@Column(name = "create_dt")
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@LastModifiedBy
	@Column(name = "modify_by")
	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@LastModifiedDate
	@Column(name = "modify_dt")
	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	@Transient
	public void setCurrentDate() {
		Date currentDate = new Date();
		this.setCreateDt(currentDate);
		this.setModifyDt(currentDate);
	}

}
