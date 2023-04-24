package com.ss.mryy.service.impl;

import com.ss.mryy.dao.UserDao;
import com.ss.mryy.entity.User;
import com.ss.mryy.response.ResponseCode;
import com.ss.mryy.response.ResponseData;
import com.ss.mryy.service.UserService;
import com.ss.mryy.util.StringUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-04-20 01:52:45
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public ResponseData userRegister(User user) {
        String phone = user.getPhone();
        String username = user.getUsername();
        String password = user.getPassword();
        // 1.非空校验，phone取出空格，只要一个代码 两次 提取
        if (StringUtil.isNull(username)){
            return new ResponseData(ResponseCode.ERROR_3);
        }
        if (StringUtil.isNull(phone)){
            return new ResponseData(ResponseCode.ERROR_1);
        }
        if (StringUtil.isNull(password)){
            return new ResponseData(ResponseCode.ERROR_2);
        }
        try {
            // 2.校验用户名是否存在
            // 如何校验？根据username查询user表，如果有数据，说明存在，返回提示信息
            User queryuser = userDao.queryUserByUserName(username);
            if (queryuser != null) {
                return new ResponseData(ResponseCode.ERROR_4);
            }
            // 3.加密     source：对哪个资源进行加密，salt：盐值，hashIterations：加密次数
            Md5Hash md5Hash = new Md5Hash(password, "qianfeng", 10);
            String newPassword = md5Hash.toString();// 得到加密之后的密码
            // 4.保存
            user.setPassword(newPassword);
            userDao.insert(user);
            return new ResponseData(ResponseCode.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }
}
