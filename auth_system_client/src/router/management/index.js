import Frame from "@/frame/index.vue";

const SysUserIndex = () => import('@/views/Management/SysUser/Index.vue')
const SysAccountIndex = () => import('@/views/Management/SysAccount/Index.vue')
const SysRoleIndex = () => import('@/views/Management/SysRole/Index.vue')
const SysFunctionIndex = () => import('@/views/Management/SysFunction/Index.vue')
const SysCredentialRuleIndex = () => import('@/views/Management/SysCredentialRule/Index.vue')
const SysCompanyIndex = () => import('@/views/Management/SysCompany/Index.vue')
const SysOrgIndex = () => import('@/views/Management/SysOrg/Index.vue')

const SysGroupIndex = () => import('@/views/Management/SysGroup/Index.vue')

const SysDataSourceIndex = () => import('@/views/Management/SysDatasource/Index.vue')

const SysDatasetIndex = () => import('@/views/Management/SysDataset/Index.vue')

const SysNoticeIndex = () => import('@/views/Management/SysNotice/Index.vue')

const SysSettingIndex = () => import('@/views/Management/SysSetting/Index.vue')

const SysDictIndex = () => import('@/views/Management/SysDict/Index.vue')
const SysAppIndex = () => import('@/views/Management/SysApp/Index.vue')

const manageRouter =
{
    path: '/management',
    component: Frame,
    name:'权限管理',
    children: [
        {
            path: '/management/user/index',
            component: SysUserIndex,
            name: '用户管理'
        },
        {
            path: '/management/account/index',
            component: SysAccountIndex,
            name: '账号管理'
        },
        {
            path: '/management/role/index',
            component: SysRoleIndex,
            name: '角色管理'
        },
        {
            path: '/management/function/index',
            component: SysFunctionIndex,
            name: '功能管理'
        },
        {
            path: '/management/credentialRule/index',
            component: SysCredentialRuleIndex,
            name: '密码规则'
        },
        {
            path: '/management/company/index',
            component: SysCompanyIndex,
            name: '公司管理'
        },
        {
            path: '/management/org/index',
            component: SysOrgIndex,
            name: '部门管理'
        },
        {
            path: '/management/group/index',
            component: SysGroupIndex,
            name: '组管理'
        },
        {
            path: '/management/dataSource/index',
            component: SysDataSourceIndex,
            name: '数据源管理'
        },

        {
            path: '/management/sysDataset/index',
            component: SysDatasetIndex,
            name: '数据集管理'
        },

        {
            path: '/management/sysNotice/index',
            component: SysNoticeIndex,
            name: '公告管理'
        },

        {
            path: '/management/sysSetting/index',
            component: SysSettingIndex,
            name: '系统配置'
        },

        {
            path: '/management/sysDict/index',
            component: SysDictIndex,
            name: '字典配置'
        },
        {
            path: '/management/sysApp/index',
            component: SysAppIndex,
            name: '应用管理'
        },
    ]
}

export default manageRouter;
