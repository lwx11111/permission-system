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
                          用户账号信息表管理
                        </div>
                    </template>
                    <div style="display: flex;"
                         class="card-search">
                        <el-form :inline="true"
                                 :model="data.formList"
                                 size="default"
                                 label-width="100px">
                            <el-form-item label="账号名">
                                <el-input placeholder="请输入账号名"
                                          v-model="data.formList.accountName"
                                          style="width: 200px"
                                          @keyup.enter.native="getData">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="状态">
                                <el-select placeholder="请输入状态"
                                           v-model="data.formList.status"
                                           style="width: 200px"
                                           @keyup.enter.native="getData">
                                    <el-option value="0" label="正常状态"/>
                                    <el-option value="1" label="锁定状态"/>
                                    <el-option value="2" label="删除状态"/>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="用户ID">
                                <el-input placeholder="请输入用户ID"
                                            v-model="data.formList.userId"
                                            style="width: 200px"
                                            @keyup.enter.native="getData">
                                  </el-input>
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
                          @click="excelData()">
                    导出数据
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
                        prop="userName"
                        label="用户名"
                        width="180"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="accountName"
                        label="账号名"
                        width="180"
                        align="center">
                </el-table-column>
                <el-table-column
                    prop="lastLoginTime"
                    label="上次登录时间"
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
                    prop="createdBy"
                    label="创建人"
                    width="180"
                    align="center">
                </el-table-column>
                <el-table-column
                        prop="status"
                        label="状态"
                        width="180"
                        align="center">
                    <template #default="scope">
                        <UserStatusColumn :status="scope.row.status"/>
                    </template>
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        align="center"
                        width="400">
                    <template #default="scope">
                        <el-link
                            v-if="scope.row.status === '0'"
                            style="margin-right: 20px"
                            @click="lock(scope)"
                            type="primary"
                            size="small"
                            :underline="false">
                            锁定
                        </el-link>
                        <el-link
                            v-if="scope.row.status === '1'"
                            style="margin-right: 20px"
                            @click="unlock(scope)"
                            type="primary"
                            size="small"
                            :underline="false">
                            解锁
                        </el-link>
                        <el-link
                                style="margin-right: 20px"
                                @click="resetPassword(scope)"
                                type="primary"
                                size="small"
                                :underline="false">
                            重置密码
                        </el-link>
                        <el-link
                                style="margin-right: 20px"
                                @click="toModifyPassword(scope)"
                                type="primary"
                                size="small"
                                :underline="false">
                            修改密码
                        </el-link>
                        <el-link
                                style="margin-right: 20px"
                                @click="setPermissions(scope)"
                                type="primary"
                                size="small"
                                :underline="false">
                            设置权限
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
        <ModifyPassword ref="MPDialog"></ModifyPassword>
        <AccountPremDialog ref="premDialog"></AccountPremDialog>
<!--            v-if="data.permDialogVisible"-->
<!--                           :dialog-visible="data.permDialogVisible"-->
<!--                           @close="handlePermCloseDialog"/>-->
    </div>
