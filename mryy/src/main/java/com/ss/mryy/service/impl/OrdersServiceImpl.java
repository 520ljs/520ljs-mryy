package com.ss.mryy.service.impl;

import com.ss.mryy.dao.OrdersDao;
import com.ss.mryy.dao.UserDao;
import com.ss.mryy.entity.Orders;
import com.ss.mryy.response.ResponseCode;
import com.ss.mryy.response.ResponseData;
import com.ss.mryy.service.OrdersService;
import com.ss.mryy.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 12:26:03
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(Long id) {
        return this.ordersDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param orders      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Orders> queryByPage(Orders orders, PageRequest pageRequest) {
        long total = this.ordersDao.count(orders);
        return new PageImpl<>(this.ordersDao.queryAllByLimit(orders, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders insert(Orders orders) {
        this.ordersDao.insert(orders);
        return orders;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders update(Orders orders) {
        this.ordersDao.update(orders);
        return this.queryById(orders.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.ordersDao.deleteById(id) > 0;
    }

    @Override
    public ResponseData createOrder(Orders order, String token) {
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
            ordersDao.insert(order);

            return new ResponseData(ResponseCode.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }

    @Override
    public ResponseData getOrderByState(String orderstate, String token) {
        try{
            //1.根据token获取oipenid
            String openid = userDao.queryOpenidByToken(token);

            //2.根据openid和orderstate查询订单表  获取数据
            Orders order = new Orders();
            order.setOpenid(openid);
            order.setOrderstate(orderstate);
            List<Orders> orders = ordersDao.queryOrders(order);

            return new ResponseData(ResponseCode.SUCCESS,orders);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }
}
