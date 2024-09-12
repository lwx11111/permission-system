<template>
  <el-dialog
    title="修改密码"
    v-model="data.dialogVisible"
    :before-close="handleClose"
    width="400px">
    <span slot="title" class="dialog-footer">
      <i class="el-icon-key"/> 修改密码
    </span>
    <el-form ref="form"
             :model="data.form"
             label-width="100px"
             :rules="data.rules">
      <el-form-item label="账户名">
        <el-input v-model="data.form.accountName" style="width:250px" :disabled="true"/>
      </el-form-item>
      <el-form-item label="旧密码" prop="oldPass">
        <el-input v-model="data.form.oldPass" style="width:250px" show-password/>
      </el-form-item>
      <el-form-item label="新密码" prop="newPass">
        <el-input v-model="data.form.newPass" style="width:250px" show-password/>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPass">
        <el-input v-model="data.form.confirmPass" style="width:250px" show-password/>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="handleClose()">取 消</el-button>
      <el-button type="danger" @click="handleSavePass()">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script lang="ts" setup>
import Api from '@/api/Management/api_sysaccount.js'
import { reactive, watch, ref, defineProps, toRefs, onMounted} from 'vue'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
import {ElMessage, ElMessageBox} from "element-plus";

const store = useStore();
const router = useRouter()

// Data
const data = reactive({
    id:'',
    dialogVisible: false,
    form: {
        oldPass: '',
        newPass: '',
        confirmPass: '',
        accountName: '',
        accountId: ''
    },
    rules: {
        oldPass: [
            {required: true, message: '请输入旧密码', trigger: 'blur'}
        ],
        newPass: [
            {
                required: true,
                // pattern: /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}/,
                message: '请输入新密码',
                trigger: 'blur'
            }
        ],
        confirmPass: [
            // {validator: validatePass, trigger: 'blur'}
        ]
    }
})

// Methods
const validatePass = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入确认密码'));
    } else if (value !== this.form.newPass) {
        callback(new Error('两次输入密码不一致!'));
    } else {
        callback();
    }
};

const handleClose = () => {
    data.dialogVisible = false;
}

// 表单ref
const form = ref();
const handleSavePass = () => {
    form.value.validate((valid, fields) => {
        if (valid) {
            Api.modifyPassApi(data,form).then(response => {
                console.log(response)
                if (response.code === 200){
                    ElMessage({
                        message: '修改密码成功',
                        type: 'success',
                    })
                }

            });
        } else {
            ElMessage({
                message: '请校验',
                type: 'warning',
            })
        }
    })
}

const init = (id) => {
    data.id = id;
    data.dialogVisible = true;
    Api.sel4sysaccount(id).then(response => {
        console.log(response)
        if (response.code === 200){
            data.form = response.data;
        }

    });
}


defineExpose({
    init,
});
</script>

<style scoped>

</style>
