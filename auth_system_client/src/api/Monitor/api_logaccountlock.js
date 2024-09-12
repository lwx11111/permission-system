import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4logaccountlock(data) {
    return request({
      url: serverName + '/logaccountlock/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4logaccountlock(obj) {
    return request({
      url: serverName + '/logaccountlock',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4logaccountlock(id, obj) {
    return request({
      url: serverName + '/logaccountlock/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4logaccountlock(id) {
    return request({
      url: serverName + '/logaccountlock/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4logaccountlock(ids) {
    return request({
      url: serverName + '/logaccountlock/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4logaccountlock(id) {
    return request({
      url: serverName + '/logaccountlock/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/logaccountlock/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/logaccountlock/uploadExcel';
  },

  // 导出Excel
  excelData4logaccountlock(params) {
    return request({
      url: serverName + '/logaccountlock/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
