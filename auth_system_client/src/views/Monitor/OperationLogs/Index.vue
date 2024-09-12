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
                          操作日志管理
                        </div>
                    </template>
                    <div style="display: flex;"
                         class="card-search">
                        <el-form :inline="true"
                                 :model="data.formList"
                                 size="default"
                                 label-width="100px">
                            <el-form-item label="类型">
<!--                                1增加 2删除 3修改 4查询 5其他 6登录-->
                                <el-select  placeholder=""
                                            v-model="data.formList.type"
                                            style="width: 200px"
                                            @keyup.enter.native="getData">
                                    <el-option label="增加" value="1" />
                                    <el-option label="删除" value="2" />
                                    <el-option label="修改" value="3" />
                                    <el-option label="查询" value="4" />
                                    <el-option label="其他" value="5" />
                                    <el-option label="登录" value="6" />
                                </el-select>
                            </el-form-item>
                            <el-form-item label="操作内容">
                                <el-input placeholder="请输入内容"
                                          v-model="data.formList.operation"
                                          style="width: 200px"
                                          @keyup.enter.native="getData">
                                </el-input>
                            </el-form-item>
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
                    <el-button
                        type="primary"
                        icon="Upload"
                        @click="uploadExcel()">
                        导入
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
                  border
                  @selection-change="selectionChanged">
                <el-table-column
                        type="selection"
                        width="60">
                </el-table-column>
                 <el-table-column
                        prop="accountName"
                        label="账号名"
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
                <el-table-column :formatter="typeFormat"
                    prop="type"
                    label="类型"
                    width="180"
                    align="center">
                </el-table-column>
                <el-table-column
                    prop="params"
                    label="参数"
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
                <el-table-column
                    fixed="right"
                    label="操作"
                    align="center"
                    width="200">
                    <template #default="scope">
                        <el-link
                                style="margin-right: 20px"
                                @click="toDetail(scope)"
                                type="primary"
                                size="small"
                                :underline="false">
                          查看详情
                        </el-link>
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
        <ItemDialog ref="itemDialog" :type="data.type"></ItemDialog>
        <Upload ref="uploadExcelRef" @callback="uploadExcelCallback"></Upload>
    </div>
</template>
<script lang="ts" setup>
    import Api from '@/api/Monitor/api_logplatformoper.js'
    import ItemDialog from './Item.vue'
    import SuccessStatusColumn from "@/views/components/SuccessStatusColumn.vue";
    import { reactive, ref, defineProps, toRefs, onMounted} from 'vue'
    import Upload from "@/views/components/upload.vue";
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";

    const store = useStore();
    const router = useRouter()

    // Data
    const data = reactive({
        screenHeight: window.innerHeight,// screenHeight:控制高度自适应-页面高度
        otherHeight: 310,// otherHeight:控制高度自适应-表格外的高度
        isSearch: false, // isSearch:控制搜索状态
        selectedRows: {}, // selectedRows:选中行对象
        // formList:搜索条件对象 分页控制对象
        formList: {
            type: '',
            operation: '',
            createTime: '',
            endTime:''
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
        // dialog
        type: '',
    })

    // Mounted
    onMounted(() => {
        getData();
    })

    const typeFormat = (row, column) => {
        switch (row.type){
            case 1:
                return '增加';
            case 2:
                return '删除';
            case 3:
                return '修改';
            case 4:
                return '查询';
            case 5:
                return '其他';
            case 6:
                return '登录';
            default:
                return '未知';
        }
    }

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
            type : data.formList.type,
            operation : data.formList.operation,
            createTime : data.formList.createTime,
            endTime : data.formList.endTime,
            pageIndex : data.pageConfig.currentPage,
            pageSize : data.pageConfig.pageSize
        }
        // 后台请求
        Api.selpage4logplatformoper(params).then(res=> {
            if (res.code === 200){
                data.tableData = res.data.records
                data.pageConfig.total = res.data.total
                data.isSearch = false
            }
        })
    }
    // 添加记录
    const itemDialog = ref();
    const addData = () => {
        data.type = "add";
        itemDialog.value.init(null,data.type);
    }

    // 下载模板
    const downloadExcelTemplate = () => {
        const params = {}
        Api.downloadExcelTemplate(params).then(data => {
            const blob = new Blob([data.data], { type: 'application/vnd.ms-excel' })
            const blobUrl = URL.createObjectURL(blob)
            const a = document.createElement('a')
            a.href = blobUrl
            a.download = 'LogPlatformOper.xls'
            a.click()
            window.URL.revokeObjectURL(blobUrl)
        })
    }

    // 导入数据
    const uploadExcelRef = ref();
    const uploadExcel = () => {
        const uploadExcelUrl = Api.uploadExcelUrl();
        uploadExcelRef.value.init(uploadExcelUrl);
    }

    const deleteDataMany = () => {
        // 删除多条记录
        const datas = data.selectedRows
        const dataids = []
        if (!datas || !datas.length || datas.length === 0) {
            ElMessage({
                message: '请选择数据',
                type: 'warning',
            })
        } else {
            for (const i of datas) {
                dataids.push(i.id)
            }
            ElMessageBox.confirm(
                    '确认删除？',
                    '警告',
                    {
                        confirmButtonText: '确认删除',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
            ).then(() => {
                Api.dels4logplatformoper(dataids).then(res => {
                    if (res.code === 200){
                        ElMessage({
                            type: 'success',
                            message: '删除成功',
                        })
                        getData()
                    } else {
                        ElMessage({
                            type: 'warning',
                            message: '删除失败',
                        })
                    }
                })
            }).catch(() => {
                ElMessage({
                    type: 'info',
                    message: '已取消删除',
                })
            })
        }
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

    const excelData = () => {
        const params = {}
        Api.excelData4logplatformoper(params).then(data => {
            const blob = new Blob([data.data], { type: 'application/vnd.ms-excel' })
            const blobUrl = URL.createObjectURL(blob)
            const a = document.createElement('a')
            a.href = blobUrl
            a.download = 'LogPlatformOper.xls'
            a.click()
            window.URL.revokeObjectURL(blobUrl)
        })
    }

    /**
     导入后的回调方法
     @param action: put/delete， put=上传，delete=删除
     @param status: true/false，true=成功，false=失败
     @param groupId: 附件组ID
     @param response: 响应内容
     **/
    const uploadExcelCallback = (action, status, groupId, response) =>{
        if (action === 'put' && status) {
            ElMessage.success({
                message: '导入成功',
                type: 'success',
            })
        } else {
            ElMessage.warning({
                message: '导入失败',
                type: 'warning',
            })
        }
    }

    const selectionChanged = (val: number) => {
        // 选中行变化事件
        data.selectedRows = val
    }

    const toUpdate = (scope) => {
        data.type = "update"
        itemDialog.value.init(scope.row.id, data.type);
    }
    const toDetail = (scope) => {
        data.type = "detail"
        itemDialog.value.init(scope.row.logId, data.type);
    }

    const toDelete = (scope) => {
        ElMessageBox.confirm(
                '确认删除？',
                '警告',
                {
                    confirmButtonText: '确认删除',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
        ).then(() => {
            console.log(scope.row.id)
            Api.del4logplatformoper(scope.row.id).then(res => {
                console.log(res)
                if (res.code === 200){
                    ElMessage({
                        type: 'success',
                        message: '删除成功',
                    })
                    getData()
                } else {
                    ElMessage({
                        type: 'warning',
                        message: '删除失败',
                    })
                }
            })
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消删除',
            })
        })
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
