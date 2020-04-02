Page({
  data: {
    tabbar: [],//{ id: -1, name: "全部" }, { id: 0, name: "菜品推荐" }
    menuHeight: "", //菜单高度
    currentTab: 0, //预设当前项的值
    scrollTop: 0 //tab标题的滚动条位置
    ,
    newsList: [],
    dataSources: []
  },
  onLoad: function (options) {
    wx.getSystemInfo({
      success: (res) => {
        this.setData({
          menuHeight: res.windowHeight - res.windowWidth / 750 * 92
        });
      }
    });


    var that = this;
    wx.request({
      url: getApp().globalData.server + "cus/category/list1?limit=100",
      data: { "page": 1 },
      method: 'post',
      header: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },

      success: function (res) {
        let temp = res.data.rows;

        var dd = [];
        for (let i = 0, length = temp.length; i < length; i++) {

          dd[i] = { id: temp[i].id, name: temp[i].name };


        }

        that.setData({
          tabbar: that.data.tabbar.concat(dd),


        })

        that.query(0);

      }
    });



  },
  // 点击标题切换当前页时改变样式
  swichNav: function (e) {
   
    let cur = e.currentTarget.dataset.current;

    this.query(cur);
    if (this.data.currentTab == cur) {
      return false;
    } else {
      wx.pageScrollTo({
        scrollTop: 0
      })
      this.setData({
        currentTab: cur
      })
      this.checkCor();
    }
  },
  //判断当前滚动超过一屏时，设置tab标题滚动条。
  checkCor: function () {
    let that = this;
    //这里计算按照实际情况进行修改，动态数据要进行动态分析
    //思路：窗体高度/单个分类高度 200rpx 转px计算 =>得到一屏幕所显示的个数，结合后台传回分类总数进行计算
    //数据很多可以多次if判断然后进行滚动距离计算即可
    if (that.data.currentTab > 7) {
      that.setData({
        scrollTop: 500
      })
    } else {
      that.setData({
        scrollTop: 0
      })
    }
  },

  productList(e) {
    let key = e.currentTarget.dataset.key;
    console.log(key);
    wx.navigateTo({
      url: 'menudetail?id=' + key
    })
  },
  search: function () {
    wx.navigateTo({
      url: 'product'
    })
  },
  query(e) {
    console.log(e);
    var that = this;
    wx.request({
      url: getApp().globalData.server + "cus/menu/list1?limit=100",
      data: { "page": 1, "userId": getApp().globalData.userId
      ,
       "categoryId": this.data.tabbar[e].id },
      method: 'post',
      header: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },

      success: function (res) {
        let temp = res.data.rows;

        var dd = [];
        for (let i = 0, length = temp.length; i < length; i++) {

          dd[i] = ({

            id: temp[i].id,
            img: getApp().globalData.server + temp[i].pic,
            name: temp[i].name,

          });


        }

        that.setData({
          newsList: dd,


        })





      },
      fail: function (r) {
        console.log(r);
      }
    });

  }
})