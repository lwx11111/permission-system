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
                                label="规则名称"
                                prop="ruleDesc">
                            <el-input
                                    v-model="data.item.ruleDesc"
                                    :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item
                            label="状态"
                            prop="activeStatus">
                            <el-select  v-model="data.item.activeStatus"
                                        :disabled="data.disabled">
                                <el-option value="0" label="生效"/>
                                <el-option value="1" label="不生效"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item
                                label="用户自定义"
                                prop="userDefine">
                            <el-select   v-model="data.item.userDefine"
                                         :disabled="data.disabled">
                                <el-option value="0" label="不开启"/>
                                <el-option value="1" label="开启"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row v-if="data.item.userDefine === '1'">
                    <el-col :span="6" >
                        <el-form-item
                            label="自定义表达式"
                            prop="userDefineExp">
                            <el-input
                                v-model="data.item.userDefineExp"
                                :disabled="data.disabled">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <template v-else>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item
                                label="历史密码不重复次数"
                                prop="historyRepeatTimes">
                                <el-input
                                    v-model="data.item.historyRepeatTimes"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item
                                label="特殊字符个数"
                                prop="specificCharNum">
                                <el-input
                                    v-model="data.item.specificCharNum"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="6">
                            <el-form-item
                                label="大写字母个数"
                                prop="bigLetterNum">
                                <el-input
                                    v-model="data.item.bigLetterNum"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item
                                label="小写字母个数"
                                prop="smallLetterNum">
                                <el-input
                                    v-model="data.item.smallLetterNum"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item
                                label="数字个数"
                                prop="numberNum">
                                <el-input
                                    v-model="data.item.numberNum"
                                    :disabled="data.disabled">
                                </el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item
                                label="最小长度"
                                prop="minLength">
                                <el-input
                                    v-model="data.item.minLength"
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
    import Api from '@/api/Management/api_syscredentialrule.js'
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
            ruleId: '',
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
        showDialog: false,
        rules: {
          ruleDesc: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          activeStatus: [
              { required: true, message: '0-不生效 1-生效不能为空', trigger: 'blur' }
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
            Api.sel4syscredentialrule(data.id).then(res => {
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
            Api.update4syscredentialrule(data.id, data.item).then(res => {
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
            Api.add4syscredentialrule(data.item).then(res => {
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
