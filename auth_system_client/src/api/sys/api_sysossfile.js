import request from '@/utils/request.js'

export default {
  // 添加
  // add4sysossfile: obj => {
  //   return Vue.axios.post(serverName + '/api/sysossfile', obj)
  // },
  // // 修改
  // update4sysossfile: (id, obj) => {
  //   return Vue.axios.put(serverName + `/api/sysossfile/${id}`, obj)
  // },
  // 删除单条
  del4sysossfile(id) {
    return request({
      url: '/api/sysossfile/' + id,
      method: 'delete',
    })
  },
  // // 删除多条
  // dels4sysossfile: ids => {
  //   return Vue.axios.post(serverName + '/api/sysossfile/dels', ids)
  // },
  // // 查询单条
  // sel4sysossfile: id => {
  //   return Vue.axios.get(serverName + `/api/sysossfile/${id}`)
  // },
  // // 分页查询
  // selpage4sysossfile: params => {
  //   return Vue.axios.post(serverName + '/api/sysossfile/selpage', params)
  // },
  // 列表查询
  sellist4sysossfile: params => {
    // return Vue.axios.post(serverName + '/api/sysossfile/selby', params)
  },
  // // 列表查询
  // selphotopreview4sysossfile: params => {
  //   return Vue.axios.post(serverName + '/api/sysossfile/photoPreview1', params)
  // },
  // // 列表查询
  // sellist4sysossfilebybcfj: params => {
  //   return Vue.axios.post(serverName + '/api/sysossfile/selbyBcfj', params)
  // },
  // // 导出Excel
  // excelData4sysossfile: params => {
  //   return Vue.axios.post(serverName + '/api/sysossfile/excel', params, { responseType: 'arraybuffer' })
  // },
  // 查询单条文件的下载 url
  selurl4sysossfile: id => {
    // return Vue.axios.get(serverName + `/api/sysossfile/url/${id}`)
  },
  // // 下载单个文件
  // downSysossfile: id => {
  //   return Vue.axios.get(serverName + `/api/sysossfile/downloadFile/${id}`, { responseType: 'blob' })
  // },
  // // 列表查询
  // getNum: params => {
  //   return Vue.axios.post(serverName + '/api/sysossfile/getNum', params)
  // },
  // getZip: ids => {
  //   return Vue.axios.get(serverName + `/api/sysossfile/getZip/${ids}`, { responseType: 'blob' })
  // },
  // //考核工单查询附件列表
  // getfileList: params => {
  //   return Vue.axios.post(serverName + `/api/khflow/getUploadList`,params)
  // }
}
