<template>
    <el-container>
        <el-aside width="200px">
            <Sidebar class="sidebar-container" :style="handleStyle()"></Sidebar>
        </el-aside>
        <el-container>
            <el-header>
                <Navbar></Navbar>
            </el-header>
            <el-main>
                <AppMain></AppMain>
            </el-main>
        </el-container>
    </el-container>
</template>

<script lang="ts" setup>
    import Navbar from './Navbar.vue';
    import Sidebar from './Sidebar/index.vue';
    import AppMain from "./AppMain.vue";
    import dataJson from "../../public/config.json";

    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import AuthApi  from '@/api/auth/auth.js'
    import {onMounted, reactive} from "vue";
    const store = useStore();
    const router = useRouter()

    const data = reactive({
        BACKGROUND_COLOR: dataJson.BACKGROUND_COLOR
    })

    // Mounted
    onMounted(() => {

    })

    const handleStyle = () => {
        return { background: data.BACKGROUND_COLOR };
    }

</script>

<style>
.sidebar-container {
    transition: width 0.28s;
    width: 200px;
    height: 100%;
    position: fixed;
    font-size: 0px;
    top: 0;
    bottom: 0;
    left: 0;
    z-index: 1001;
    overflow: hidden;
}
</style>




