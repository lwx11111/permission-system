import request from '@/utils/request.js'
const serverName = '/basic'

export default {
  deleteFileByUrl(data){
    return request({
      url: serverName + '/sysoss/anon/deleteFileByUrl',
      method: 'post',
      data: data
    })
  },

  deleteFileByStorageFileName(data) {
    return request({
      url: '/basic/file/sysoss/deleteFileByStorageFileName',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4sysoss(data) {
    return request({
      url: serverName + '//sysoss/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysoss(obj) {
    return request({
      url: serverName + '//sysoss',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysoss(id, obj) {
    return request({
      url: serverName + '//sysoss/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysoss(id) {
    return request({
      url: serverName + '//sysoss/' + id,
      method: 'delete',
    })
  },


  // 删除多条
  dels4sysoss(ids) {
    return request({
      url: serverName + '//sysoss/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysoss(id) {
    return request({
      url: serverName + '//sysoss/' + id,
      method: 'get',
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '//sysoss/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return request({
      url: serverName + '//sysoss/uploadExcel',
      type: 'form',
      method: 'post',
    })

  },
  // 导出Excel
  excelData4sysoss(params) {
    return request({
      url: serverName + '//sysoss/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
