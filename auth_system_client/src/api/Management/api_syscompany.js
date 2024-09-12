import request from '@/utils/request.js';
const serverName = '/manager';

export default {
  listCompany(data) {
    return request({
      url: serverName + '/syscompany/listCompany',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4syscompany(data) {
    return request({
      url: serverName + '/syscompany/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4syscompany(obj) {
    return request({
      url: serverName + '/syscompany',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4syscompany(id, obj) {
    return request({
      url: serverName + '/syscompany/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4syscompany(id) {
    return request({
      url: serverName + '/syscompany/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4syscompany(ids) {
    return request({
      url: serverName + '/syscompany/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4syscompany(id) {
    return request({
      url: serverName + '/syscompany/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/syscompany/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/syscompany/uploadExcel';
  },

  // 导出Excel
  excelData4syscompany(params) {
    return request({
      url: serverName + '/syscompany/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
