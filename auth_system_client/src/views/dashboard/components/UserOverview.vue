<template>
    <el-card style="margin-bottom:20px;">
        <div slot="header" class="clearfix">
            <svg-icon icon-class="peoples"/>&nbsp;<span>本年用户情况概览</span>
            <div :class="className" :id="id" :style="{width:width}"/>
        </div>
    </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref, defineProps, toRefs, onMounted} from 'vue'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
import * as echarts from 'echarts';
// import {userOverviewApi} from '@/api/user'

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
            // itemWidth: 14,
            // itemHeight: 5,
            // itemGap: 13,
            data: ['用户数', '账户数'],
            right: '4%'
            // textStyle: {
            //     fontSize: 12,
            //     color: '#F1F1F3'
            // }
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
            name: '(个)',
            axisTick: {
                show: false
            }
        }],
        series: [{
            name: '用户数',
            type: 'bar',
            smooth: true,
            symbol: 'circle',
            symbolSize: 5,
            showSymbol: false,
            itemStyle: {
                normal: {
                    color: 'rgb(137,189,27)',
                    borderColor: 'rgba(137,189,2,0.27)',
                    borderWidth: 12
                }
            },
            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            }
        }, {
            name: '账户数',
            type: 'bar',
            smooth: true,
            symbol: 'circle',
            symbolSize: 5,
            showSymbol: false,
            itemStyle: {
                normal: {
                    color: 'rgb(0,136,212)',
                    borderColor: 'rgba(0,136,212,0.2)',
                    borderWidth: 12

                }
            },
            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            }
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
    // userOverviewApi().then(response => {
    //     const res = response;
    //     // option.xAxis[0].data = res.data.series;
    //     option.series[0].data = res.data.usrcount;
    //     option.series[1].data = res.data.acccount;
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
    height: calc(100vh /2 - 153px);
  }
</style>
