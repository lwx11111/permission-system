<template>
  <el-tooltip
    content="删除"
    placement="top">
    <el-button icon="el-icon-delete" class="operation-modify" :disabled="disabled" @click="handleClick"/>
  </el-tooltip>
</template>

<script>
  export default {
    name: 'GButtonDelete',
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
