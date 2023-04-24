package com.ss.mryy.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ss.mryy.dao.UserDao;
import com.ss.mryy.entity.User;
import com.ss.mryy.response.ResponseCode;
import com.ss.mryy.response.ResponseData;
import com.ss.mryy.service.UserService;
import com.ss.mryy.util.HttpClientUtil;
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
        if (StringUtil.isNull(username)) {
            return new ResponseData(ResponseCode.ERROR_3);
        }
        if (StringUtil.isNull(phone)) {
            return new ResponseData(ResponseCode.ERROR_1);
        }
        if (StringUtil.isNull(password)) {
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

    @Override
    public ResponseData userLogin(String phone, String password, String code) {
        // 1.非空校验
        if (StringUtil.isNull(phone)) {
            return new ResponseData(ResponseCode.ERROR_1);
        }
        if (StringUtil.isNull(password)) {
            return new ResponseData(ResponseCode.ERROR_2);
        }

        // 2.密码加密
        Md5Hash md5Hash = new Md5Hash(password, "qianfeng", 10);
        String newPassword = md5Hash.toString();

        // 3.根据手机号密码查询用户信息  user
        User user = userDao.queryUserByPhoneAndPwd(phone, newPassword);
        if (user == null) {// 说明错误
            return new ResponseData(ResponseCode.ERROR_5);
        }

        try {
            // 4.调用微信的接口    请求方式 GET 模拟一个GET请求
            // https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
            String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=wx6bebce01217f7059&" +
                    "secret=33748d28f76659dc273345c517d2f053&" +
                    "js_code=" + code +
                    "&grant_type=authorization_code";
            String result = HttpClientUtil.doGet(url);// 模拟发送get请求
            System.out.println("result：" + result);

            // 5.获取session_key 和openid  字符串 转 JSON
            JSONObject jsonResult = JSONObject.parse(result);
            String session_key = (String) jsonResult.get("session_key");
            String openid = (String) jsonResult.get("openid");
            System.out.println("sessionKey：" + session_key + "，openid：" + openid);

            // 6.生成自定义登录状态
            Md5Hash md5Hash1 = new Md5Hash(session_key, openid, 10);
            String token = md5Hash1.toString();

            // 7.保存（更新） user表   token,session_key,openid    根据id去更新
            user.setOpenid(openid);
            user.setSessionkey(session_key);
            user.setToken(token);
            userDao.update(user);
            return new ResponseData(ResponseCode.SUCCESS, token);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseData(ResponseCode.FAIL);
        }
    }
}
