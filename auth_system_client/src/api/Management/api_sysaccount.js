import request from '@/utils/request.js';
const serverName = '/manager';

export default {
  savePerm(data) {
    return request({
      url: serverName + '/sysaccount/savePerm',
      method: 'post',
      data: data
    })
  },

  listGroupByAccountId(data) {
    return request({
      url: serverName + '/sysaccount/listGroupByAccountId',
      method: 'post',
      data: data
    })
  },

  getRoleByAccountId(data) {
    return request({
      url: serverName + '/sysaccount/getRoleByAccountId',
      method: 'post',
      data: data
    })
  },

  getUserAndAccountByAccountId(accountId) {
    return request({
      url: serverName + '/sysaccount/getUserAndAccountByAccountId/' + accountId,
      method: 'get',
    })
  },

  resetPassword(accountId) {
    return request({
      url: serverName + '/sysaccount/resetPassword/' + accountId,
      method: 'get',
    })
  },

  unlock(accountId) {
    return request({
      url: serverName + '/sysaccount/unlock/' + accountId,
      method: 'get',
    })
  },

  lock(accountId) {
    return request({
      url: serverName + '/sysaccount/lock/' + accountId,
      method: 'get',
    })
  },

  // 分页查询
  selpage4sysaccount(data) {
    return request({
      url: serverName + '/sysaccount/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysaccount(obj) {
    return request({
      url: serverName + '/sysaccount',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysaccount(id, obj) {
    return request({
      url: serverName + '/sysaccount/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysaccount(id) {
    return request({
      url: serverName + '/sysaccount/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysaccount(ids) {
    return request({
      url: serverName + '/sysaccount/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysaccount(id) {
    return request({
      url: serverName + '/sysaccount/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysaccount/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysaccount/uploadExcel';
  },

  // 导出Excel
  excelData4sysaccount(params) {
    return request({
      url: serverName + '/sysaccount/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
