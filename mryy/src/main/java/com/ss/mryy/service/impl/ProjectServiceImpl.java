package com.ss.mryy.service.impl;

import com.ss.mryy.dao.ProjectDao;
import com.ss.mryy.entity.Project;
import com.ss.mryy.response.ResponseCode;
import com.ss.mryy.response.ResponseData;
import com.ss.mryy.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Project)表服务实现类
 *
 * @author makejava
 * @since 2023-04-20 01:52:43
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectDao projectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Project queryById(Long id) {
        return this.projectDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param project     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Project> queryByPage(Project project, PageRequest pageRequest) {
        long total = this.projectDao.count(project);
        return new PageImpl<>(this.projectDao.queryAllByLimit(project, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project insert(Project project) {
        this.projectDao.insert(project);
        return project;
    }

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project update(Project project) {
        this.projectDao.update(project);
        return this.queryById(project.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.projectDao.deleteById(id) > 0;
    }

    @Override
    public ResponseData getProInfos() {
        try {
            List<Project> projects = projectDao.getProInfos();
            return new ResponseData(ResponseCode.SUCCESS, projects);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }

    @Override
    public ResponseData getProInfoById(Long id) {

        try {
            Project project = projectDao.queryById(id);
            return new ResponseData(ResponseCode.SUCCESS, project);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }

    }

}
