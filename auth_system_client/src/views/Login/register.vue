<template>
    <div class="back">
        <div class="loginBack">
            <el-card class="login-form-content">
                <h1 style="text-align: center">注册</h1>
                <el-form :model="data.form"
                         ref="formRef"
                         :rules="data.rules"
                         label-width="100px">
                    <el-input v-model="data.form.username"
                              class="input-item"
                              placeholder="用户名">
                        <template #prefix>
                            <el-icon style="color: gold"><Avatar /></el-icon>
                        </template>
                    </el-input>

                    <el-input v-model="data.form.password"
                              class="input-item flex align-center"
                              show-password
                              placeholder="密码">
                        <template #prefix>
                            <el-icon style="color: gold"><Lock /></el-icon>
                        </template>
                    </el-input>
                    <el-input v-model="data.form.confirmPassword"
                              class="input-item flex align-center"
                              show-password
                              placeholder="确认密码">
                        <template #prefix>
                            <el-icon style="color: gold"><Lock /></el-icon>
                        </template>
                    </el-input>
                    <el-input v-model="data.form.phone"
                              class="input-item flex align-center"
                              placeholder="手机号">
                        <template #prefix>
                            <el-icon style="color: gold"><Iphone /></el-icon>
                        </template>
                    </el-input>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item
                                label="头像"
                                prop="picture">
                                <MinioUpload @getUrl="getUrl"></MinioUpload>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="14">
                            <el-input v-model="data.form.verifyCode"
                                      placeholder="验证码"
                                      class="input-item">
                            </el-input>
                        </el-col>
                        <el-col :span="6">
                            <el-image class="login-code"
                                      alt="验证码"
                                      id="captchaImg"
                                      :src="data.captchaUrl"
                                      @click="setCaptchUrl"/>
                        </el-col>
                    </el-row>
                    <el-button style="width: 100%; height: 45px;"
                               type="primary"
                               @click="submitForm()">
                        注册
                    </el-button>
                </el-form>
            </el-card>
        </div>
    </div>
</template>

<script lang="ts" setup>
import commonUtil from '@/utils/common-util.js';
import {reactive, onMounted, ref, getCurrentInstance} from 'vue';
import AuthApi from '@/api/auth/auth.js';
import { getEncryptPassword } from '@/utils/passwordEncrypt.js';
import { useStore } from "vuex";
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";
import MinioUpload from "../components/MinioUpload.vue";
const store = useStore();
const router = useRouter()

// Data
const data = reactive({
    captchaUrl: '',
    uuid: '',
    form : {
        username: '',
        password: '',
        confirmPassword: '',
        phone: '',
        verifyCode: '',
        appId: '1',
        type:'',
        avatar:'',
    },
    rules: {
        type: [
            {required: true, message: '用户类型不能为空', trigger: 'blur'}
        ],
    }
})


// Mounted
onMounted(() => {
    setCaptchUrl();
})

const getUrl = (url) =>{
    data.form.avatar = url;
}
/**
 * 获取验证码
 */
const currentInstance = getCurrentInstance();
const getCaptchaUrl = () => {
    const uuid = commonUtil.createGuid()
    data.uuid = uuid;
    // 使用getCurrentInstanceAPI获取全局对象方法一
    // 从globalProperties中可以获取到所有的全局变量
    const globalProperties = currentInstance?.appContext.config.globalProperties
    return globalProperties.GATEWAY_URL + "/" + globalProperties.AUTH_NAME + "/" + globalProperties.CAPTCHA_URL + uuid;
}

/**
 * 重新获取验证码
 */
const setCaptchUrl = () => {
    data.captchaUrl = getCaptchaUrl()
    data.form.verifyCode = ''
}

const validateForm = () => {
    if (data.form.username === ''){
        ElMessage.error('用户名不能为空');
        return false;
    }
    if (data.form.password === ''){
        ElMessage.error('密码不能为空');
        return false;
    }
    var pattern = /^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\d)[a-zA-Z\d!#@*&.]{8,}$/;
    if (!pattern.test(data.form.password)){
        ElMessage.error('密码必须8位以上，必须包含大小写字母和数字');
        return false;
    }
    if (data.form.confirmPassword === ''){
        ElMessage.error('确认密码不能为空');
        return false;
    }
    if (data.form.phone === ''){
        ElMessage.error('电话号不能为空');
        return false;
    }
    if (data.form.verifyCode === ''){
        ElMessage.error('验证码不能为空');
        return false;
    }
    if (data.form.password !== data.form.confirmPassword) {
        ElMessage.error('两次密码不一致');
        return false;
    }
    if (data.form.phone.length !== 11){
        ElMessage.error('电话不满足11位');
        return false;
    }
    return true;
}

// 提交表单的方法
const formRef = ref();
const submitForm = () => {
    // 表单验证
    formRef.value.validate(valid => {
        if (valid) {
            if (validateForm()){

                const data1 = {
                    verify: data.form.verifyCode,
                    uuid: data.uuid,
                    username: data.form.username,
                    password: getEncryptPassword(data.form.password, 'md5'),
                    appId: data.form.appId,
                    phone: data.form.phone,
                    avatar: data.form.avatar
                }
                AuthApi.register(data1).then(res => {
                    console.log(res);
                    if (res.code === '20000'){
                        ElMessage.success('注册成功');
                        // 页面跳转
                        router.push({
                            path: '/login',
                        })
                    } else {
                        ElMessage.error('注册失败');
                    }
                })
            }
        }
    })
}
</script>

<style scoped>
@media screen and (max-width: 1500px){
    /* 当屏幕小于1500px的时候 id为bg的元素 进行改变 */
    .back{
        background-size: contain;
        margin: 0;
        padding: 0;
    }

    .loginBack{

    }
}

.loginBack{
    width:1000px;
    height:500px;
    MARGIN-RIGHT: auto;
    MARGIN-LEFT: auto;
}

.back {
    background-image: url('/src/assets/backgroud.jpg');
    background-size: cover;
    /* 背景图片不重复 */
    background-repeat: no-repeat;
    /* 最小宽度为100% */
    min-width: 100%;
    /* 最小高度为100vh    vh: 就等于 视窗的高度  1vh = 视窗的高度的1%*/
    min-height: 140vh;
    margin: 0;
    padding: 0;
    border: 1px white solid;
}

.login-form-content {
    text-align: center;
    margin-top: 2%;
    margin-left: 17%;
    width: 70%;
    height: 170%;
    .input-item {
        margin: 20px auto;
        height: 45px;
        border-radius: 20px;
    }

    .login-code {
        margin: 20px auto;
        height: 45px;
        float: right;
    }
}
</style>
