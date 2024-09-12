import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4syscredentialrule(data) {
    return request({
      url: serverName + '/syscredentialrule/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4syscredentialrule(obj) {
    return request({
      url: serverName + '/syscredentialrule',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4syscredentialrule(id, obj) {
    return request({
      url: serverName + '/syscredentialrule/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4syscredentialrule(id) {
    return request({
      url: serverName + '/syscredentialrule/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4syscredentialrule(ids) {
    return request({
      url: serverName + '/syscredentialrule/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4syscredentialrule(id) {
    return request({
      url: serverName + '/syscredentialrule/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/syscredentialrule/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/syscredentialrule/uploadExcel';
  },

  // 导出Excel
  excelData4syscredentialrule(params) {
    return request({
      url: serverName + '/syscredentialrule/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
