import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  lock(id){
    return request({
      url: serverName + '/sysuser/lock/' + id,
      method: 'get',
    })
  },

  unlock(id){
    return request({
      url: serverName + '/sysuser/unlock/' + id,
      method: 'get',
    })
  },

  // 分页查询
  selpage4sysuser(data) {
    return request({
      url: serverName + '/sysuser/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysuser(obj) {
    return request({
      url: serverName + '/sysuser',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysuser(id, obj) {
    return request({
      url: serverName + '/sysuser/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysuser(id) {
    return request({
      url: serverName + '/sysuser/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysuser(ids) {
    return request({
      url: serverName + '/sysuser/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysuser(id) {
    return request({
      url: serverName + '/sysuser/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysuser/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysuser/uploadExcel';
  },

  // 导出Excel
  excelData4sysuser(params) {
    return request({
      url: serverName + '/sysuser/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
