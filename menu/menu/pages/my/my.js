let globalData = getApp().globalData;
const util = require('../../utils/util.js')
Page({
  data: {
    memberName: 'echo.', //昵称
    isLogin: false
  },
  onLoad: function(options) {},
  onShow: function() {
    let isLogin = globalData.isLogin;
    if (isLogin) {
      this.setData({
        isLogin: isLogin,
        memberName: util.formatNum(wx.getStorageSync("thorui_mobile") || 'echo.')
      });
    }
  },
  logout: function() {
    wx.showModal({
      title: '提示',
      content: '确定退出登录？',
      confirmColor: '#5677FC',
      success: (res) => {
        if (res.confirm) {
          wx.clearStorage()
          wx.reLaunch({
            url: '../login/login'
          })
        }
      }
    });
  },
  edit() {
    wx.showToast({
      title: '功能开发中~',
      icon: "none"
    })
  },
  tapEvent: function(e) {
    let index = e.currentTarget.dataset.index;
    let url = "";
    if (index == 1) {
      url = 'info'
    } else if (index == 2) {
      let key = e.currentTarget.dataset.key;
      url = 'likes'
    } else if (index == 3) {
      
      url = 'commentlist'
    } else if (index == 4) {

      url = 'zan'
    } else if (index == 5) {

      url = 'articlelist'
    } else if (index == 6) {

      url = 'article'
    }
    wx.navigateTo({
      url: url
    })
  }  
})