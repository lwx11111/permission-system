import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4logaccountchangepassword(data) {
    return request({
      url: serverName + '/logaccountchangepassword/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4logaccountchangepassword(obj) {
    return request({
      url: serverName + '/logaccountchangepassword',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4logaccountchangepassword(id, obj) {
    return request({
      url: serverName + '/logaccountchangepassword/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4logaccountchangepassword(id) {
    return request({
      url: serverName + '/logaccountchangepassword/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4logaccountchangepassword(ids) {
    return request({
      url: serverName + '/logaccountchangepassword/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4logaccountchangepassword(id) {
    return request({
      url: serverName + '/logaccountchangepassword/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/logaccountchangepassword/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/logaccountchangepassword/uploadExcel';
  },

  // 导出Excel
  excelData4logaccountchangepassword(params) {
    return request({
      url: serverName + '/logaccountchangepassword/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
