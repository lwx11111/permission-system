<template>
    <div>
        <logo></logo>
        <el-scrollbar wrap-class="scrollbar-wrapper">
            <el-menu
                default-active="1"
                :collapse="data.isCollapse"
                :unique-opened="true"
                :default-openeds="data.openeds"
                :collapse-transition="false"
                mode="vertical"
                active-text-color="#ffd04b"
                :background-color=data.BACKGROUND_COLOR
                text-color="#111111"
            >
            <sidebar-item v-for="route in data.indexDate" :key="route.funId.toString()" :item="route" :base-path="route.url"/>
            <sidebar-item v-for="route in data.menuData"
                          :key="route.funId.toString()"
                          :item="route"
                          :base-path="route.url"/>
            </el-menu>
            <img alt="name" src="../../assets/e-car.png" class="sidebar-foot">
        </el-scrollbar>

    </div>
</template>

<script lang="js" setup>
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import Logo from './Logo.vue'
    import SidebarItem from './SidebarItem.vue'
    import AuthApi  from '@/api/auth/auth.js'
    import dataJson from "../../../public/config.json";
    import {onMounted, reactive} from "vue";
    const store = useStore();
    const router = useRouter()

    const data = reactive({
        BACKGROUND_COLOR: dataJson.BACKGROUND_COLOR,
        showLogo: true,
        isCollapse: false,
        openeds: [],
        menuData: [],
        indexDate: [
            {
                'funId': '1',
                'funName': '首页',
                'url': '/homePage',
            },
            {
                'funId': '1',
                'funName': '测试',
                'url': '/example/index',
            }
        ]
    })

    // Mounted
    onMounted(() => {
        const params = {
            accountId: '1',
            appId: '1',
        }
        /**
         * 获取左侧菜单
         */
        AuthApi.leftMenuApi(params).then(response => {
            // 加载权限系统的
            for (let i = 0;i < response.data.length; i++){
                console.log(response.data[i].funName)
                if (response.data[i].funName === '权限系统监控' || response.data[i].funName === '系统管理'
                    || response.data[i].funName === '权限管理'){
                    data.menuData.push(response.data[i])
                }
            }
        });
    })
</script>

<style>
.sidebar-foot{
    margin-left: 20%;
    width: 50%;
    height: 50%;
}
</style>
