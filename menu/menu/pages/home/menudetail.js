const util = require('../../utils/util.js')
Page({
  data: {
    fabulous: 123,
 
    isCollection: false,
    isZan: false,
    cmtList: [  ],
    pageIndex: 1,
    loadding: false,
    pullUpOn: true,
    menuId:'',
  },
  onLoad: function (options) {
    var id = (options.id);
   
    this.setData({
      menuId: id
    })
    let that = this;
    wx.request({
      url: getApp().globalData.server + "cus/menu/info/" + id,
      data: {},
      method: 'post',
      header: { 'Content-Type': 'application/json' },
      success: function (res) {
        let data = res.data.menu;
        console.log(data);
        var dd = {
          id: data.id,
          img: getApp().globalData.server + data.pic,
          content: data.content,
          name: data.name,
          time: data.createTime,
          video: getApp().globalData.server + data.video,
        };

        that.setData({
          item: dd
        })

        let comment = res.data.comment;
        that.setData({
          cmtList: comment
        })


      },
      fail: function (r) {
        console.log(r);
      }
    })
  },
  
 
  collection: function () {
    this.setData({
      isCollection: !this.data.isCollection
    }, () => {
      if (this.data.isCollection) {
        {

          let that = this;
          wx.request({
            url: getApp().globalData.server + "cus/likes/save",
            data: { "menuId": that.data.menuId, "userId": getApp().globalData.userId },
            method: 'post',
            header: { 'Content-Type': 'application/json' },
            success: function (res) {
              util.toast("收藏成功", 2000, true);



            },
            fail: function (r) {
              console.log(r);
            }
          })

        }
      }
    })
  },

  zan: function () {
    this.setData({
      isZan: !this.data.isZan
    }, () => {
      if (this.data.isZan) {

         {

          let that = this;
          wx.request({
            url: getApp().globalData.server + "cus/zan/save",
            data: { "menuId": that.data.menuId, "userId": getApp().globalData.userId },
            method: 'post',
            header: { 'Content-Type': 'application/json' },
            success: function (res) {
              util.toast("点赞成功", 2000, true);



            },
            fail: function (r) {
              console.log(r);
            }
          })

        }
      }
    })
  },
  btnCmt: function () {
    wx.navigateTo({
      url: 'comment?id='+this.data.menuId
    })
 
  },

})