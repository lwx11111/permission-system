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
                          系统密码规则管理
                        </div>
                    </template>
                    <div style="display: flex;"
                         class="card-search">
                        <el-form :inline="true"
                                 :model="data.formList"
                                 size="default"
                                 label-width="100px">
                            <el-form-item label="规则名称">
                                <el-input placeholder="规则名称"
                                          v-model="data.formList.ruleDesc"
                                          style="width: 200px"
                                          @keyup.enter.native="getData">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="状态">
                                <el-select placeholder="请输入0-不生效 1-生效"
                                            v-model="data.formList.activeStatus"
                                            style="width: 200px"
                                            @keyup.enter.native="getData">
                                    <el-option value="0" label="生效"/>
                                    <el-option value="1" label="不生效"/>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-collapse-item>
            </el-collapse>
        </el-card>
        <el-card style="margin: 10px; border: 1px solid gold">
            <!-- 操作按钮区 -->
            <div style="margin:10px 0;">
                <el-button
                        type="success"
                        icon="DocumentAdd"
                        @click="addData()">
                  新增
                </el-button>
                <el-button
                        type="warning"
                        icon="DocumentDelete"
                        @click="deleteDataMany()">
                  删除
                </el-button>
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
                  border
                  @selection-change="selectionChanged">
                <el-table-column
                        type="selection"
                        width="60">
                </el-table-column>
                 <el-table-column
                        prop="ruleDesc"
                        label="规则名称"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="userDefine"
                        label="用户自定义"
                        width="180"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="activeStatus"
                        label="状态"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="userDefineExp"
                        label="自定义表达式"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="specificCharNum"
                        label="特殊字符个数"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="bigLetterNum"
                        label="大写字母个数"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="smallLetterNum"
                        label="小写字母个数"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="numberNum"
                        label="数字个数"
                        width="180"
                        align="center">
                </el-table-column>
                 <el-table-column
                        prop="minLength"
                        label="最小长度"
                        width="180"
                        align="center">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        align="center"
                        width="400">
                    <template #default="scope">
                        <el-link
                                style="margin-right: 20px"
                                @click="toUpdate(scope)"
                                type="primary"
                                size="small"
                                :underline="false">
                          更新
                        </el-link>
                        <el-link
                                style="margin-right: 20px"
                                @click="toDetail(scope)"
                                type="primary"
                                size="small"
                                :underline="false">
                          查看详情
                        </el-link>
                        <el-link
                                @click="toDelete(scope)"
                                type="primary"
                                size="small"
                                :underline="false">
                          删除
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
    import Api from '@/api/Management/api_syscredentialrule.js'
    import ItemDialog from './Item.vue'
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
            ruleDesc: '',
            userDefine: '',
            userDefineExp: '',
            specificCharNum: '',
            bigLetterNum: '',
            smallLetterNum: '',
            numberNum: '',
            minLength: '',
            activeStatus: '',
            historyRepeatTimes: '',
            createdBy: '',
            createTime: '',
            updatedBy: '',
            updatedTime: '',
            appId: ''
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
    // 解构抛出 直接使用
    // const { type} = toRefs(data)

    // Mounted
    onMounted(() => {
        getData();
    })

    // Methods
    const getData = () => {
        // 查询方法
        // 查询参数
        const params = {
            ruleDesc : data.formList.ruleDesc,
            activeStatus : data.formList.activeStatus,
            pageIndex : data.pageConfig.currentPage,
            pageSize : data.pageConfig.pageSize
        }
        // 后台请求
        Api.selpage4syscredentialrule(params).then(res=> {
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
            a.download = '系统密码规则.xls'
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
                Api.dels4syscredentialrule(dataids).then(res => {
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
        Api.excelData4syscredentialrule(params).then(data => {
            const blob = new Blob([data.data], { type: 'application/vnd.ms-excel' })
            const blobUrl = URL.createObjectURL(blob)
            const a = document.createElement('a')
            a.href = blobUrl
            a.download = '系统密码规则.xls'
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
        itemDialog.value.init(scope.row.id, data.type);
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
            Api.del4syscredentialrule(scope.row.id).then(res => {
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
