<template>
  <el-select v-model="currentValue" clearable placeholder="请选择" :size="size" @change="onChange">
    <el-option v-if="allOption" label="全部" value="-1"/>
    <el-option
      v-for="item in result"
      :key="item.companyId"
      :label="item.companyName"
      :value="item.companyId"/>
  </el-select>
</template>

<script>
  import { loginInfoApi } from '@/api/account'
  import { searchApi } from '@/api/company'

  export default {
    name: 'GCompany',
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
      },
      companyId:{
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
    created(){
      this.result=[]
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
        searchApi(this.companyId).then(response => {
          this.result = response.data
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
