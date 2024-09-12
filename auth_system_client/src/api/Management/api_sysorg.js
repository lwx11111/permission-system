import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  listOrgsByCompany(data) {
    return request({
      url: serverName + '/sysorg/listOrgsByCompany',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4sysorg(data) {
    return request({
      url: serverName + '/sysorg/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysorg(obj) {
    return request({
      url: serverName + '/sysorg',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysorg(id, obj) {
    return request({
      url: serverName + '/sysorg/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysorg(id) {
    return request({
      url: serverName + '/sysorg/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysorg(ids) {
    return request({
      url: serverName + '/sysorg/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysorg(id) {
    return request({
      url: serverName + '/sysorg/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysorg/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysorg/uploadExcel';
  },

  // 导出Excel
  excelData4sysorg(params) {
    return request({
      url: serverName + '/sysorg/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
