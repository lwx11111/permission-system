import Frame from "@/frame/index.vue";

const Logs = () => import('@/views/Monitor/OperationLogs/Index.vue')

const PasswordLogs =  () => import('@/views/Monitor/PasswordLogs/Index.vue')

const LockLogs =  () => import('@/views/Monitor/LockLogs/Index.vue')

const LoginLogs =  () => import('@/views/Monitor/LoginLogs/Index.vue')

const monitorRouter =
{
    path: '/monitor',
    component: Frame,
    // redirect: '/homePage',
    name: '日志管理',
    children: [
        {
            path: '/monitor/logs/index',
            component: Logs,
            name: '操作日志'
        },
        {
            path: '/monitor/passwordLogs/index',
            component: PasswordLogs,
            name: '密码日志'
        },
        {
            path: '/monitor/lockLogs/index',
            component: LockLogs,
            name: '锁定日志'
        },
        {
            path: '/monitor/loginLogs/index',
            component: LoginLogs,
            name: '登录日志'
        },
    ]
}

export default monitorRouter;
