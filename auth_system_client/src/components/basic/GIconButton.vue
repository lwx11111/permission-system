<template>
  <el-tooltip
    :content="tip"
    placement="top">
    <el-button :icon="icon" class="operation" :disabled="disabled" @click="handleClick"/>
  </el-tooltip>
</template>

<script>
  export default {
    name: 'GIconButton',
    props: {
      disabled: {
        type: Boolean,
        default: false
      },
      icon: {
        type: String,
        required: true
      },
      tip: {
        type: String,
        default: ''
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
  .operation {
    border: 0px;
    padding: 6px;
    cursor: pointer;
    background-color: transparent;

    &:hover {
      background-color: #bdc3c7;
    }
  }
</style>
