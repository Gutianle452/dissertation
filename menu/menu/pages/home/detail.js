const util = require('../../utils/util.js')
Page({
  data: {
    fabulous: 123,

    
   
    pageIndex: 1,
    loadding: false,
    pullUpOn: true,
    gonggaoId: '',
  },
  onLoad: function (options) {
    var id = (options.id);
console.log(id);
    this.setData({
      gonggaoId: id
    })
    let that = this;
    wx.request({
      url: getApp().globalData.server + "cus/gonggao/info/" + id,
      data: {},
      method: 'post',
      header: { 'Content-Type': 'application/json' },
      success: function (res) {
        let data = res.data.gonggao;
        console.log(data);
        var dd = {
          id: data.id,
          img: getApp().globalData.server + data.pic,
          content: data.content,
          name: data.name,
          time: data.createTime,
         
        };

        that.setData({
          item: dd
        })
 


      },
      fail: function (r) {
        console.log(r);
      }
    })
  },

 

})