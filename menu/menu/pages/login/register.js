const form = require("../../components/utils/formValidation.js")
Page({
  data: {

  },
  onLoad: function (options) {

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
    }, {
      name: "pwd",
      rule: ["required", "isEnAndNo"],
      msg: ["请输入密码", "密码为8~20位数字和字母组合"]
    }, {
      name: "pwd2",
      rule: ["required", "isSame:pwd"],
      msg: ["请输入确认密码", "两次输入的密码不一致"]
    }];
    //进行表单检查
    let formData = e.detail.value;
    let checkRes = form.validation(formData, rules);
    if (!checkRes) {
      wx.request({
        url: getApp().globalData.server + "cus/user/save",
        data: { "username": e.detail.value.mobile, "phone": e.detail.value.mobile, "nickname": e.detail.value.name, "password": e.detail.value.pwd },
        method: 'post',
        header: { 'Content-Type': 'application/json' },
        // header: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
        success: function (res) {

          if (res.data.code == 0) {
            wx.showToast({
              title: "注册成功!",
              icon: "none"
            });
            wx.navigateBack({
              delta: 1
            })
          } else {
            wx.showToast({
              title: "注册不成功!",
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