// pages/tecDetails/tecDetails.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        httpUrl: "http://127.0.0.1:8080/",
        httpImageUrl: "http://127.0.0.1:8080/img/",
        tecInfo: {}
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        var that = this;
        console.log("获取的技师id为：", options.id)
        wx.request({
            url: that.data.httpUrl + 'technician/getTecInfoById',
            data: {
                id: options.id,
            },
            header: {
                'content-type': 'application/json' // 默认值
            },
            success(res) {
                console.log(res.data)
                that.setData({
                    tecInfo: res.data.data,
                    condition: true
                })
            }
        })
    },

    toOrder(e) {
        wx.navigateTo({
            url: '/pages/order/order?id=' + e.currentTarget.dataset.id,
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