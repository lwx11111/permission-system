<template>
  <el-tooltip
    :content="tip"
    placement="top">
    <el-button :icon="icon" class="operation-modify" :disabled="disabled" @click="handleClick"/>
  </el-tooltip>
</template>

<script>
  export default {
    name: 'GButtonLock',
    props: {
      disabled: {
        type: Boolean,
        default: false
      },
      status: {
        type: String,
        required: true
      }
    },
    data() {
      return {}
    },
    computed: {
      icon() {
        return this.status === '0' ? 'el-icon-lock' : 'el-icon-unlock';
      },
      tip() {
        return this.status === '0' ? '锁定' : '解锁';
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
