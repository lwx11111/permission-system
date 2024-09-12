<template>
    <div>
        <el-card style="margin: 10px; border: 1px solid gold">
            <!-- 查询条件 -->
            <el-collapse
                    accordion
                    v-model="data.activeName"
                    class="card-bg">
                <el-collapse-item name="1">
                    <template #title>
                        <div class="innerHeader">
                          登录日志管理
                        </div>
                    </template>
                    <div style="display: flex;"
                         class="card-search">
                        <el-form :inline="true"
                                 :model="data.formList"
                                 size="default"
                                 label-width="100px">
                            <el-form-item label="创建时间">
                                <el-date-picker
                                    v-model="data.formList.createTime"
                                    type="datetime"
                                    format="YYYY/MM/DD hh:mm:ss"
                                    value-format="YYYY-MM-DD hh:mm:ss"
                                    style="width: 200px"
                                    @keyup.enter.native="getData">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="结束时间">
                                <el-date-picker
                                    v-model="data.formList.endTime"
                                    type="datetime"
                                    format="YYYY/MM/DD hh:mm:ss"
                                    value-format="YYYY-MM-DD hh:mm:ss"
                                    style="width: 200px"
                                    @keyup.enter.native="getData">
                                </el-date-picker>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-collapse-item>
            </el-collapse>
        </el-card>
        <el-card style="margin: 10px; border: 1px solid gold">
            <!-- 操作按钮区 -->
            <div style="margin:10px 0;">
                <div style="float:right;">
                    <el-button
                          type="primary"
                          @click="queryData()"
                          icon="Search"
                          :loading="data.isSearch">
                    查询
                    </el-button>
                    <el-button
                          @click="resetData()"
                          icon="Close">
                    清空
                    </el-button>
                </div>
            </div>

            <!-- 表格呈现 -->
            <el-table
                  :data="data.tableData"
                  :height="data.screenHeight - data.otherHeight"
                  tooltip-effect="dark"
                  style="width:100%"
                  stripe
                  size="default"
                  border>
                 <el-table-column
                        prop="accountName"
                        label="账号"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="userName"
                        label="用户名"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="serverIp"
                        label="服务端IP"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="clientIp"
                        label="客户端IP"
                        width="180"
                        align="center">
                </el-table-column>
                <el-table-column
                    prop="time"
                    label="执行时间（毫秒）"
                    width="180"
                    align="center">
                </el-table-column>
                <el-table-column
                        prop="operation"
                        label="操作内容"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="createTime"
                        label="创建时间"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="successFlag"
                        label="是否成功"
                        width="180"
                        align="center">
                    <template #default="scope">
                        <SuccessStatusColumn :isSuccess="scope.row.successFlag"></SuccessStatusColumn>
                    </template>
                </el-table-column>
              </el-table>

            <el-pagination
                      v-model:current-page="data.pageConfig.currentPage"
                      v-model:page-size="data.pageConfig.pageSize"
                      class="customPage mt-10"
                      background
                      :page-sizes="[10, 20, 50]"
                      :current-page="data.pageConfig.currentPage"
                      :page-size="data.pageConfig.pageSize"
                      :total="data.pageConfig.total"
                      @size-change="handleSizeChange"
                      @current-change="handleCurrentChange"
                      layout="sizes,total, ->, prev, pager, next">
              </el-pagination>
        </el-card>
    </div>
</template>
<script lang="ts" setup>
    import Api from '@/api/Monitor/api_logplatformoper.js'
    import SuccessStatusColumn from "@/views/components/SuccessStatusColumn.vue";
    import { reactive, onMounted} from 'vue'
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import {ElMessage} from "element-plus";

    const store = useStore();
    const router = useRouter()

    // Data
    const data = reactive({
        screenHeight: window.innerHeight,// screenHeight:控制高度自适应-页面高度
        otherHeight: 310,// otherHeight:控制高度自适应-表格外的高度
        isSearch: false, // isSearch:控制搜索状态
        // formList:搜索条件对象 分页控制对象
        formList: {
            accountId: '',
            userId: '',
            type: '',
            sensitiveLevel: '',
            operation: '',
            time: '',
            method: '',
            params: '',
            serverIp: '',
            clientIp: '',
            createTime: '',
            location: '',
            successFlag: '',
            endTime: ''
        },
        // tableData:表格数据
        tableData: [],
        activeName: '1',
        // 分页配置
        pageConfig: {
            currentPage: 1,
            pageSize: 10,
            total: 1000
        },
    })

    // Mounted
    onMounted(() => {
        getData();
    })

    // Methods
    const getData = () => {
        if (data.formList.endTime !== ''){
            if (data.formList.createTime > data.formList.endTime){
                ElMessage({
                    message: '开始时间不能晚于结束时间',
                    type: 'warning',
                })
                return;
            }
        }

        // 查询方法
        // 查询参数
        const params = {
            type : "6",
            createTime : data.formList.createTime,
            endTime : data.formList.endTime,
            pageIndex : data.pageConfig.currentPage,
            pageSize : data.pageConfig.pageSize
        }
        // 后台请求
        Api.selpage4logplatformoper(params).then(res => {
            console.log(res)
            if (res.code === 200){
                data.tableData = res.data.records
                data.pageConfig.total = res.data.total
                data.isSearch = false
            }
        })
    }

    const queryData = () => {
        data.isSearch = true
        data.pageConfig.currentPage = 1
        getData()
    }

    const resetData = () => {
        // 重置事件
        data.isSearch = false
        for (const key in data.formList) {
            data.formList[key] = ''
        }
    }

    const handleCurrentChange = (val: number) => {
        // 页码切换事件
        data.pageConfig.currentPage = val
        getData()
    }

    const handleSizeChange = (val: number) => {
        // 页面条数变化事件
        data.pageConfig.pageSize = val
        getData()
    }

    const activated = () => {
        getData()
    }

</script>
<style lang="css" scoped>
/* 单页面样式 */
>>>.el-table .cell {
  white-space: nowrap
}
</style>
