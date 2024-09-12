import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4syssetting(data) {
    return request({
      url: serverName + '/syssetting/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4syssetting(obj) {
    return request({
      url: serverName + '/syssetting',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4syssetting(id, obj) {
    return request({
      url: serverName + '/syssetting/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4syssetting(id) {
    return request({
      url: serverName + '/syssetting/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4syssetting(ids) {
    return request({
      url: serverName + '/syssetting/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4syssetting(id) {
    return request({
      url: serverName + '/syssetting/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/syssetting/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/syssetting/uploadExcel';
  },

  // 导出Excel
  excelData4syssetting(params) {
    return request({
      url: serverName + '/syssetting/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
