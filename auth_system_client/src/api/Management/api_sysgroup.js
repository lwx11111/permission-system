import request from '@/utils/request.js';
const serverName = '/manager';

export default {
  listGroups(data) {
    return request({
      url: serverName + '/sysgroup/listGroups',
      method: 'post',
      data: data
    })
  },

  saveMembers(data) {
    return request({
      url: serverName + '/sysgroup/saveMembers',
      method: 'post',
      data: data
    })
  },

  listMembers(data) {
    return request({
      url: serverName + '/sysgroup/listMembers',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4sysgroup(data) {
    return request({
      url: serverName + '/sysgroup/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysgroup(obj) {
    return request({
      url: serverName + '/sysgroup',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysgroup(id, obj) {
    return request({
      url: serverName + '/sysgroup/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysgroup(id) {
    return request({
      url: serverName + '/sysgroup/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysgroup(ids) {
    return request({
      url: serverName + '/sysgroup/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysgroup(id) {
    return request({
      url: serverName + '/sysgroup/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysgroup/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysgroup/uploadExcel';
  },

  // 导出Excel
  excelData4sysgroup(params) {
    return request({
      url: serverName + '/sysgroup/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
