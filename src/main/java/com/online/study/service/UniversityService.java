package com.online.study.service;

import com.online.study.common.ServerResponse;
import com.online.study.entity.College;
import com.online.study.entity.University;
import java.util.List;

/**
 * (University)表服务接口
 *
 * @author makejava
 * @since 2020-04-13 17:12:16
 */
public interface UniversityService {

    ServerResponse addUniversity(University university);

    ServerResponse getList(int pageNum,int pageSize);

    ServerResponse deleteById(Integer id);

    ServerResponse updateUniversity(University university);

    ServerResponse getUniversity();

    ServerResponse addCollege(College college);
}