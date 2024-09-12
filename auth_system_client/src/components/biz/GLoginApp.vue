<template>
  <el-select v-model="currentValue" value-key="appId" clearable placeholder="请选择应用" :size="size" @change="onChange">
    <span slot="prefix" class="el-input__icon icon iconfont">&#xe600;</span>
    <el-option v-if="allOption" label="全部" :value="-1"/>
    <el-option
      v-for="item in result"
      :key="item.appId"
      :label="item.appName"
      :value="item"/>
  </el-select>
</template>

<script>
  import {appsApi} from '@/api/login'

  export default {
    name: 'GLoginApp',
    props: {
      value: {
        type: Object,
        default() {
          return {}
        }
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
        appsApi().then(response => {
          for (let i = 0; i < response.data.length; i++) {
            response.data[i].appId = response.data[i].app_id;
            response.data[i].appName = response.data[i].app_name;
          }
          console.log(response);
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
