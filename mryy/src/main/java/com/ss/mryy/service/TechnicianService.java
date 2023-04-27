package com.ss.mryy.service;

import com.ss.mryy.entity.Technician;
import com.ss.mryy.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Technician)表服务接口
 *
 * @author makejava
 * @since 2023-04-20 01:52:44
 */
public interface TechnicianService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Technician queryById(Long id);

    /**
     * 分页查询
     *
     * @param technician  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Technician> queryByPage(Technician technician, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param technician 实例对象
     * @return 实例对象
     */
    Technician insert(Technician technician);

    /**
     * 修改数据
     *
     * @param technician 实例对象
     * @return 实例对象
     */
    Technician update(Technician technician);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页获取技师信息  每页查询5条
     *
     * @param page
     * @param limit
     * @return
     */
    ResponseData getTecInfos(int page, int limit);

    /**
     * 根据id获取技师信息  以及 技师下面的项目信息    技师对项目  多对多
     *
     * @param id
     * @return
     */
    ResponseData getTecInfoById(Long id);
}
