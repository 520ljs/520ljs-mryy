package com.ss.mryy.dao;

import com.ss.mryy.entity.Technician;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Technician)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-20 01:52:44
 */
public interface TechnicianDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Technician queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param technician 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<Technician> queryAllByLimit(Technician technician, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param technician 查询条件
     * @return 总行数
     */
    long count(Technician technician);

    /**
     * 新增数据
     *
     * @param technician 实例对象
     * @return 影响行数
     */
    int insert(Technician technician);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Technician> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Technician> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Technician> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Technician> entities);

    /**
     * 修改数据
     *
     * @param technician 实例对象
     * @return 影响行数
     */
    int update(Technician technician);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询指定行数据
     *
     * @param page  页
     * @param limit 条
     * @return 对象列表
     */
    List<Technician> getTecInfosByLimit(@Param("page") int page, @Param("limit") int limit);

    /**
     * 统计总条数
     *
     * @return
     */
    Long queryCount();

    /**
     * 根据id获取技师信息  以及  项目信息
     *
     * @param id
     * @return
     */
    Technician getTecInfoById(Long id);
}











