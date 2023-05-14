// pages/technician/technician.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        httpUrl: "http://127.0.0.1:8080/",
        httpImageUrl: "http://127.0.0.1:8080/img/",
        page: 1, // 当前默认第一页
        tecData: [], // 查询到的数据要做拼接
        isData: true
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        var that = this;
        // 1.发起请求，获取第一页的数据
        wx.request({
            url: that.data.httpUrl + 'technician/getTecInfos',
            data: {
                page: that.data.page,
                limit: 5
            },
            header: {
                'content-type': 'application/json' // 默认值
            },
            success(res) {
                console.log("获取的技师信息：", res.data)
                that.setData({
                    tecData: res.data.data
                })
            }
        })
    },

    lower() {
        var that = this;
        if (that.data.isData) {
            // 发起请求
            that.data.page++;
            wx.request({
                url: that.data.httpUrl + 'technician/getTecInfos',
                data: {
                    page: that.data.page,
                    limit: 5
                },
                header: {
                    'content-type': 'application/json' // 默认值
                },
                success(res) {
                    console.log("获取的技师信息：", res.data)
                    var tecList = [...that.data.tecData, ...res.data.data];
                    var isData = true;
                    if (tecList.length >= res.data.count) {
                        isData = false
                    }
                    that.setData({
                        tecData: tecList,
                        isData: isData
                    })
                }
            })
        }
    },

    toTecDetails(e) {
        console.log(e.currentTarget.dataset.id);
        // 跳转到 技师详情页面
        wx.navigateTo({
            url: '/pages/tecDetails/tecDetails?id=' + e.currentTarget.dataset.id,
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