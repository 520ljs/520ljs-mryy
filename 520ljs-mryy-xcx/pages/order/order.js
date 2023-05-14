// pages/order/order.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        httpUrl: "http://127.0.0.1:8080/",
        httpImageUrl: "http://127.0.0.1:8080/img/",
        busInfo: {},
        date: "2023-3-3",
        time: "9:00"
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        var that = this;
        console.log("订单页面获取项目id为：", options.id);
        wx.request({
            url: that.data.httpUrl + '/business/getBusInfoByProId',
            data: {
                id: options.id
            },
            header: {
                'content-type': 'application/json' // 默认值
            },
            success(res) {
                console.log("获取商家信息：", res.data)
                that.setData({
                    busInfo: res.data.data,
                    condition: true
                })
            }
        })
    },

    bindDateChange(e) {
        console.log("日期：", e)
        this.setData({
            date: e.detail.value

        })
    },

    bindTimeChange(e) {
        console.log(e.detail.value);
        this.setData({
            time: e.detail.value
        })
    },

    // 点击登录 调用的方法
    formSubmit(e) {
        var that = this;
        console.log(e.detail.value); //三个数据
        // 应该去判断是否已经登录
        wx.getStorage({
            key: 'token',
            success(res) {
                console.log(res.data); //token信息
                // 发起请求
                wx.request({
                    url: that.data.httpUrl + 'orders/createOrder',
                    method: "POST",
                    header: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    data: {
                        token: res.data,
                        proname: that.data.busInfo.project.proname,
                        makedate: that.data.date + " " + that.data.time,
                        username: e.detail.value.username,
                        usertell: e.detail.value.usertell,
                        information: e.detail.value.information,
                        busid: that.data.busInfo.id,
                        proid: that.data.busInfo.project.id
                    },
                    success(res) {
                        console.log(res.data)
                        wx.switchTab({
                            url: '/pages/index/index',
                        })
                    }
                })
            },
            fail: function () {
                //跳转到登录页面
                wx.navigateTo({
                    url: '/pages/login/login',
                })
            }
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