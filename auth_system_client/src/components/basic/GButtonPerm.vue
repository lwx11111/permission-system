<template>
  <el-tooltip
    content="设置权限"
    placement="top">
    <el-button icon="el-icon-setting" class="operation-modify" :disabled="disabled" @click="handleClick"/>
  </el-tooltip>
</template>

<script>
  export default {
    name: 'GButtonPerm',
    props: {
      disabled: {
        type: Boolean,
        default: false
      }
    },
    methods: {
      handleClick() {
        this.$emit('click')
      }
    }
  }
</script>

<style lang="scss" scoped>
  .operation-modify {
    border: 0px;
    padding: 6px;
    cursor: pointer;
    background-color: transparent;

    &:hover {
      background-color: #bdc3c7;
    }
  }
</style>
