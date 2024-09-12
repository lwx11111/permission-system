import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4sysapp(data) {
    return request({
      url: serverName + '/sysapp/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysapp(obj) {
    return request({
      url: serverName + '/sysapp',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysapp(id, obj) {
    return request({
      url: serverName + '/sysapp/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysapp(id) {
    return request({
      url: serverName + '/sysapp/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysapp(ids) {
    return request({
      url: serverName + '/sysapp/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysapp(id) {
    return request({
      url: serverName + '/sysapp/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysapp/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysapp/uploadExcel';
  },

  // 导出Excel
  excelData4sysapp(params) {
    return request({
      url: serverName + '/sysapp/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
