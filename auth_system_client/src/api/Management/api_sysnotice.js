import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4sysnotice(data) {
    return request({
      url: serverName + '/sysnotice/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysnotice(obj) {
    return request({
      url: serverName + '/sysnotice',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysnotice(id, obj) {
    return request({
      url: serverName + '/sysnotice/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysnotice(id) {
    return request({
      url: serverName + '/sysnotice/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysnotice(ids) {
    return request({
      url: serverName + '/sysnotice/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysnotice(id) {
    return request({
      url: serverName + '/sysnotice/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysnotice/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysnotice/uploadExcel';
  },

  // 导出Excel
  excelData4sysnotice(params) {
    return request({
      url: serverName + '/sysnotice/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
