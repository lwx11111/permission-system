import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  listDatasetByRole(data) {
    return request({
      url: serverName + '/sysdataset/listDatasetByRole',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4sysdataset(data) {
    return request({
      url: serverName + '/sysdataset/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysdataset(obj) {
    return request({
      url: serverName + '/sysdataset',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysdataset(id, obj) {
    return request({
      url: serverName + '/sysdataset/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysdataset(id) {
    return request({
      url: serverName + '/sysdataset/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysdataset(ids) {
    return request({
      url: serverName + '/sysdataset/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysdataset(id) {
    return request({
      url: serverName + '/sysdataset/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysdataset/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysdataset/uploadExcel';
  },

  // 导出Excel
  excelData4sysdataset(params) {
    return request({
      url: serverName + '/sysdataset/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
