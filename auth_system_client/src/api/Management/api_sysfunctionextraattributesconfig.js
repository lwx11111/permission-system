import request from '@/utils/request.js';
const serverName = '/manager';

export default {

  // 分页查询
  selpage4sysfunctionextraattributesconfig(data) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4sysfunctionextraattributesconfig(obj) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4sysfunctionextraattributesconfig(id, obj) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4sysfunctionextraattributesconfig(id) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig/' + id,
      method: 'delete'
    })
  },


  // 删除多条
  dels4sysfunctionextraattributesconfig(ids) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4sysfunctionextraattributesconfig(id) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig/' + id,
      method: 'get'
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/sysfunctionextraattributesconfig/uploadExcel';
  },

  // 导出Excel
  excelData4sysfunctionextraattributesconfig(params) {
    return request({
      url: serverName + '/sysfunctionextraattributesconfig/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
