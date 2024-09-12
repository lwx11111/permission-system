<template>
  <el-table
    v-loading="listLoading"
    :data="data"
    :row-key="rowKey"
    :tree-props="treeProps"
    stripe
    fit
    highlight-current-row
    style="width: 100%;margin-top: 20px;"
    class="normalTable-manage"
    @selection-change="handleSelectionChange">
    <slot></slot>
    <div slot="empty">
      <table-no-data/>
    </div>
  </el-table>
</template>

<script>
  import TableNoData from './TableNoData'

  export default {
    name: 'GTable',
    props: {
      listLoading: {
        type: Boolean,
        required: true
      },
      data: {
        type: Array,
        required: true
      },
      rowKey: {
        type: String,
        default: undefined
      },
      treeProps: {
        type: Object,
        default: function () {
          return {}
        }
      }
    },
    components: {TableNoData},
    methods: {
      handleSelectionChange(val) {
        this.$emit('selection-change', val)
      }
    }
  }
</script>

<style scoped>

</style>
