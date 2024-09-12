import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4logplatformoper(data) {
    return request({
      url: serverName + '/logplatformoper/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4logplatformoper(obj) {
    return request({
      url: serverName + '/logplatformoper',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4logplatformoper(id, obj) {
    return request({
      url: serverName + '/logplatformoper/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4logplatformoper(id) {
    return request({
      url: serverName + '/logplatformoper/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4logplatformoper(ids) {
    return request({
      url: serverName + '/logplatformoper/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4logplatformoper(id) {
    return request({
      url: serverName + '/logplatformoper/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/logplatformoper/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/logplatformoper/uploadExcel';
  },

  // 导出Excel
  excelData4logplatformoper(params) {
    return request({
      url: serverName + '/logplatformoper/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
