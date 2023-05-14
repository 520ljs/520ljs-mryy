// pages/register/register.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        httpUrl: "http://127.0.0.1:8080/"
    },

    /** 
     * 提交表单
     */
    formSubmit(e) {
        var that = this;
        console.log(e.detail.value);
        // 1、获取本地缓存的user信息
        wx.getStorage({
            key: 'userInfo',
            success(res) {
                // console.log(res.data) 用户信息
                // 2、发起请求，注册，post请求
                wx.request({
                    url: that.data.httpUrl + 'user/userRegister',
                    method: "POST",
                    data: {
                        nickname: res.data.nickName,
                        imageurl: res.data.avatarUrl,
                        username: e.detail.value.username,
                        password: e.detail.value.password,
                        phone: e.detail.value.phone
                    },
                    header: {
                        'content-type': 'application/x-www-form-urlencoded'
                    },
                    success(res) {
                        // res.data返回的值
                        console.log(res.data)
                        if (res.data.data == 200) {// 表示成功
                            wx.switchTab({
                                url: '/pages/my/my'
                            })
                        } else {
                            // 失败 显示提示信息
                            wx.showToast({
                                title: res.data.msg,
                                icon: 'error',
                                duration: 2000
                              })
                        }
                    }
                })
            }
        })
    },

    toLogin() {
        wx.navigateTo({
            url: '/pages/login/login',
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

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