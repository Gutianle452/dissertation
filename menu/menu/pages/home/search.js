const util = require('../../utils/util.js')
Page({
  data: {
    
    key: "",
    showActionSheet: false,
    tips: "确认清空搜索历史吗？"
  },
  onLoad: function(options) {

  },
  search: function() {
   // wx.navigateBack();
  },
  input: function(e) {
    let key = util.trim(e.detail.value);
    this.setData({
      key: key
    })
  },
  cleanKey: function() {
    this.setData({
      key: ''
    });
  },
  closeActionSheet: function() {
    this.setData({
      showActionSheet: false
    })
  },
  openActionSheet: function() {
    this.setData({
      showActionSheet:true
    })
  },
  itemClick: function(e) {
    let index = e.detail.index;
    if (index == 0) {
      this.setData({
        showActionSheet: false,
        history: []
      })
    }
  }
})