import request from '@/utils/request.js';
const serverName = '/manager';

export default {
  listFunctionsByAccount(data) {
    return request({
      url: serverName + '/sysfunction/listFunctionsByAccount',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4sysfunction(data) {
    return request({
      url: serverName + '/sysfunction/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysfunction(obj) {
    return request({
      url: serverName + '/sysfunction',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysfunction(id, obj) {
    return request({
      url: serverName + '/sysfunction/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysfunction(id) {
    return request({
      url: serverName + '/sysfunction/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysfunction(ids) {
    return request({
      url: serverName + '/sysfunction/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysfunction(id) {
    return request({
      url: serverName + '/sysfunction/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysfunction/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysfunction/uploadExcel';
  },

  // 导出Excel
  excelData4sysfunction(params) {
    return request({
      url: serverName + '/sysfunction/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
