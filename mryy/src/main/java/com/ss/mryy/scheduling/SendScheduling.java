package com.ss.mryy.scheduling;

import com.ss.mryy.service.OrdersService;

import javax.annotation.Resource;

/**
 * @Author:ljy.s
 * @Date:2023/4/27 - 04 - 27 - 17:25
 */
//@Component
public class SendScheduling {

    @Resource
    OrdersService orderService;

//    @Scheduled(cron = "*/30 * * * * ?")
//    private void proces() {
//        System.out.println("执行定时任务");
//        //1.获取需要发送短信的订单   根据订单的状态查询订单信息   0
//        List<Orders> orderList = orderService.getOrdersByOrderState("0");
//        //2.获取需要发送短信的电话
//        for (int i = 0; i < orderList.size(); i++) {
//            Orders order = orderList.get(i);
//
//            System.out.println(order.getUsertell());
//            String usertell = order.getUsertell();
//            //3.发送短信
//            boolean flag = SMSUtil.sendSMS(usertell);
//
//            if (flag) {
//                //4.如果发送成功，修改当前订单的状态为1
//                order.setOrderstate("1");
//                orderService.update(order);
//            }
//        }
//    }

}
