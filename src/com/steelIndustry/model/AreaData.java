package com.steelIndustry.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.acls.model.ChildrenExistException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.steelIndustry.framework.base.BaseSimplePojo;

@Entity
@Table(name = "area_data")
@NamedQuery(name = "AreaData.findAll", query = "SELECT a FROM AreaData a")
public class AreaData implements BaseSimplePojo {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "area_id", nullable = false)
    private int areaId;

    @Column(name = "area_name")
    private String areaNname;
    
    @Column(name = "area_parent_id")
    private String areaParentId;

    @ManyToOne
    @JoinColumn(name = "area_parent_id",  insertable = false, updatable = false)
    @JsonIgnore
    private AreaData parentArea;
    
    @OneToMany(mappedBy = "parentArea", fetch = FetchType.EAGER)
    private List<AreaData> children;

    @Column(name = "area_sort")
    private int areaSort;

    @Column(name = "area_deep")
    private int areaDeep;

    @Column(name = "area_region")
    private String areaRegion;

    public AreaData() {
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaNname() {
        return areaNname;
    }

    public void setAreaNname(String areaNname) {
        this.areaNname = areaNname;
    }

    public int getAreaSort() {
        return areaSort;
    }

    public void setAreaSort(int areaSort) {
        this.areaSort = areaSort;
    }

    public int getAreaDeep() {
        return areaDeep;
    }

    public void setAreaDeep(int areaDeep) {
        this.areaDeep = areaDeep;
    }

    public String getAreaRegion() {
        return areaRegion;
    }

    public void setAreaRegion(String areaRegion) {
        this.areaRegion = areaRegion;
    }

    public AreaData getParentArea() {
        return parentArea;
    }

    public void setParentArea(AreaData parentArea) {
        this.parentArea = parentArea;
    }

    public List<AreaData> getChildren() {
        return children;
    }

    public void setChildren(List<AreaData> children) {
        this.children = children;
    }

    public String getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(String areaParentId) {
        this.areaParentId = areaParentId;
    }

}