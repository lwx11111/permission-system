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
                                label="accountId"
                                prop="accountId">
                            <el-input
                                    v-model="data.item.accountId"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="userId"
                                prop="userId">
                            <el-input
                                    v-model="data.item.userId"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="1	增加"
                                prop="type">
                            <el-input
                                    v-model="data.item.type"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="4	极敏感级 "
                                prop="sensitiveLevel">
                            <el-input
                                    v-model="data.item.sensitiveLevel"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="operation"
                                prop="operation">
                            <el-input
                                    v-model="data.item.operation"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="time"
                                prop="time">
                            <el-input
                                    v-model="data.item.time"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="method"
                                prop="method">
                            <el-input
                                    v-model="data.item.method"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="params"
                                prop="params">
                            <el-input
                                    v-model="data.item.params"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="operator"
                                prop="operator">
                            <el-input
                                    v-model="data.item.operator"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="serverIp"
                                prop="serverIp">
                            <el-input
                                    v-model="data.item.serverIp"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="clientIp"
                                prop="clientIp">
                            <el-input
                                    v-model="data.item.clientIp"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="createTime"
                                prop="createTime">
                            <el-input
                                    v-model="data.item.createTime"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="location"
                                prop="location">
                            <el-input
                                    v-model="data.item.location"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="0:成功1:失败"
                                prop="successFlag">
                            <el-input
                                    v-model="data.item.successFlag"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
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
    import Api from '@/api/Monitor/api_logplatformoper.js'
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
            logId: '',
            accountId: '',
            userId: '',
            type: '',
            sensitiveLevel: '',
            operation: '',
            time: '',
            method: '',
            params: '',
            operator: '',
            serverIp: '',
            clientIp: '',
            createTime: '',
            location: '',
            successFlag: ''
        },
        showDialog: false,
        rules: {
          accountId: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userId: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          type: [
              { required: true, message: '1	增加不能为空', trigger: 'blur' }
          ],
          sensitiveLevel: [
              { required: true, message: '4	极敏感级不能为空', trigger: 'blur' }
          ],
          operation: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          time: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          method: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          params: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          operator: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          serverIp: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          clientIp: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          location: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          successFlag: [
              { required: true, message: '0:成功', trigger: 'blur' }
          ]
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
            Api.sel4logplatformoper(data.id).then(res => {
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
            Api.update4logplatformoper(data.id, data.item).then(res => {
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
            Api.add4logplatformoper(data.item).then(res => {
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
