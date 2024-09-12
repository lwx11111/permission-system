<template>
    <el-upload :class="{ hide_box: data.upload_btn }"
               ref="minioUpload"
               :action="data.minioUrl"
               :file-list="data.fileList"
               :on-remove="handleRemove"
               :limit="1"
               list-type="picture-card"
               :on-success="handleSuccess"
               :on-error="handleError"
               :auto-upload="true">
        <!--添加-->
        <el-icon><Plus /></el-icon>
        <!--上传文件展示-->
        <template #file="{ file }">
            <div>
                <img  class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                <span class="el-upload-list__item-actions">
                    <!-- 放大-->
                    <span
                            class="el-upload-list__item-preview"
                            @click="handlePictureCardPreview(file)"
                    >
                      <el-icon><zoom-in /></el-icon>
                    </span>
                    <!-- 下载-->
                    <span
                            v-if="!disabled"
                            class="el-upload-list__item-delete"
                            @click="handleDownload(file)"
                    >
                      <el-icon><Download /></el-icon>
                    </span>
                    <!-- 删除-->
                    <span
                            v-if="!disabled"
                            class="el-upload-list__item-delete"
                            @click="handleRemove(file)"
                    >
                      <el-icon><Delete /></el-icon>
                    </span>
                </span>
            </div>
        </template>
    </el-upload>
    <!-- 放大显示-->
    <el-dialog style="width: 800px" v-model="dialogVisible">
        <img :src="dialogImageUrl" alt="Preview Image" />
    </el-dialog>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
import type { UploadProps, UploadUserFile, UploadRawFile } from 'element-plus'
import type { UploadFile } from 'element-plus'
import { ElMessage } from "element-plus";
import ApiOss from "@/api/sys/api_sysoss.js";

const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const disabled = ref(false)

// Data
const data = reactive({
    // 达到limit限制时隐藏上传按钮,false显示
    upload_btn: false,
    minioUrl: "http://localhost:8921/basic/sysoss/anon/uploadOSS",
    minioServerUrl: "http://127.0.0.1:9000/",
    // 上传的图片数组
    fileList:[],
    // 图片url
    url:'',
})

// Props
const props = defineProps({
    // key1 key2 用来回调时区分，key1区分来源那个，key2区分该来源具体下标
    key1:{
        type: String,
        required: false
    },
    key2:{
        type: Number,
        required: false
    },
    // 上传的url 用来展示
    url: {
        type: String,
        required: false
    },
    // 只展示标志
    disabled: {
        type: Boolean,
        default: false,
        required: false
    },
})

// Mounted
onMounted(() => {
    // 如果已经有图片
    if (props.url) {
        let obj = {
            name: 'pic',
            url: props.url
        }
        data.url = props.url;
        data.fileList.push(obj);
        data.upload_btn = true
    }
    // 是否只展示
    if (props.disabled) {
        disabled.value = props.disabled
    }
})

/**
 * 上传成功回调函数，对象存储：提示成功，并更新文件列表、groupId，同时返回 response，调用回调函数，由调用者处理
 * excel导入：只返回 response，调用回调函数，由调用者处理
 * @param response
 * @param file
 * @param fileList
 */
const emits = defineEmits(["getUrl"]);
const handleSuccess: UploadProps['onSuccess'] = (response, file, fileList) => {
    if (response.code === 200){
        // 上传路径
        const url = data.minioServerUrl + response.data.bucket + "/" + response.data.storageFileName;
        data.url = url;
        data.upload_btn = true
        // 回调
        emits("getUrl",url,props.key1,props.key2);
    } else {
        data.fileList = []
        ElMessage({
            type: 'error',
            message: response.message
        })
    }
}


/**
 * 删除图片
 */
const handleRemove = (file: UploadFile) => {
    const param = {
        url: data.url
    }
    ApiOss.deleteFileByUrl(param).then(res => {
        if (res.code === 200 && res.data === true) {
            ElMessage({
                type: 'success',
                message: '删除成功'
            })
            data.fileList = []
            data.upload_btn = false
        } else {
            ElMessage({
                type: 'error',
                message: '删除失败'
            })
        }
    })

}

const handlePictureCardPreview = (file: UploadFile) => {
    dialogImageUrl.value = file.url!
    dialogVisible.value = true

}

const handleDownload = (file: UploadFile) => {

}


const handleError = (err: Error, file: UploadRawFile, fileList: UploadUserFile[]) => {
    console.log(err, file, fileList)
    ElMessage({
        type: 'error',
        message: '上传图片失败，请重新上传或联系管理员',
    })
}
</script>
<style>
    .hide_box .el-upload--picture-card {
        display: none;
    }
</style>
