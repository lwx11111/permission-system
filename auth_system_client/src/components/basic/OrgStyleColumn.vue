<template>
  <el-tag :type="statusCn.type"><span class="icon iconfont" v-html="statusCn.icon"></span>&nbsp;{{ statusCn.name }}</el-tag>
</template>

<script>
  export default {
    name: 'OrgStyleColumn',
    props: {
      orgStyle: {
        type: String,
        required: true
      }
    },
    computed: {
      statusCn() {
        const statusObj = {
          '1': {
            name: '部门',
            type: 'success',
            icon: '&#xe66e;'
          },
          '2': {
            name: '中心',
            type: 'warning',
            icon: '&#xe6e6;'
          },
          '3': {
            name: '组',
            type: 'danger',
            icon: '&#xe617;'
          }
        }
        return statusObj[this.orgStyle]
      }
    }
  }
</script>

<style scoped>

</style>
