<template>
    <el-dialog v-model="data.showDialog"
               destroy-on-close
               :before-close="handleDialogClose"
                width="90%"
                :title="data.operateTitle">
        <el-card style="border: 1px solid gold;"
                 class="box-card"
                 shadow="never">
            <el-form
                    :model="data.item"
                    :rules="data.rules"
                    ref="itemForm"
                    label-width="100px">
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="编码"
                                prop="dsetCode">
                            <el-input
                                    v-model="data.item.dsetCode"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="名称"
                                prop="dsetName">
                            <el-input
                                    v-model="data.item.dsetName"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                            label="获取数据方式 "
                            prop="fetchDataType">
                            <el-select v-model="data.item.fetchDataType"
                                       :disabled="data.disabled">
                                <el-option value="0" label="数据源"/>
                                <el-option value="1" label="REST接口"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if=" data.item.fetchDataType === '0'">
                    <el-col :span="6" >
                        <el-form-item
                                label="获取数据内容"
                                prop="fetchDataContent">
                            <el-input
                                    v-model="data.item.fetchDataContent"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <template v-else>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item
                                label="表名"
                                prop="resTable">
                                <el-input
                                    v-model="data.item.resTable"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item
                                label="主键列名"
                                prop="primarykeyCol">
                                <el-input
                                    v-model="data.item.primarykeyCol"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item
                                label="显示列名"
                                prop="resnameCol">
                                <el-input
                                    v-model="data.item.resnameCol"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item
                                label="过滤条件"
                                prop="whereClause">
                                <el-input
                                    v-model="data.item.whereClause"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item
                                label="排序列名"
                                prop="orderCol">
                                <el-input
                                    v-model="data.item.orderCol"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </template>
                <el-form-item>
                    <el-button
                            v-show="data.showBtn"
                            type="primary"
                            @click="submitForm('itemForm')">
                      保存
                    </el-button>
                    <el-button
                            v-show="data.showBtn"
                            @click="resetForm()">
                      重置
                    </el-button>
                    <el-button @click="back()">
                      返回
                    </el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </el-dialog>
</template>
<script lang="ts" setup>
    import Api from '@/api/Management/api_sysdataset.js'
    import { reactive, ref, onMounted, toRefs } from 'vue'
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";

    const store = useStore();
    const router = useRouter()

    // Data
    const data = reactive({
        operateTitle: '新增',
        type: '',
        showBtn: true,
        disabled: false,
        id: 0,
        item: {},
        params: {
            dsetId: '',
            dsId: '',
            appId: '',
            dsetCode: '',
            dsetName: '',
            fetchDataContent: '',
            fetchDataType: '',
            createdBy: '',
            createTime: '',
            updatedBy: '',
            updatedTime: '',
            resTable: '',
            primarykeyCol: '',
            resnameCol: '',
            whereClause: '',
            orderCol: ''
        },
        showDialog: false,
        rules: {
          dsetCode: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          dsetName: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          fetchDataType: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
        },
    })

    // Props
    const props = defineProps({
        //子组件接收父组件传递过来的值
        type: {
            type: String,
            default: '新增'
        },
    })

    // Mounted
    onMounted(() => {

    })

    // Methods
    const init = (id, type) => {
        // 界面初始化接收参数
        data.type = type;
        switch (data.type) {
            case 'add':
                data.operateTitle = '新增'
                data.showBtn = true
                data.disabled = false
                break
            case 'detail':
                data.operateTitle = '详情'
                data.showBtn = false
                data.disabled = true
                break
            case 'update':
                data.operateTitle = '修改'
                data.showBtn = true
                data.disabled = false
                break
        }

        // 获取数据
        if (data.type === 'detail' || data.type === 'update') {
            // ID校验
            if (id != null && id != '') {
                data.id = id
            } else {
                ElMessage({
                  message: '数据ID丢失，请重试',
                  type: 'warning',
                })
                return;
            }
            // 发送请求
            Api.sel4sysdataset(data.id).then(res => {
                console.log(res)
                if (res.code === 200){
                    data.item = res.data;
                    // 界面显示
                    data.showDialog = true;
                } else {
                    ElMessage({
                      message: '获取数据失败，请重试',
                      type: 'warning',
                    })
                    return;
                }
            })
        } else {
            // 界面显示
            data.showDialog = true;
        }

    }

    const back = () => {
        // 返回操作
        data.showDialog = false;
        location.reload()
    }

    // 表单ref
    const itemForm = ref();
    const submitForm = (formName) => {
        // 表单验证
        itemForm.value.validate((valid, fields) => {
            if (valid) {
                saveOrUpdate();
            } else {
                ElMessage({
                  message: '请校验',
                  type: 'warning',
                })
            }
        })
    }

    const resetForm = () => {
        // 重置按钮
        itemForm.value.resetFields();
    }

    const saveOrUpdate = () => {
        // 保存或更新操作
        if (data.type === 'update') {
            Api.update4sysdataset(data.id, data.item).then(res => {
                if (res.code === 200){
                    ElMessage({
                      message: '修改成功',
                      type: 'success',
                    })
                    back()
                } else {
                    ElMessage({
                      message: '修改失败',
                      type: 'warning',
                    })
                }
            })
        } else if (data.type === 'add') {
            Api.add4sysdataset(data.item).then(res => {
                console.log(res)
                if (res.code === 200){
                    ElMessage({
                      message: '保存成功',
                      type: 'success',
                    })
                    back();
                } else {
                    ElMessage({
                      message: '保存失败',
                      type: 'warning',
                    })
                }
            })
        }
    }

    /**
     * 关闭弹窗前的操作
     */
    const handleDialogClose = () => {
        if (data.type === 'add' || data.type === 'update') {
            ElMessageBox.confirm('确认关闭？ 数据将不会保存')
                    .then(() => {
                        data.item = {};
                        data.params = {};
                        data.showDialog = false;
                    })
                    .catch(() => {

                    });
        } else {
            data.showDialog = false;
        }
    }

    //暴露state和play方法
    defineExpose({
        init,
    });
</script>
