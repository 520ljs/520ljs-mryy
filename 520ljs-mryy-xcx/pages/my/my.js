// pages/my/my.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        httpUrl: "http://127.0.0.1:8080/",
        imageHttpUrl: "http://127.0.0.1:8080/img/",
        userInfo: {},
        navTabData: ["全部订单", "已消费", "待消费", "预定中"],
        currentIndex: 0,
        orderData: [],
        isHidden: false
    },

    getOrderData(e) {
        var that = this;
        console.log("点击的脚标", e.currentTarget.dataset.inx);
        var index = e.currentTarget.dataset.inx;
        that.setData({
            currentIndex: index
        })
        // 数据库 orderstate    0：预定中（刚刚预定）   1：待消费（接收到短信）   2：已消费（消费）
        var orderstate = ''; // 默认为空
        if (index == 1) {
            orderstate = "2"
        } else if (index == 2) {
            orderstate = "1"
        } else if (index == 3) {
            orderstate = "0"
        }
        // 1、获取本地缓存的token
        wx.getStorage({
            key: 'token',
            success(res) {
                console.log("token = ", res.data)
                // 2、发起请求 token和orderstate
                wx.request({
                    url: that.data.httpUrl + 'orders/getOrdersByState',
                    data: {
                        token: res.data,
                        orderstate: orderstate
                    },
                    header: {
                        'content-type': 'application/json' // 默认值
                    },
                    success(res) {
                        console.log(res.data);
                        that.setData({
                            orderData: res.data.data
                        })
                    }
                })
            }
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        var that = this;
        // 1.获取本地缓存的用户信息
        wx.getStorage({
            key: 'userInfo',
            success(res) {
                console.log("我的页面获取的用户信息：", res.data)
                that.setData({
                    userInfo: res.data
                })
            }
        })
        // 1、获取本地缓存的token
        wx.getStorage({
            key: 'token',
            success(res) {
                console.log("token = ", res.data);
                // 2、获取所有订单   默认
                wx.request({
                    url: that.data.httpUrl + 'orders/getOrdersByState',
                    data: {
                        token: res.data,
                        orderstate: ''
                    },
                    header: {
                        'content-type': 'application/json' // 默认值
                    },
                    success(res) {
                        console.log(res.data)
                        that.setData({
                            orderData: res.data.data,
                            isHidden: true
                        })
                    }
                })
            }
        })
    },

    /**
     * 点击注册时，调转到注册页面
     */
    toRegister() {
        wx.navigateTo({
            url: '/pages/register/register',
        })
    },

    toLogin() {
        wx.navigateTo({
            url: '/pages/login/login',
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})