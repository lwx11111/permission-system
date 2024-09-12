<template>
    <div class="sidebar-logo-container">
        <router-link class="sidebar-logo-link" to="/">
            <img alt="name" src="../../assets/icon-logo-small.png" class="sidebar-logo">
            <div style="float: left">
                <h1 class="sidebar-title"> {{ data.appName}} </h1>
                <div class="sub-title">{{ data.appName }}</div>
            </div>
        </router-link>
    </div>
</template>

<script lang="js" setup>
import { useStore } from 'vuex'
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter()

const data = reactive({
    appName: '',
})


// Mounted
const currentInstance = getCurrentInstance();
onMounted(() => {
    // 使用getCurrentInstanceAPI获取全局对象方法一 从globalProperties中可以获取到所有的全局变量
    const globalProperties = currentInstance?.appContext.config.globalProperties
    data.appName = globalProperties.APP_NAME;
})
</script>

<style lang="scss" scoped>

  .sidebarLogoFade-enter-active {
    transition: opacity 1.5s;
  }

  .sidebarLogoFade-enter,
  .sidebarLogoFade-leave-to {
    opacity: 0;
  }

  .sidebar-logo-container {
    position: relative;
    width: 100%;
    height: 50px;
    /*line-height: 50px;*/
    //background: $menuBg;
    /*text-align: center;*/
    overflow: hidden;
    margin-left: 10px;
    margin-top: 10px;

    & .sidebar-logo-link {
      height: 100%;
      width: 100%;

      & .sidebar-logo {
        width: 32px;
        height: 40px;
        vertical-align: bottom;
        float: left;
      }

      & .sidebar-title {
        display: inline-block;
        margin: 0px 0px 0px 10px;
        color: #ffd04b;
        font-weight: 600;
        line-height: 28px;
        font-size: 16px;
        font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
        vertical-align: middle;
      }

      & .sub-title {
        color: #ffd04b;
        font-size: 13px;
        text-align: center;
        height: 18px;
        line-height: 18px;
        font-style: italic;
        padding-left: 17px;
      }
    }

    &.collapse {
      .sidebar-logo {
        margin-right: 0px;
      }
    }
  }
</style>
