// 权限管理API  【菜单、角色、权限】
import request from '@/utils/request.js'
const managerUriPrefix = 'manager/'

export default {
    loginWithCode(data) {
        return request({
            url: managerUriPrefix + 'sysaccount/anon/loginWithCode',
            method: 'post',
            data: data
        })
    },

    register(data) {
        return request({
            url: managerUriPrefix + '/v1/account/anon/save',
            method: 'post',
            type: 'form',
            data: data
        })
    },

    modifyPass(data) {
        return request({
            url: managerUriPrefix + '/v1/account/modify-password',
            method: 'post',
            type: 'form',
            data: data
        })
    },

    logout() {
        return request({
            url: managerUriPrefix + 'v1/account/logout',
            method: 'post'
        })
    },

    pagePermApi(url) {
        return request({
            url: managerUriPrefix + 'v1/function/page-perm',
            method: 'post',
            data: url
        })
    },

    leftMenuApi(data) {
        return request({
            url: managerUriPrefix + 'sysfunction/left-menu',
            method: 'post',
            data: data
        })
    },

    getCaptchaUrl(){
        return "/" + managerUriPrefix + "verificationCode/anon/create?uuid=";
    }
}





