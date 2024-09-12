<template>
  <el-tag :type="data.type">
      <el-icon v-if="this.status === '0'"><CircleCheck /></el-icon>
      <el-icon v-if="this.status === '1'"><Lock /></el-icon>
      <el-icon v-if="this.status === '2'"><Warning /></el-icon>
      {{ data.name }}
  </el-tag>
</template>

<script lang="ts" setup>

import { reactive, ref, onMounted } from 'vue'


// Data
const data = reactive({
    type: "",
    name:''
})

// Props
const props = defineProps({
    status:{
        type: String,
        required: true
    },
})

// Mounted
onMounted(() => {
    if (props.status === '0'){
        data.type = "warning"
        data.name = '隐藏'
    } else {
        data.type = "success"
        data.name = '显示'
    }
})
</script>

<style scoped>

</style>
