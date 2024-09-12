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
                            label="用户名"
                            prop="userName">
                            <el-input
                                v-model="data.item.userName"
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
                            label="帐号状态"
                            prop="status">
                            <el-select v-model="data.item.status"
                                       :disabled="data.disabled">
                                <el-option value="0" label="正常状态"/>
                                <el-option value="1" label="锁定状态"/>
                                <el-option value="2" label="删除状态"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                            label="所属公司"
                            prop="companyId">
                            <el-input
                                v-model="data.item.companyId"
                                :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="所属部门:"
                                      prop="orgCode">
                            <el-input
                                v-model="data.item.orgCode"
                                :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                            label="用户类型"
                            prop="employeeType">
                            <el-select v-model="data.item.employeeType"
                                       :disabled="data.disabled">
                                <el-option label="内部用户" value="1"/>
                                <el-option label="外部用户" value="2"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                            label="省份"
                            prop="provinceId">
                            <el-input
                                v-model="data.item.provinceId"
                                :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                            label="地市"
                            prop="cityId">
                            <el-input
                                v-model="data.item.cityId"
                                :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                            label="区县"
                            prop="cityName">
                            <el-input
                                v-model="data.item.countyId"
                                :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                            label="电话"
                            prop="mobile">
                            <el-input
                                v-model="data.item.mobile"
                                :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                            label="邮箱"
                            prop="email">
                            <el-input
                                v-model="data.item.email"
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
    import Api from '@/api/Management/api_sysuser.js'
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
            userId: '',
            userName:'',
            appId: '',
            orgCode: '',
            companyId: '',
            sn: '',
            description: '',
            email: '',
            nation: '',
            gender: '',
            birthday: '',
            c: '',
            religion: '',
            telephoneNumber: '',
            mobile: '',
            facsimileTelephoneNumber: '',
            startTime: '',
            endTime: '',
            status: '',
            passwordModifiedDate: '',
            idCardNumber: '',
            employeeType: '',
            createdBy: '',
            createTime: '',
            updatedBy: '',
            updatedTime: '',
            employeeNumber: '',
            userLevel: '',
            levelName: '',
            category: '',
            functions: '',
            displayOrder: '',
            duty: '',
            positionLevel: '',
            supporterCorpName: '',
            supporterDept: '',
            supporterCorpContact: '',
            supervisor: '',
            post: '',
            userNo: '',
            user: '',
            provinceId: '',
            cityId: '',
            cityName: '',
            companyName: '',
            orgName: '',
            countyId: '',
            countyName: '',
            accountName: '',
            userRoleId: ''
        },
        showDialog: false,
        rules: {

            userName: [
                { required: true, message: 'userName不能为空', trigger: 'blur' }
            ],
          appId: [
              { required: true, message: '多租户应用ID不能为空', trigger: 'blur' }
          ],
          orgCode: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          companyId: [
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
            Api.sel4sysuser(data.id).then(res => {
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
            Api.update4sysuser(data.id, data.item).then(res => {
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
            Api.add4sysuser(data.item).then(res => {
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
