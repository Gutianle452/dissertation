const util = require('../../utils/util.js')
Page({
  data: {
    menuId: '',
    content: ''
  },
  onLoad: function (options) {
    this.setData({
      menuId: options.id
    })
  },
  input: function (e) {
    this.setData({
      content: e.detail.value
    });
  },
  protocol: function () {
    if (this.data.content == '') {
      wx.showModal({
        title: '提示',
        content: '请先输入内容',
        confirmText: '好的',
        confirmColor: '#ACB4E3',
        showCancel: false,
      });
    }
    else {

      this.liu();
      util.toast("发表成功", 2000, true);
    }


  },
  liu: function () {

    let that = this;
    wx.request({
      url: getApp().globalData.server + "cus/comment/save",
      data: { "menuId": that.data.menuId, "content": that.data.content, "userId": getApp().globalData.userId },
      method: 'post',
      header: { 'Content-Type': 'application/json' },
      success: function (res) {
        that.setData({
          city: '请输入留言内容'
        });



      },
      fail: function (r) {
        console.log(r);
      }
    })

  }
 
})