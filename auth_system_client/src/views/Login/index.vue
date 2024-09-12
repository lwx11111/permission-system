<template>
    <div class="back">
        <div class="loginBack">
            <el-card class="login-form-content">
                <h1 style="text-align: center">登录</h1>
                <el-form :model="form"
                         ref="formRef">
                    <el-input v-model="form.username"
                              class="input-item"
                              placeholder="用户名">
                        <template #prefix>
                            <el-icon style="color: gold"><Avatar /></el-icon>
                        </template>
                    </el-input>
                    <el-input v-model="form.password"
                              placeholder="密码"
                              class="input-item"
                              show-password>
                        <template #prefix>
                            <el-icon style="color: gold"><Lock /></el-icon>
                        </template>
                    </el-input>

                    <el-row>
                        <el-col :span="14">
                            <el-input v-model="form.verifyCode"
                                      placeholder="验证码"
                                      class="input-item">
                            </el-input>
                        </el-col>
                        <el-col :span="10">
                            <el-image class="login-code"
                                 alt="验证码"
                                 id="captchaImg"
                                 :src="data.captchaUrl"
                                 @click="setCaptchaUrl"/>
                        </el-col>
                    </el-row>
                    <el-button style="width: 100%; height: 45px;"
                               type="primary"
                               @click="onSubmit()">
                        登录
                    </el-button>
                    <el-text class="input-item" @click="toRegister()" type="primary">去注册</el-text>
                </el-form>
            </el-card>
        </div>
    </div>
</template>

<script lang="ts" setup>
import {getCurrentInstance, onMounted, reactive, ref} from 'vue'
import commonUtil from '../../utils/common-util.js';
import {getEncryptPassword} from '@/utils/passwordEncrypt.js';
import AuthApi from '@/api/auth/auth.js';
import AuthStorage from '@/cache/authStorage.js';
import { useStore } from "vuex";
import { useRouter} from "vue-router";
import {ElMessage} from "element-plus";
import dataJson from "../../../public/config.json";

const store = useStore();
const router = useRouter()
const data = reactive({
    captchaUrl: '',
    uuid: '',
})
const form = reactive({
    username: '',
    password: '',
    verifyCode: '',
})

// Mounted
onMounted(() => {
    setCaptchaUrl();
})

/**
 * 获取验证码
 */
const currentInstance = getCurrentInstance();
const getCaptchaUrl = () => {
    const uuid = commonUtil.createGuid()
    data.uuid = uuid;
    // 使用getCurrentInstanceAPI获取全局对象方法 从globalProperties中可以获取到所有的全局变量
    const globalProperties = currentInstance?.appContext.config.globalProperties
    return globalProperties.GATEWAY_URL + AuthApi.getCaptchaUrl() + uuid;
}

/**
 * 重新获取验证码
 */
const setCaptchaUrl = () => {
    data.captchaUrl = getCaptchaUrl()
    form.verifyCode = ''
}

/**
 * 登录校验
 */
const onSubmit = () => {
    if (form.username === ''){
        ElMessage.error('用户名不能为空');
        return;
    }
    if (form.password === ''){
        ElMessage.error('密码不能为空');
        return;
    }
    if (form.verifyCode === ''){
        ElMessage.error('验证码不能为空');
        return;
    }
    loginWithCode();
}

/**
 * 请求后端登录
 */
const loginWithCode = () => {
    const globalProperties = currentInstance?.appContext.config.globalProperties
    const params = {
        verify: form.verifyCode,
        uuid: data.uuid,
        username: getEncryptPassword(form.username, 'aes'),
        password: getEncryptPassword(form.password, 'aes'),
        appId: dataJson.APP_ID,
    };

    AuthApi.loginWithCode(params).then(res => {
        console.log(res)
        if (res.code === 200){
            let account = res.data.info.account
            AuthStorage.setToken(res.data.token.accessToken);
            // 跳转
            routerPushByType(account.accountId);
        } else {
            ElMessage.error(res.message)
        }
    });
}

/**
 * 根据用户类型跳转
 * @param accountId
 */
const routerPushByType = (accountId) => {
    // 页面跳转
    router.push({
        path: '/homepage',
    })
}

/**
 * 跳转注册页面
 */
const toRegister = () => {
    router.push({
        path: '/register',
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
    min-height: 100vh;
    margin: 0;
    padding: 0;
    /* 不加这个有问题 */
    border: 1px solid white;
}

.login-form-content {
    text-align: center;
    margin-top: 15%;
    margin-left: 25%;
    width: 40%;
    height: 90%;
    /*background-color: #f5f6f7;*/
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
