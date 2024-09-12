/**
 * ajax请求配置
 */
import axios from 'axios'
import {ElMessage} from 'element-plus'
import qs from "qs";
import dataJson from "/public/config.json";
import AuthStorage from '@/cache/authStorage.js';
// axios默认配置
axios.defaults.timeout = 10000 // 超时时间

// 网关地址
axios.defaults.baseURL = dataJson.GATEWAY_URL;
// 整理数据
axios.defaults.transformRequest = function(data) {
    return data
}

// 路由请求拦截
axios.interceptors.request.use(
    config => {
        // appId标识
        config.headers['APPID'] = dataJson.APP_ID;
        config.headers['USER_NAME'] = "admin";
        if (AuthStorage.getToken()) {
            config.headers['Authorization'] = AuthStorage.getToken()
            // config.headers['Authorization'] = 'eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl9hcHBfaWQiOiIxNjk0NTQzNDg1MjE0MTU4ODUwIiwic3ViIjoiSW5zcHVyLUF1dGgtTWFuYWdlciIsImxvZ2luX3VpZCI6IjE2OTQ1NDM0OTA2NjI1NTk3NDYiLCJsb2dpbl9hY2NvdW50X2lkIjoiMTY5NDU0MzQ5MTgxNTk5MzM0NSIsImlzcyI6Ikluc3B1ciIsImxvZ2luX2xvZ2lubmFtZSI6ImFkbWluIiwiY2xpZW50X2lwIjoiMTI3LjAuMC4xIiwibG9naW5fYWNjb3VudF9uYW1lIjoi566h55CG5ZGYIiwidXNlcnNfYXBwX2lkIjoiMTY5NDU0MzQ4NTIxNDE1ODg1MCIsImp0aSI6IjEuMCIsImxvZ2luX3VuYW1lIjoi566h55CG5ZGYIiwiZXhwIjoxNjk1MDkwNDA3LCJuYmYiOjE2OTUwMDQwMDd9.4Rm6iOz3QGnU_SDjMZW-MEb3tetLXsggugE7atGzd3M0jz64e7D9kunUK7vIyPtk37iTRaZuWT6UJERpxoXkNA'
        }
        if (config.type === 'form'){
            // 后端@RequestParams注解接收
            config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
            config.data = qs.stringify(config.data)
        } else if (config.type === 'file') {
            // 后端@RequestParams注解接收
            config.headers['Content-Type'] = 'multipart/form-data'
            config.data = qs.stringify(config.data)
        } else {
            // 后端@RequestBody注解接收
            config.headers['Content-Type'] = 'application/json;charset=UTF-8'
            config.data = JSON.stringify(config.data)
        }

        return config
    },
    error => {
        return Promise.reject(error.response)
    }
)

// 路由响应拦截
axios.interceptors.response.use(
    response => {
        if (response.headers && (response.headers['content-type'] === 'application/x-msdownload' ||
                response.headers['content-type'].indexOf('application/vnd.ms-excel') !== -1 ||
                response.headers['content-type'].indexOf('application/octet-stream') !== -1)) {
            return response;
        } else {
            return response.data
        }
    },
    error => {
        console.log('err' + error) // for debug
        if (error && error.response) {
            const {status} = error.response;
            if (status === 401) {
                ElMessage.error('Token值无效，请重新登录');
                // router.replace('/login');
            } else {

            }
        } else {
            ElMessage.error('网络出现问题，请稍后再试');
        }
        return Promise.reject(error)
    }
)
export default axios
