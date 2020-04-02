const form = require("../../components/utils/formValidation.js")
Page({
  data: {
    name: '', phone: '',
  },
  onLoad: function (options) {


    let that = this;
    wx.request({
      url: getApp().globalData.server + "cus/user/info/" + getApp().globalData.userId,
      data: {},
      method: 'post',
      header: { 'Content-Type': 'application/json' },
      success: function (res) {
        let data = res.data.user;


        that.setData({
          name: data.nickname,
          phone: data.phone,
        })



      },
      fail: function (r) {
        console.log(r);
      }
    })

  },
  formSubmit: function (e) {
    //表单规则
    let rules = [{
      name: "name",
      rule: ["required", "minLength:2", "maxLength:6"], //可使用区间，此处主要测试功能
      msg: ["请输入姓名", "姓名必须2个或以上字符", "姓名不能超过6个字符"]
    }, {
      name: "mobile",
      rule: ["required", "isMobile"],
      msg: ["请输入手机号", "请输入正确的手机号"]
    }];
    //进行表单检查
    let formData = e.detail.value;
    let checkRes = form.validation(formData, rules);
    if (!checkRes) {
      wx.request({
        url: getApp().globalData.server + "cus/user/update",
        data: { "username": e.detail.value.mobile, "phone": e.detail.value.mobile, "nickname": e.detail.value.name, "id": getApp().globalData.userId },
        method: 'post',
        header: { 'Content-Type': 'application/json' },
        // header: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
        success: function (res) {

          if (res.data.code == 0) {
            wx.showToast({
              title: "修改成功!",
              icon: "none"
            });
            wx.navigateBack({
              delta: 1
            })
          } else {
            wx.showToast({
              title: "修改不成功!",
              icon: "none"
            });
          }

        },
        fail: function (r) {
          console.log(r);
        }
      })
    } else {
      wx.showToast({
        title: checkRes,
        icon: "none"
      });
    }
  },
  formReset: function (e) {
    console.log("清空数据")
  }
})