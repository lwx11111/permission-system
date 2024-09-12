<template>
    <el-card style="margin-bottom:20px;">
        <div slot="header" class="clearfix">
            <svg-icon icon-class="international"/>&nbsp;<span>本年登录情况概览</span>
            <div :class="className" :id="id" :style="{width:width}"/>
        </div>
    </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref, defineProps, toRefs, onMounted} from 'vue'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
import * as echarts from 'echarts';
// import {loginOverviewApi} from '@/api/log-platform-oper'

const store = useStore();
const router = useRouter()

// Data
const data = reactive({
    chart: null,
    option:{
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: '#57617B'
                }
            }
        },
        legend: {
            top: 5,
            icon: 'rect',
            itemWidth: 14,
            itemHeight: 5,
            itemGap: 13,
            data: ['登录次数'],
            right: '4%'
        },
        grid: {
            top: 50,
            left: '1%',
            right: '1%',
            bottom: '1%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            axisLine: {
                lineStyle: {
                    color: '#57617B'
                }
            },
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        }],
        yAxis: [{
            type: 'value',
            name: '(次数)',
            axisTick: {
                show: false
            }
        }],
        series: [{
            name: '登录次数',
            type: 'line',
            smooth: true,
            symbol: 'circle',
            symbolSize: 5,
            showSymbol: false,
            lineStyle: {
                normal: {
                    width: 1
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(137, 189, 27, 0.3)'
                    }, {
                        offset: 0.8,
                        color: 'rgba(137, 189, 27, 0)'
                    }], false),
                    shadowColor: 'rgba(0, 0, 0, 0.1)',
                    shadowBlur: 10
                }
            },
            itemStyle: {
                normal: {
                    color: 'rgb(137,189,27)',
                    borderColor: 'rgba(137,189,2,0.27)',
                    borderWidth: 12
                }
            },
            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        }]
    }
})

// Props
const props = defineProps({
    className: {
        type: String,
        default: 'chart'
    },
    id: {
        type: String,
        default: 'chart'
    },
    width: {
        type: String,
        default: '100%'
    },
    height: {
        type: String,
        default: '220px'
    }
})

// Mounted
onMounted(() => {
    // loginOverviewApi().then(response => {
    //     const res = response;
    //     // option.xAxis[0].data = res.data.series;
    //     option.series[0].data = res.data;
    //     this.chart.setOption(option);
    // });
    // 基于准备好的dom，初始化echarts实例,多个组件 id必须区分
    var myChart = echarts.init(document.getElementById(props.id));
    // 绘制图表
    myChart.setOption(data.option);
})

</script>

<style lang="scss" scoped>
  .chart {
    height: calc(100vh / 2 - 153px);
  }
</style>
