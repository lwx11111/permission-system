<template>
  <el-tag :type="statusCn.type"><i :class="statusCn.icon"></i>&nbsp;{{ statusCn.name }}</el-tag>
</template>

<script>
  export default {
    name: 'UserStatusColumn',
    props: {
      status: {
        type: String,
        required: true
      }
    },
    computed: {
      statusCn() {
        const statusObj = {
          '0': {
            name: '正常状态',
            type: 'success',
            icon: 'el-icon-success'
          },
          '1': {
            name: '锁定状态',
            type: 'warning',
            icon: 'el-icon-lock'
          },
          '2': {
            name: '删除状态',
            type: 'danger',
            icon: 'el-icon-error'
          }
        }
        return statusObj[this.status]
      }
    }
  }
</script>

<style scoped>

</style>
