import router from './router'
// 全局变量
import dataJson from '../public/config.json';
// 权限缓存
import AuthStorage from '@/cache/authStorage.js';
// 权限系统API
import AuthApi from '@/api/auth/auth.js'
// 页面白名单
const whiteList = ['/login','/register']

router.beforeEach(async (to, from, next) => {
    next();
    // 设置页面标题
    document.title = dataJson.APP_NAME;

    const query = to.query
    if (query.hasOwnProperty('token')) {
        AuthStorage.setToken(query.token);
    }

    // 检查用户是否已经登录
    const hasToken = AuthStorage.getToken()
    if (hasToken) {
        if (to.path === '/login') {
            next({path: '/'})
        } else {
            //判断当前用户是否拥有此页面的访问权限
            const path = to.path;
            // next();
            await AuthApi.pagePermApi(path).then(response => {
                if (response.data === true) {
                    next();
                } else {
                    next(`/login?redirect=${to.path}`)
                }
            });
        }
    } else {
        // 白名单
        if (whiteList.indexOf(to.path) !== -1) {
            next()
        } else {
            next(`/login?redirect=${to.path}`)
        }
    }
})

router.afterEach(() => {

})
