// pages/index/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        bannerData: [],
        navData: [],
        proData: [],
        httpImageUrl: "http://127.0.0.1:8080/img/",
        httpUrl: "http://127.0.0.1:8080/",
        page: 1,
        isData: true // 默认有数据
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        var that = this;
        // 1.发送请求，获取轮播图信息
        wx.request({
            url: that.data.httpUrl + 'image/queryImageByType', // 后端接口
            data: {
                imagetype: 'banner'
            },
            header: {
                'content-type': 'application/json' // 默认值
            },
            success(res) {
                console.log("获取轮播图信息：", res.data)
                if (res.data.code == 200) { // 说明请求成功，把返回的数据，设置给data
                    that.setData({
                        bannerData: res.data.data
                    })
                } else { // 失败 提示失败原因

                }
            }
        })

        // 2.发送请求，获取导航菜单信息
        wx.request({
            url: that.data.httpUrl + 'image/queryImageByType', // 后端接口
            data: {
                imagetype: 'nav'
            },
            header: {
                'content-type': 'application/json' // 默认值
            },
            success(res) {
                console.log("获取导航菜单信息：", res.data)
                if (res.data.code == 200) { // 说明请求成功，把返回的数据，设置给data
                    that.setData({
                        navData: res.data.data
                    })
                } else { // 失败 提示失败原因

                }
            }
        })

        // 3.发送请求，获取项目信息
        wx.request({
            url: that.data.httpUrl + 'project/getProInfos', // 后端接口
            header: {
                'content-type': 'application/json' // 默认值
            },
            data: {
                page: that.data.page,
                limit: 10
            },
            success(res) {
                console.log("获取项目信息：", res.data)
                if (res.data.code == 200) { // 说明请求成功，把返回的数据，设置给data
                    that.setData({
                        proData: res.data.data
                    })
                } else { // 失败 提示失败原因

                }
            }
        })
    },

    toProDetails(e) {
        console.log(e.currentTarget.dataset.id);
        // 跳转页面，项目的详情页面
        wx.navigateTo({
            url: '/pages/proDetails/proDetails?id=' + e.currentTarget.dataset.id,
        })
    },

    lower() {
        var that = this;
        if (that.data.isData) {
            that.data.page++
            console.log("当前页面：", that.data.page)
            wx.request({
                url: that.data.httpUrl + 'project/getProInfos', // 后端接口
                header: {
                    'content-type': 'application/json' // 默认值
                },
                data: {
                    page: that.data.page,
                    limit: 10
                },
                success(res) {
                    console.log("获取项目信息：", res.data.data) // 每次拿到的数据，应该和之前的数据进行拼接
                    var proList = [...that.data.proData, ...res.data.data]; // 拼接之后的数据
                    var isData = true;
                    console.log(res.data.count)
                    if (proList.length >= res.data.count) { // 说明没有数据了
                        // 如果proList长度大于count，把当前的isData设为false，反之
                        isData = false;
                    }
                    if (res.data.code == 200) { // 说明请求成功，把返回的数据，设置给data
                        that.setData({
                            proData: proList, // 拼接之后的数据赋值给当前页面
                            isData: isData // js里面定义的 ：当前页面定义的变量，默认是true，进入if之后变为false
                        })
                    } else { // 失败 提示失败原因

                    }
                }
            })
        }
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