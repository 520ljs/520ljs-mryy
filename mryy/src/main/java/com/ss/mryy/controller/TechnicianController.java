package com.ss.mryy.controller;

import com.ss.mryy.entity.Technician;
import com.ss.mryy.response.ResponseData;
import com.ss.mryy.service.TechnicianService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Technician)表控制层
 *
 * @author makejava
 * @since 2023-04-20 01:52:43
 */
@Api(tags = "技师接口")
@RestController
@RequestMapping("technician")
public class TechnicianController {
    /**
     * 服务对象
     */
    @Resource
    private TechnicianService technicianService;

    /**
     * 分页查询
     *
     * @param technician  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Technician>> queryByPage(Technician technician, PageRequest pageRequest) {
        return ResponseEntity.ok(this.technicianService.queryByPage(technician, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Technician> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.technicianService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param technician 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Technician> add(Technician technician) {
        return ResponseEntity.ok(this.technicianService.insert(technician));
    }

    /**
     * 编辑数据
     *
     * @param technician 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Technician> edit(Technician technician) {
        return ResponseEntity.ok(this.technicianService.update(technician));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.technicianService.deleteById(id));
    }

    @GetMapping("getTecInfos")
    public ResponseData getTecInfos(int page, int limit) {
        System.out.println("page = " + page);
        System.out.println("limit = " + limit);

        return technicianService.getTecInfos(page, limit);
    }

    @GetMapping("getTecInfoById")
    public ResponseData getTecInfoById(Long id) {
        System.out.println("id = " + id);

        return technicianService.getTecInfoById(id);
    }

}

