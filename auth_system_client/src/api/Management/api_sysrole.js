import request from '@/utils/request.js';
const serverName = '/manager';

export default {
  roleInheritance(data){
    return request({
      url: serverName + '/sysrole/roleInheritance',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4sysrole(data) {
    return request({
      url: serverName + '/sysrole/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysrole(obj) {
    return request({
      url: serverName + '/sysrole',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysrole(id, obj) {
    return request({
      url: serverName + '/sysrole/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysrole(id) {
    return request({
      url: serverName + '/sysrole/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysrole(ids) {
    return request({
      url: serverName + '/sysrole/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysrole(id) {
    return request({
      url: serverName + '/sysrole/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysrole/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysrole/uploadExcel';
  },

  // 导出Excel
  excelData4sysrole(params) {
    return request({
      url: serverName + '/sysrole/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
