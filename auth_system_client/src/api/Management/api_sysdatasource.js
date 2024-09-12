import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4sysdatasource(data) {
    return request({
      url: serverName + '/sysdatasource/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysdatasource(obj) {
    return request({
      url: serverName + '/sysdatasource',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysdatasource(id, obj) {
    return request({
      url: serverName + '/sysdatasource/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysdatasource(id) {
    return request({
      url: serverName + '/sysdatasource/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysdatasource(ids) {
    return request({
      url: serverName + '/sysdatasource/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysdatasource(id) {
    return request({
      url: serverName + '/sysdatasource/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysdatasource/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysdatasource/uploadExcel';
  },

  // 导出Excel
  excelData4sysdatasource(params) {
    return request({
      url: serverName + '/sysdatasource/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
