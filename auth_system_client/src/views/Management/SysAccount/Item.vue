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
                                label="用户ID"
                                prop="userId">
                            <el-input
                                    v-model="data.item.userId"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="应用ID"
                                prop="appId">
                            <el-input
                                    v-model="data.item.appId"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="账号名"
                                prop="accountName">
                            <el-input
                                    v-model="data.item.accountName"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="登录名"
                                prop="loginName">
                            <el-input
                                    v-model="data.item.loginName"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="密码"
                                prop="loginPass">
                            <el-input
                                    v-model="data.item.loginPass"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="avatar"
                                prop="avatar">
                            <el-input
                                    v-model="data.item.avatar"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="allowWeek"
                                prop="allowWeek">
                            <el-input
                                    v-model="data.item.allowWeek"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="allowBeginTime"
                                prop="allowBeginTime">
                            <el-input
                                    v-model="data.item.allowBeginTime"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="allowEndTime"
                                prop="allowEndTime">
                            <el-input
                                    v-model="data.item.allowEndTime"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="状态0正常1锁定2逻辑删除"
                                prop="status">
                            <el-input
                                    v-model="data.item.status"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="description"
                                prop="description">
                            <el-input
                                    v-model="data.item.description"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="startTime"
                                prop="startTime">
                            <el-input
                                    v-model="data.item.startTime"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="endTime"
                                prop="endTime">
                            <el-input
                                    v-model="data.item.endTime"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="createdBy"
                                prop="createdBy">
                            <el-input
                                    v-model="data.item.createdBy"
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
                    <el-col :span="6">
                        <el-form-item
                                label="updatedBy"
                                prop="updatedBy">
                            <el-input
                                    v-model="data.item.updatedBy"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="updatedTime"
                                prop="updatedTime">
                            <el-input
                                    v-model="data.item.updatedTime"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="loginFailTimes"
                                prop="loginFailTimes">
                            <el-input
                                    v-model="data.item.loginFailTimes"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="lastLoginTime"
                                prop="lastLoginTime">
                            <el-input
                                    v-model="data.item.lastLoginTime"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="initialPasswordFlag"
                                prop="initialPasswordFlag">
                            <el-input
                                    v-model="data.item.initialPasswordFlag"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="phone"
                                prop="phone">
                            <el-input
                                    v-model="data.item.phone"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="定制ID，暂时用该字段区分用户类型"
                                prop="customAccountId">
                            <el-input
                                    v-model="data.item.customAccountId"
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
    import Api from '@/api/Management/api_sysaccount.js'
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
            accountId: '',
            userId: '',
            appId: '',
            accountName: '',
            loginName: '',
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
        showDialog: false,
        rules: {
          userId: [
              { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          appId: [
              { required: true, message: '应用ID不能为空', trigger: 'blur' }
          ],
          accountName: [
              { required: true, message: '账号名不能为空', trigger: 'blur' }
          ],
          loginName: [
              { required: true, message: '登录名不能为空', trigger: 'blur' }
          ],
          loginPass: [
              { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          avatar: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          allowWeek: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          allowBeginTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          allowEndTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
              { required: true, message: '状态0正常1锁定2逻辑删除不能为空', trigger: 'blur' }
          ],
          description: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          startTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          endTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createdBy: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          updatedBy: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          updatedTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          loginFailTimes: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          lastLoginTime: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          initialPasswordFlag: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          phone: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          customAccountId: [
              { required: true, message: '定制ID，暂时用该字段区分用户类型不能为空', trigger: 'blur' }
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
            Api.sel4sysaccount(data.id).then(res => {
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
            Api.update4sysaccount(data.id, data.item).then(res => {
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
            Api.add4sysaccount(data.item).then(res => {
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

    //暴露init方法
    defineExpose({
        init,
    });
</script>
