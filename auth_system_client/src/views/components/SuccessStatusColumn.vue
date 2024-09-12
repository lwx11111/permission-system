<template>
  <el-tag :type="data.type">
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
    isSuccess:{
        type: String,
        required: true
    },
})

// Mounted
onMounted(() => {
    if (props.isSuccess === '0'){
        data.type = "success"
        data.name = '成功'
    } else {
        data.type = "danger"
        data.name = '失败'
    }
})
</script>

