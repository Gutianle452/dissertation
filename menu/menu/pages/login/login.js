const util = require('../../utils/util.js')
let globalData = getApp().globalData
Page({
  data: {
    disabled: false,

    username: "12345678901",
    type: "primary",
    password: "12345678901"
  },
  onLoad: function (options) { },

  input: function (e) {
    this.setData({
      username: e.detail.value
    });
  },

  btnSend: function () {
    if (util.isNullOrEmpty(this.data.username)) {
      util.toast('请输入用户名');
      return
    }
    if (util.isNullOrEmpty(this.data.password)) {
      util.toast('请输入密码');
      return
    }



  },
  login: function (e) {
    let password = e.detail.value.password;
    let username = e.detail.value.username;
    if (util.isNullOrEmpty(username)) {
      util.toast('请输入手机号码');
      return
    } else if (util.isNullOrEmpty(password)) {
      util.toast('请输入密码');
      return
    }

    let that = this;
    wx.request({
      url: getApp().globalData.server + "cus/api/user/login",
      data: { "username": username, "password": password },
      method: 'post',
      //  header: { 'Content-Type': 'application/json' },
      header: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
      success: function (res) {
        console.log(res.data);
        if (res.data.code == 0) {
          globalData.isLogin = true;
          globalData.userId = res.data.data.id;
          wx.setStorageSync("thorui_mobile", username);
          util.toast("登录成功", 2000, true);
          setTimeout(() => {
            wx.reLaunch({
              url: '../home/home'
            })
          }, 200);
        } else {
          util.toast('账号密码不对');
        }

      },
      fail: function (r) {
        console.log(r);
      }
    })


  },
  protocol: function () {
    wx.navigateTo({
      url: 'register'
    })
  }
})