</template>
<script lang="ts" setup>
    import Api from '@/api/Management/api_sysaccount.js'
    import ItemDialog from './Item.vue'
    import { reactive, ref, defineProps, toRefs, onMounted} from 'vue'
    import Upload from "@/views/components/upload.vue";
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";
    import UserStatusColumn from "@/views/Management/SysUser/UserStatusColumn.vue";
    import ModifyPassword from "@/views/Management/SysAccount/ModifyPassword.vue";
    import AccountPremDialog from "@/views/Management/SysAccount/AccountPremDialog.vue";
    const store = useStore();
    const router = useRouter()

    // Data
    const data = reactive({
        // 设置权限
        permDialogVisible: false,
        // 修改密码
        passwordDialogVisible: false,
        modifyPasswordAccountId:"",

        screenHeight: window.innerHeight,// screenHeight:控制高度自适应-页面高度
        otherHeight: 310,// otherHeight:控制高度自适应-表格外的高度
        isSearch: false, // isSearch:控制搜索状态
        selectedRows: {}, // selectedRows:选中行对象
        // formList:搜索条件对象 分页控制对象
        formList: {
            accountId: '',
            userId: '',
            appId: '',
            accountName: '',
            loginPass: '',
            avatar: '',
            allowWeek: '',
            allowBeginTime: '',
            allowEndTime: '',
            status: '',
            description: '',
            startTime: '',
            endTime: '',
            createdBy: '',
            createTime: '',
            updatedBy: '',
            updatedTime: '',
            loginFailTimes: '',
            lastLoginTime: '',
            initialPasswordFlag: '',
            phone: '',
            customAccountId: ''
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

    // Methods
    const unlock = (scope) => {
        ElMessageBox.confirm(
            '确定要解锁此账户吗?',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        ).then(() => {
            Api.unlock(scope.row.accountId).then(res => {
                console.log(res)
                ElMessage({
                    type: 'success',
                    message: '解锁成功',
                })
                getData()
            })
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消',
            })
        })
    }

    const lock = (scope) => {
        ElMessageBox.confirm(
            '确定要锁定此账户吗?',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        ).then(() => {
            console.log(scope.row.accountId)
            Api.lock(scope.row.accountId).then(res => {
                console.log(res)
                ElMessage({
                    type: 'success',
                    message: '锁定成功',
                })
                getData()
            })
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消',
            })
        })
    }

    const resetPassword = (scope) => {
        ElMessageBox.confirm(
            '确定要重置密码吗?',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        ).then(() => {
            Api.resetPassword(scope.row.accountId).then(res => {
                console.log(res)
                ElMessage({
                    type: 'success',
                    message: '重置密码成功',
                })
                getData()
            })
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消',
            })
        })
    }


    const handlePasswordCloseDialog = () => {
        data.passwordDialogVisible = false;
    }

    /**
     * 设置权限
     */
    const premDialog = ref();
    const setPermissions = (scope) => {
        premDialog.value.init(scope.row.accountId);
    }

    const handlePermCloseDialog = () => {
        data.permDialogVisible = false
        data.id = ''
    }

    const getData = () => {
        // 查询方法
        // 查询参数
        const params = {
            userId : data.formList.userId,
            appId : data.formList.appId,
            accountName : data.formList.accountName,
            loginPass : data.formList.loginPass,
            avatar : data.formList.avatar,
            allowWeek : data.formList.allowWeek,
            allowBeginTime : data.formList.allowBeginTime,
            allowEndTime : data.formList.allowEndTime,
            status : data.formList.status,
            description : data.formList.description,
            startTime : data.formList.startTime,
            endTime : data.formList.endTime,
            createdBy : data.formList.createdBy,
            createTime : data.formList.createTime,
            updatedBy : data.formList.updatedBy,
            updatedTime : data.formList.updatedTime,
            loginFailTimes : data.formList.loginFailTimes,
            lastLoginTime : data.formList.lastLoginTime,
            initialPasswordFlag : data.formList.initialPasswordFlag,
            customAccountId : data.formList.customAccountId,
            pageIndex : data.pageConfig.currentPage,
            pageSize : data.pageConfig.pageSize
        }
        // 后台请求
        Api.selpage4sysaccount(params).then(res=> {
            console.log(res)
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

    const MPDialog = ref();
    const toModifyPassword = (scope) => {
        MPDialog.value.init(scope.row.accountId);
    }


    // 下载模板
    const downloadExcelTemplate = () => {
        const params = {}
        Api.downloadExcelTemplate(params).then(data => {
            const blob = new Blob([data.data], { type: 'application/vnd.ms-excel' })
            const blobUrl = URL.createObjectURL(blob)
            const a = document.createElement('a')
            a.href = blobUrl
            a.download = '用户账号信息表.xls'
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
                Api.dels4sysaccount(dataids).then(res => {
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
        Api.excelData4sysaccount(params).then(data => {
            const blob = new Blob([data.data], { type: 'application/vnd.ms-excel' })
            const blobUrl = URL.createObjectURL(blob)
            const a = document.createElement('a')
            a.href = blobUrl
            a.download = '用户账号信息表.xls'
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
            Api.del4sysaccount(scope.row.id).then(res => {
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
