const util = require('../../utils/util.js')
Page({
  data: {
    imagesrc: '',
    imagepath:'',
    content:'',
    name:'',
    //上传地址
    serverUrl: "http://127.0.0.1/cus/api/upload"
  },
  onLoad: function(options) {
  },
  input: function (e) {
    this.setData({
      content: e.detail.value
    });
  },
  nameinput: function (e) {
    this.setData({
      name: e.detail.value
    });
  },
protocol: function() {
  if (this.data.content == '') {
    wx.showModal({
      title: '提示',
      content: '请先输入内容',
      confirmText: '好的',
      confirmColor: '#ACB4E3',
      showCancel: false,
    });
  }
  else if (this.data.name == '') {
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
  upload: function (e) {
    var that=this;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths;
        console.log("ddd");

        wx.uploadFile({
          url: getApp().globalData.server + "cus/api/wxupload", //服务器上传接口
          filePath: tempFilePaths[0], //文件资源路径
          name: 'file',
          header: {
            'Content-Type': 'Application/json'
          },
          success(res) {
            console.log(res)
            if (res.statusCode == 200) {
              that.setData({
                imagesrc: getApp().globalData.server + res.data,
                imagepath:res.data
              });
            }
          }
        }) 
        
        
      }
    })
  },
  liu: function () {

    let that = this;
    wx.request({
      url: getApp().globalData.server + "cus/gonggao/save",
      data: { "pic": that.data.imagepath, "content": that.data.content, "name": that.data.name, "userId": getApp().globalData.userId },
      method: 'post',
      header: { 'Content-Type': 'application/json' },
      success: function (res) {
         



      },
      fail: function (r) {
        console.log(r);
      }
    })

  } 
})