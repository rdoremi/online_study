package com.online.study.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Classroom)实体类
 *
 * @author makejava
 * @since 2020-04-13 17:11:10
 */
public class Classroom implements Serializable {
    private static final long serialVersionUID = -39362287188304239L;
    
    private Long id;
    
    private Long universityId;
    
    private Long collegeId;
    
    private String name;
    /**
    * 班主任
    */
    private String teacher;
    
    private String cover;
    
    private String createTime;
    
    private String updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}