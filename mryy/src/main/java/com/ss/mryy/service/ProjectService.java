package com.ss.mryy.service;

import com.ss.mryy.entity.Project;
import com.ss.mryy.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Project)表服务接口
 *
 * @author makejava
 * @since 2023-04-20 01:52:43
 */
public interface ProjectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Project queryById(Long id);

    /**
     * 分页查询
     *
     * @param project     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Project> queryByPage(Project project, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    Project insert(Project project);

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    Project update(Project project);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 获取所有的项目信息
     * @return
     */
    ResponseData getProInfos();

    /**
     * 根据id获取项目信息
     * @param id
     * @return
     */
    ResponseData getProInfoById(Long id);
}
