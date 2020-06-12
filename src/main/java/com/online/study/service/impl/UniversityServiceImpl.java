package com.online.study.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.study.common.ServerResponse;
import com.online.study.dao.CollegeDao;
import com.online.study.entity.College;
import com.online.study.entity.University;
import com.online.study.dao.UniversityDao;
import com.online.study.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (University)表服务实现类
 *
 * @author makejava
 * @since 2020-04-13 17:12:16
 */
@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityDao universityDao;
    @Autowired
    private CollegeDao collegeDao;

    @Override
    public ServerResponse addUniversity(University university) {
        int check = universityDao.checkByName(university.getName());
        if (check > 0) {
            return ServerResponse.createByErrorMessage("学校已存在");
        }
        int result = universityDao.insert(university);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("SUCCESS");
        }
        return ServerResponse.createByErrorMessage("添加失败");

    }

    @Override
    public ServerResponse getList(int pageNum,int pageSize) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<University> universityList = universityDao.selectList();
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(universityList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse deleteById(Integer id) {
        return null;
    }

    @Override
    public ServerResponse updateUniversity(University university) {
        return null;
    }

    @Override
    public ServerResponse getUniversity() {
        List<University> universityList = universityDao.selectList();
        return ServerResponse.createBySuccess(universityList);
    }

    @Override
    public ServerResponse addCollege(College college) {
        int check = collegeDao.checkName(college.getUniversityId(),college.getName());
        if (check > 0){
            return ServerResponse.createByErrorMessage("该院系已存在");
        }
        int result = collegeDao.insert(college);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("SUCCESS");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }
}