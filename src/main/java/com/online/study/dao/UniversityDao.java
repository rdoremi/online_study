package com.online.study.dao;

import com.online.study.entity.University;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (University)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-13 17:12:16
 */
public interface UniversityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    University queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<University> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param university 实例对象
     * @return 对象列表
     */
    List<University> queryAll(University university);

    /**
     * 新增数据
     *
     * @param university 实例对象
     * @return 影响行数
     */
    int insert(University university);

    /**
     * 修改数据
     *
     * @param university 实例对象
     * @return 影响行数
     */
    int update(University university);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


    int checkByName(String name);

    List<University> selectList();
}