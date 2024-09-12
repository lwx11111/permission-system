<template>
    <div>
        <!-- 有二级分类的一级分类 -->
        <el-sub-menu
            class="myMenu"
            v-if="item.children && item.children.length > 0"
            :key="item.funId"
            :index="item.funId.toString()"
            teleported>
            <template #title>
                <el-icon><HelpFilled /></el-icon>
                <span>{{item.funName}}</span>
            </template>
            <!--二级分类-->
            <router-link v-for="(citem,cindex) in item.children"
                         :key="cindex" :to="resolvePath(citem.url)">
                <el-menu-item :index="citem.url">
                    <el-icon><Flag /></el-icon>
                    {{ citem.funName }}
                </el-menu-item>
            </router-link>
        </el-sub-menu>
        <!--只有一级分类-->
        <router-link class="myMenu"
                     v-else-if="item.children == null || item.children.length == 0"
                    :key="item.funId.toString()"
                    :to="resolvePath(item.url)"
                    :index="item.funId.toString()">
            <el-menu-item :index="item.funId.toString()">
                <template #title>
                    <el-icon><HelpFilled /></el-icon>
                    <span>{{item.funName}}</span>
                </template>
            </el-menu-item>
        </router-link>
    </div>
</template>

<script lang="js" setup>
import {onMounted, reactive} from "vue";
import {
    HelpFilled,
    Flag,
} from '@element-plus/icons-vue'


const data = reactive({

})

// Props
const props = defineProps({
    item: {
        type: Object,
        required: true
    },
    isNest: {
        type: Boolean,
        default: false
    },
    basePath: {
        type: String,
        default: ''
    }
})

// Mounted
onMounted(() => {

})

// Methods
const resolvePath = (routePath) => {
    return routePath;
}
</script>
<style scoped lang="scss">
    a {
        text-decoration: none;
    }

    .router-link-active {
    text-decoration: none;
    }

    .fa-margin {
    margin-right: 10px;
    }

    .myMenu v-deep .el-menu-item {
    white-space: pre;
    height: 50px;
    line-height: 50px;
    padding: 0 10px;
    }

    .myMenu v-deep .el-submenu__title {
    height: 50px;
    line-height: 50px;
    padding: 0 10px;
    }
</style>
