Page({
  data: {
    pageIndex: 1,
    productList: [{
        img: 1,
        name: "教程1",
        sale: 599,
        factory: 899,
        payNum: 2342
      },
      {
        img: 2,
        name: "教程2",
        sale: 29,
        factory: 69,
        payNum: 999
      },
      {
        img: 3,
        name: "教程3",
        sale: 299,
        factory: 699,
        payNum: 666
      } 
    ],
    loadData: [{
      img: 1,
      name: "教程1",
      sale: 599,
      factory: 899,
      payNum: 2342
    },
       
    ],
    loadding: false,
    pullUpOn: true
  },
  onLoad: function(options) {

  },
  search: function () {
 this.onPullDownRefresh();
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    //延时为了看效果
    setTimeout(() => {
      this.setData({
        productList: this.data.loadData,
        pageIndex: 1,
        pullUpOn: true,
        loadding: false
      })
      wx.stopPullDownRefresh()
      wx.showToast({
        title: '刷新成功',
        icon: "none"
      })
    }, 200)
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    if (!this.data.pullUpOn) return;
    this.setData({
      loadding: true
    }, () => {
      if (this.data.pageIndex == 3) {
        this.setData({
          loadding: false,
          pullUpOn: false
        })
      } else {
        this.setData({
          productList: this.data.productList.concat(this.data.loadData),
          pageIndex: this.data.pageIndex + 1,
          loadding: false
        })
      }
    })
  },
  detail(e) {
    wx.navigateTo({
      url: 'detail'
    })
  }
})