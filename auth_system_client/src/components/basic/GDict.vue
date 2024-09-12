<template>
  <el-select v-model="currentValue" placeholder="请选择" :size="size" @change="onChange" :disabled="disabled">
    <el-option v-if="allOption" label="全部" :value="-1" />
    <el-option
      v-for="item in result"
      :key="item.dictVal"
      :label="item.dictName"
      :value="item.dictVal"/>
  </el-select>
</template>

<script>
  import {getDictsApi} from '@/api/dict'

  export default {
    name: 'GDict',
    props: {
      value: {
        type: String,
        default: ''
      },
      allOption: {
        type: Boolean,
        default: true
      },
      dictCode: {
        type: String,
        required: true
      },
      size: {
        type: String,
        default: ''
      },
      disabled: {
        type: Boolean,
        default: false
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
        getDictsApi(this.dictCode).then(response => {
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
