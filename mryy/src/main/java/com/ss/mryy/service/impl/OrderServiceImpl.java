package com.ss.mryy.service.impl;

import com.ss.mryy.dao.OrderDao;
import com.ss.mryy.dao.UserDao;
import com.ss.mryy.entity.Order;
import com.ss.mryy.response.ResponseCode;
import com.ss.mryy.response.ResponseData;
import com.ss.mryy.service.OrderService;
import com.ss.mryy.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2023-04-20 01:52:42
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Long id) {
        return this.orderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param order       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Order> queryByPage(Order order, PageRequest pageRequest) {
        long total = this.orderDao.count(order);
        return new PageImpl<>(this.orderDao.queryAllByLimit(order, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order insert(Order order) {
        this.orderDao.insert(order);
        return order;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order update(Order order) {
        this.orderDao.update(order);
        return this.queryById(order.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orderDao.deleteById(id) > 0;
    }

    @Override
    public ResponseData createOrder(Order order, String token) {
        // 1.非空校验
        String username = order.getUsername();
        String usertell = order.getUsertell();
        if (StringUtil.isNull(username) || StringUtil.isNull(usertell)) {
            return new ResponseData(ResponseCode.ERROR_6);
        }

        try {
            // 2.根据token获取到openid
            String openid = userDao.queryOpenidByToken(token);

            // 3.获取系统当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            order.setOpenid(openid);
            order.setPlacedate(sdf.format(new Date()));
            order.setOrderstate("0");//状态默认为0

            // 4.生成订单
            orderDao.insert(order);

            return new ResponseData(ResponseCode.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }
}
