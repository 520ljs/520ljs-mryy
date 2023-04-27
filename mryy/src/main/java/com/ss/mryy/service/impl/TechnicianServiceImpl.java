package com.ss.mryy.service.impl;

import com.ss.mryy.dao.TechnicianDao;
import com.ss.mryy.entity.Technician;
import com.ss.mryy.response.ResponseCode;
import com.ss.mryy.response.ResponseData;
import com.ss.mryy.service.TechnicianService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Technician)表服务实现类
 *
 * @author makejava
 * @since 2023-04-20 01:52:44
 */
@Service("technicianService")
public class TechnicianServiceImpl implements TechnicianService {
    @Resource
    private TechnicianDao technicianDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Technician queryById(Long id) {
        return this.technicianDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param technician  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Technician> queryByPage(Technician technician, PageRequest pageRequest) {
        long total = this.technicianDao.count(technician);
        return new PageImpl<>(this.technicianDao.queryAllByLimit(technician, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param technician 实例对象
     * @return 实例对象
     */
    @Override
    public Technician insert(Technician technician) {
        this.technicianDao.insert(technician);
        return technician;
    }

    /**
     * 修改数据
     *
     * @param technician 实例对象
     * @return 实例对象
     */
    @Override
    public Technician update(Technician technician) {
        this.technicianDao.update(technician);
        return this.queryById(technician.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.technicianDao.deleteById(id) > 0;
    }

    @Override
    public ResponseData getTecInfos(int page, int limit) {
        try {
            int start = (page - 1) * limit;
            List<Technician> technicians = technicianDao.getTecInfosByLimit(start, limit);
            //获取总条数
            Long count = technicianDao.queryCount();
            return new ResponseData(ResponseCode.SUCCESS, technicians, count);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }

    @Override
    public ResponseData getTecInfoById(Long id) {

        //分析  需要哪些字段（技师表）  哪些数据表   项目表    技师和项目的中间表
        try {
            Technician tecInfoById = technicianDao.getTecInfoById(id);
            return new ResponseData(ResponseCode.SUCCESS, tecInfoById);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }
}
