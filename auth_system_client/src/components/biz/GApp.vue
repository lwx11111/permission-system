<template>
  <el-select v-model="currentValue" clearable placeholder="请选择应用" :size="size" @change="onChange">
    <el-option v-if="allOption" label="全部" value="-1"/>
    <el-option
      v-for="item in result"
      :key="item.appId"
      :label="item.appName"
      :value="item.appId"/>
  </el-select>
</template>

<script>
  import {searchApi} from '@/api/app'

  export default {
    name: 'GApp',
    props: {
      value: {
        type: String,
        default: null
      },
      allOption: {
        type: Boolean,
        default: false
      },
      size: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        result: [],
        currentValue: this.value
      }
    },
    watch: {
      currentValue: function (val) {
        this.$emit('input', val);
      },
      value: function (val) {
        this.currentValue = val;
      }
    },
    mounted() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        searchApi({status: '1'}).then(response => {
          const res = response;
          this.result = res.data;
        })
      },
      onChange(val) {
        this.$emit('change', val);
      }
    }
  }
</script>

<style scoped>

</style>
