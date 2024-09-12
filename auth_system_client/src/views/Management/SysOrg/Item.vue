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
                                label="公司"
                                prop="companyId">
                            <el-select v-model="data.item.companyId"
                                       :disabled="data.disabled"
                                       @change="handleCompanyChange">
                                <el-option
                                    v-for="item in data.companyList"
                                    :key="item.companyId"
                                    :label="item.companyName"
                                    :value="item.companyId"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="部门名称"
                                prop="orgName">
                            <el-input
                                    v-model="data.item.orgName"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="部门形态"
                                prop="style">
                            <el-select v-model="data.item.style"
                                       :disabled="data.disabled">
                                <el-option value="1" label="公司"/>
                                <el-option value="2" label="部门"/>
                                <el-option value="3" label="其他组织"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="上级组织"
                                prop="parentOrgCode">
                            <el-cascader
                                :options="data.orgList"
                                @change="orgCodeChange"
                                v-model="data.item.parentOrgCode"
                                :disabled="data.disabled"
                                node-key="orgCode"
                                :show-all-levels="true"
                                :props="data.propsOrg">
                            </el-cascader>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="职责"
                                prop="responsibility">
                            <el-input
                                    v-model="data.item.responsibility"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="上级主管领导的UID"
                                prop="supervisor">
                            <el-input
                                    v-model="data.item.supervisor"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="部门负责人"
                                prop="orgManager">
                            <el-input
                                    v-model="data.item.orgManager"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="排序"
                                prop="orgOrder">
                            <el-input
                                    v-model="data.item.orgOrder"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="状态"
                                prop="status">
                            <el-select v-model="data.item.status"
                                       :disabled="data.disabled">
                                <el-option value="1" label="正常"/>
                                <el-option value="0" label="锁定"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="部门电话"
                                prop="telephoneNumber">
                            <el-input
                                    v-model="data.item.telephoneNumber"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="传真电话"
                                prop="facsimileTelephoneNumber">
                            <el-input
                                    v-model="data.item.facsimileTelephoneNumber"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                                label="描述"
                                prop="description">
                            <el-input
                                    v-model="data.item.description"
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
    import Api from '@/api/Management/api_sysorg.js'
    import ApiCompany from '@/api/Management/api_syscompany.js'
    import { reactive, ref, onMounted, toRefs } from 'vue'
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";

    const store = useStore();
    const router = useRouter()

    // Data
    const data = reactive({
        propsOrg: {
            checkStrictly: true,
            value: 'orgCode',
            label: 'orgName'
        },
        orgList: [],
        companyList:[],
        operateTitle: '新增',
        type: '',
        showBtn: true,
        disabled: false,
        id: 0,
        item: {},
        params: {
            orgCode: '',
            companyId: '',
            orgName: '',
            style: '',
            parentOrgCode: '',
            leaf: '',
            responsibility: '',
            supervisor: '',
            orgManager: '',
            viceManager: '',
            orgOrder: '',
            status: '',
            telephoneNumber: '',
            facsimileTelephoneNumber: '',
            initials: '',
            description: '',
            createdBy: '',
            createTime: '',
            updatedBy: '',
            updatedTime: '',
            appId: ''
        },
        showDialog: false,
        rules: {
          companyId: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          orgName: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          style: [
              { required: true, message: '可选值：1. 公司2. 部门3．外部组织不能为空', trigger: 'blur' }
          ],
          status: [
              { required: true, message: '定义组织的状态，“0”表示正常状态，“1”表示锁定状态不能为空', trigger: 'blur' }
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
    const orgCodeChange = (val) => {
        data.item.parentOrgCode = val[val.length - 1];
        // var pathName = '';
        // var rootList = data.orgList
        // for (var i = 0; i < val.length; i++){
        //     pathName = pathName + findNode(rootList, val[i]) + "/";
        // }
        // console.log(pathName)
        // data.item.orgPath = pathName;
    }


    const findNode = (data, orgCode) => {
        for (var i = 0; i < data.length; i++){
            if (data[i].orgCode === orgCode){
                return data[i].orgName
            }
        }

    }

    const handleCompanyChange = () => {
        const params = {
            appId: '1',
            companyId: data.item.companyId
        }
        Api.listOrgsByCompany(params).then(res => {
            console.log(res)
            if (res.code === 200){
                data.orgList = res.data
            } else {
                ElMessage({
                    message: '获取数据失败，请重试',
                    type: 'warning',
                })
            }
        })

    }

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
            Api.sel4sysorg(data.id).then(res => {
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

        const params = {
            appId: '1'
        }
        ApiCompany.listCompany(params).then(res => {
            console.log(res)
            if (res.code === 200){
                data.companyList = res.data
            } else {
                ElMessage({
                    message: '获取数据失败，请重试',
                    type: 'warning',
                })
            }
        })
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
            Api.update4sysorg(data.id, data.item).then(res => {
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
            console.log(data.item)
            Api.add4sysorg(data.item).then(res => {
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
