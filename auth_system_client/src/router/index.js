import {
  createRouter,
  createWebHistory,
  createWebHashHistory,
} from 'vue-router'

import Frame from "@/frame/index.vue";
import HomePage from "@/views/homePage.vue";
import managementRouter from "@/router/management/index.js";
import monitorRouter from "@/router/Monitor/index.js";
const Register = () => import('@/views/Login/register.vue')
const Login = () => import('@/views/Login/index.vue')
const Dashboard = () => import('../views/dashboard/index.vue')

// 模块化路由
const routes = [
    // 路由守卫 vue3动态路由问题导致刷新完页面会爆出No match found for location with path
    {
        path: "/:pathMatch(.*)*", // 必备
        component: () => import("@/views/404.vue"),
    },
    {
        path: '/login',
        component: Login,
        name: '登录'
    },
    {
        path: '/register',
        component: Register,
        name: '注册'
    },
    {
        path: '/',
        component: Frame,
        redirect: '/login',
        name: '首页',
        children: [
            {
                path: 'homePage',
                component: HomePage,
                name: '欢迎页',
            },

            {
                path: '/dashboard',
                component: Dashboard,
                name: 'Dashboard'
            },
        ]
    },
    managementRouter,
    monitorRouter
]

// 创建路由对象
const router = createRouter({
  // history: createWebHashHistory(), // hash模式：createWebHashHistory，有#
  history: createWebHistory(),  // history模式：createWebHistory
  routes
})

export default router

