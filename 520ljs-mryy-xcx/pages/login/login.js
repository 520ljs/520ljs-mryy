// pages/login/login.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        httpUrl: "http://127.0.0.1:8080/"
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },

    // 点击登录，调用的
    formSubmit(e) {
        var that = this;
        console.log(e.detail.value.phone);
        wx.login({
            success(res) {
                if (res.code) {
                    //发起网络请求  res.code 需要的登录凭证 
                    wx.request({
                        url: that.data.httpUrl + 'user/userLogin',
                        method: "POST",
                        header: {
                            'content-type': 'application/x-www-form-urlencoded'
                        },
                        data: {
                            code: res.code,
                            phone: e.detail.value.phone,
                            password: e.detail.value.password
                        },
                        success(result) {
                            console.log(result.data)
                            if (result.data.code == 200) { // 成功
                                // 把返回token放入本地缓存
                                wx.setStorage({
                                    key: "token",
                                    data: result.data.data
                                })
                                // 跳转到首页
                                wx.switchTab({
                                    url: '/pages/index/index',
                                })
                            } else { // 失败，显示错误信息
                                wx.showToast({
                                    title: result.data.msg,
                                    icon: 'error',
                                    duration: 2000
                                })
                            }
                        }
                    })
                } else {
                    console.log('登录失败！' + res.errMsg)
                }
            }
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