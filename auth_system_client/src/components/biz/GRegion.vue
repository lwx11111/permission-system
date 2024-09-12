<template>
  <el-select v-model="currentValue" clearable placeholder="请选择" :size="size" @change="onChange">
    <el-option v-if="allOption" label="全部" value=""/>
    <el-option v-if="displayChina" label="中国" value="86"/>
    <el-option
      v-for="item in result"
      :key="item.regionId"
      :label="item.regionName"
      :value="item.regionId"/>
  </el-select>
</template>

<script>
  import {searchApi} from '@/api/region'

  export default {
    name: 'GRegion',
    props: {
      value: {
        type: String,
        default: null
      },
      allOption: {
        type: Boolean,
        default: false
      },
      displayChina: {
        type: Boolean,
        default: false
      },
      size: {
        type: String,
        default: ''
      },
      parentId: {
        type: String,
        default: ''
      },
      regionType: {
        type: String,
        required: true,
        default: ''
      }
    },
    data() {
      return {
        result: [],
        currentValue: this.value,
        tip: ''
      }
    },
    watch: {
      currentValue: function (val) {
        this.$emit('input', val);
      },
      value: function (val) {
        this.currentValue = val;
      },
      parentId() {
        this.fetchData();
      }
    },
    mounted() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        searchApi({
          regionType: this.regionType, parentId: this.parentId
        }).then(response => {
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
