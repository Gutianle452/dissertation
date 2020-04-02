Page({
  data: {
    pageIndex: 1,
    productList: [
    ],
    loadding: false,
    pullUpOn: true
  },
  onLoad: function (options) {
    this.onPullDownRefresh();
  },
  search: function () {
    this.onPullDownRefresh();
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    //延时为了看效果
    setTimeout(() => {
      this.setData({

        pageIndex: 1,
        pullUpOn: true,
        loadding: false,
        productList: []
      });
      this.query();
      wx.showToast({
        title: '刷新成功',
        icon: "none"
      })
    }, 200)
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    if (!this.data.pullUpOn) return;
    this.setData({
      loadding: true
    });
    this.query();
  },
  detail(e) {
    console.log(e.currentTarget.dataset);
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../home/menudetail?id=' + id
    })
  }

  , query() {
    var that = this;
    wx.request({
      url: getApp().globalData.server + "cus/comment/list?limit=10",
      data: { "page": this.data.pageIndex, "userId": getApp().globalData.userId },

      method: 'post',
      header: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },

      success: function (res) {
        let temp = res.data.rows;

        var dd = [];
        for (let i = 0, length = temp.length; i < length; i++) {

          dd[i] = ({

            id: temp[i].id,
            img: getApp().globalData.server + temp[i].pic,
            name: temp[i].menu,
            content: temp[i].content,

          });


        }

        that.setData({
          productList: that.data.productList.concat(dd),
          pageIndex: that.data.pageIndex + 1,
          loadding: false

        })


        wx.stopPullDownRefresh()


      },
      fail: function (r) {
        console.log(r);
      }
    });

  }
})