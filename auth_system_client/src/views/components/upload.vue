<template>
  <el-dialog
    title=""
    width="600px"
    v-model="data.visible"
    :destroy-on-close="true"
    append-to-body
    @closed="close"
  >
    <div class="upload">
      <el-upload
        drag
        ref="upload"
        :multiple="false"
        :file-list="data.files"
        :auto-upload="true"
        :action="data.url"
        :data="data.data"
        :headers="data.headers"
        :on-change="handleChange"
        :before-upload="handleBeforeUpload"
        :on-success="handleSucess"
        :on-progress="handleProcess"
        :on-error="handleError"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或<em>点击上传</em>
        </div>
        <div
          class="el-upload__tip"
          slot="tip"
          v-show="data.uploadStatus"
        >
          <i class="el-icon-loading"></i>
          正在处理数据，请稍后......
        </div>
      </el-upload>
    </div>
  </el-dialog>
</template>
<script lang="ts" setup>
import Api from '@/api/sys/api_sysossfile.js'
import Vue, {watch} from 'vue'
import { reactive, ref } from 'vue'
import {ElMessage} from "element-plus";
// Data
const data = reactive({
    visible: false,
    uploadStatus: false,
    oss: false,
    readOnlyStatus: false,
    files: [],
    url: '',
    headers: {
        Authorization: 'Bearer ' + localStorage.getItem('inmsToken'),
        'X-soource': 'PC'
    },
    data: {
        groupId: ''
    }
})

// Props
const props = defineProps({
    //子组件接收父组件传递过来的值
    groupId: {
        type: String,
        default: '',
        required: false
    },
    readOnly: {
        type: Boolean,
        default: false,
        required: false
    }
})

// Methods
const init = (action = '/api/sysossfile/uploadOSS') => {
    console.log(action)
    data.visible = true
    data.uploadStatus = false
    data.drag = true
    data.files = []
    data.url = "http://localhost:8888/" + action + '?_=' + new Date().getTime()
    console.log(data.url)
    if (action === '/api/sysossfile/uploadOSS') {
        data.oss = true
        getFiles()
    } else {
        data.oss = false
    }
}

// 初始化时获取附件列表
const getFiles = () => {
    const params = {
        groupId: data.data.groupId
    }
    Api.sellist4sysossfile(params).then(data1 => {
        data.files = data1
    })
}

// 文件变更时的回调函数
// excel导入：只允许显示一个文件
// 对象存储：允许上传多个文件
const handleChange = (file, fileList) => {
    if (!data.oss) {
        if (fileList.length > 0) {
            fileList = [fileList[fileList.length - 1]]
        }
        data.files = fileList
    }
}

// 上传前的回调函数
// 对象存储：只读情况下，提示没有权限，不允许上传
// excel导入：不做任何校验，readOnlyStatus 参数不生效
const handleBeforeUpload = (file) => {
    if (file.size > 100 * 1024 * 1024) {
        ElMessage({
            message: '文件过大，超过100M，请重新选择！',
            type: 'warning',
        })
        return false   //必须返回false
    }
    if (data.oss && data.readOnlyStatus) {
        ElMessage({
            message: '没有权限',
            type: 'warning',
        })
        return false
    }
}

// 上传时的回调函数
// 显示“正在处理中”的提示信息
const handleProcess = (event, file, fileList) => {
    data.uploadStatus = true
}

// 上传成功回调函数
// 对象存储：提示成功，并更新文件列表、groupId，同时返回 response，调用回调函数，由调用者处理
// excel导入：只返回 response，调用回调函数，由调用者处理
const emits = defineEmits(["callback"]);
const handleSucess = (response, file, fileList) => {
    data.uploadStatus = false
    console.log(response)
    // if(response.data !==null && response.data.result !==undefined) {
    //     if (response.data.result == 'true') {
    //         data.data.groupId = response.data.groupId
    //         getFiles()
    //         ElMessage({
    //             message: '上传成功',
    //             type: 'success',
    //         })
    //         // data.$emit('changeGroupId', response.data.groupId)
    //     } else if (response.data.result == 'false') {
    //         getFiles()
    //         ElMessage({
    //             message: '失败',
    //             type: 'warning',
    //         })
    //     }
    // }
    emits('callback', 'put', true, data.data.groupId, response)
}

// 上传失败的回调函数
// 对象存储：提示失败，同时返回 response，调用回调函数，由调用者处理
// excel导入：只返回 response，调用回调函数，由调用者处理
const handleError = (err, file, fileList) => {

    data.uploadStatus = false
    if (data.oss) {
        ElMessage({
            message: '错误',
            type: 'warning',
        })
    }
    // data.$emit('callback', 'put', false, data.data.groupId, err)
}

// 点击显示文件的回调函数
const handlePreview = (file) => {
    if (file.id === undefined) {
        return false
    }
    Api.selurl4sysossfile(file.id).then(response => {
        window.open(response.url)
    }).catch(err => {
        ElMessage({
            message: 'err.message',
            type: 'warning',
        })
    })
}

// 删除文件的回调函数
// excel导入：不执行后台删除请求
// 文件id 不存在时，返回 false
// 对象存储：先校验是否只读。readOnlyStatus=true，不允许删除；readOnlyStatus=false，提示删除成功或失败，并且返回 response，调用回调函数，由调用者处理
const handleRemove = (file, fileList) => {
    if (!data.oss) {
        return false
    }
    if (file.id === undefined) {
        return false
    }
    if (data.readOnlyStatus) {
        ElMessage({
            message: '没有权限',
            type: 'warning',
        })
        return false
    }
    Api.del4sysossfile(file.id).then(response => {
        getFiles()
        ElMessage({
            message: '删除成功',
            type: 'warning',
        })
        // data.$emit('callback', 'delete', true, data.data.groupId, response)
    }).catch(err => {
        ElMessage({
            message: 'err.message',
            type: 'warning',
        })
        // data.$emit('callback', 'delete', false, data.data.groupId, err)
    })
}

// 关闭时的回调函数
// const close () => {
//     // TODO
// }

// Watch
// watch(data.data.groupId, () => {
//     data.data.groupId = newVal
// })
// watch(data.readOnly, () => {
//     data.readOnlyStatus = newVal
// })

//暴露state和play方法
defineExpose({
    init,
});
</script>

<style scoped>
  .upload {
    display: flex;
    justify-content: center;
  }
  .el-upload__tip {
    text-align: center;
  }
  /deep/ .el-list-enter-active,
  /deep/ .el-list-leave-active {
    transition: none;
  }

  /deep/ .el-list-enter,
  /deep/ .el-list-leave-active {
    opacity: 0;
  }
  /*/deep/ .el-upload-list {*/
  /*  height: 40px;*/
  /*}*/
</style>
