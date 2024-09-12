import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from "./router/index";
import axios from 'axios'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import './permission'
// 全局变量
import dataJson from "../public/config.json";

const app = createApp(App);
app.config.globalProperties.$axios = axios;
app.use(ElementPlus).use(router).mount('#app');

// 全局注册element-plus的图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// 全局变量配置
app.config.globalProperties = {
    APP_NAME: dataJson.APP_NAME,
    GATEWAY_URL: dataJson.GATEWAY_URL,
};

