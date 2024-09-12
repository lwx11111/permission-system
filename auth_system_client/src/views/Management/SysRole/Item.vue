<template>
    <el-dialog v-model="data.showDialog"
               destroy-on-close
               :before-close="handleDialogClose"
                width="90%"
                :title="data.operateTitle">
        <el-card style="border: 1px solid gold;"
                 class="box-card"
                 shadow="never">
            <el-tabs v-model="data.activeName" class="demo-tabs" @tab-click="handleClick">
                <el-tab-pane label="角色属性" name="attribute">
                    <el-form
                        :model="data.item"
                        :rules="data.rules"
                        ref="itemForm"
                        label-width="100px">
                        <el-row>
                            <el-col :span="6">
                                <el-form-item
                                    label="角色编码"
                                    prop="roleCode">
                                    <el-input
                                        v-model="data.item.roleCode"
                                        :disabled="data.disabled">
                                    </el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item
                                    label="角色名称"
                                    prop="roleName">
                                    <el-input
                                        v-model="data.item.roleName"
                                        :disabled="data.disabled">
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="6">
                                <el-form-item
                                    label="角色描述"
                                    prop="remark">
                                    <el-input
                                        v-model="data.item.remark"
                                        :disabled="data.disabled">
                                    </el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="功能分配" name="allocation">
                    <el-row>
                        <el-col>
                            <div style="height: 350px;overflow: auto">
                                <el-tree
                                    ref="tree"
                                    :data="data.menuTreeData"
                                    show-checkbox
                                    highlight-current
                                    :expand-on-click-node="false"
                                    :default-checked-keys="data.checkMenuData"
                                    node-key="funId"
                                    :check-strictly="false"
                                    @node-click="handleFunNodeClick"
                                    :props="data.menuDefaultProps">
                                </el-tree>
                            </div>
                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="角色继承" name="inheritance">
                    <el-transfer
                        v-model="data.value"
                        filterable
                        :filter-method="filterMethod"
                        filter-placeholder="State Abbreviations"
                        :data="data.allRoles"
                        :titles="['待选角色', '已选角色']"
                        :props="{
                          key: 'roleId',
                          label: 'roleName',
                        }"
                    />
                </el-tab-pane>
                <el-tab-pane label="数据分配" name="data">
                    <el-row>
                        <el-col :span="12">
                            <div style="height: 350px;overflow: auto">
                                <el-tree
                                    ref="dsetTree"
                                    :data="data.dsetTreeData"
                                    show-checkbox
                                    highlight-current
                                    default-expand-all
                                    :expand-on-click-node="false"
                                    :default-checked-keys="data.checkDsetData"
                                    node-key="id"
                                    @node-click="handleDsetNodeClick"
                                    :props="data.dsetDefaultProps">
                                </el-tree>
                            </div>
                        </el-col>
                        <el-col :span="12">
                            <el-row :gutter="0">
                                <el-col :span="24">
                                    <el-table
                                        ref="dsetDataTable"
                                        :data="data.dsetData"
                                        style="width: 100%"
                                        height="399"
                                        highlight-current-row
                                        @selection-change="handleDsetDataSelectionChange">
                                        <el-table-column
                                            type="selection"
                                            label="设定"
                                            width="55">
                                        </el-table-column>
                                        <el-table-column
                                            prop="id"
                                            width="80"
                                            label="标识">
                                        </el-table-column>
                                        <el-table-column
                                            prop="label"
                                            label="名称">
                                        </el-table-column>
                                    </el-table>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-tab-pane>
            </el-tabs>
            <el-row style="justify-content: center">
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
            </el-row>

        </el-card>
    </el-dialog>
</template>
<script lang="ts" setup>
    import Api from '@/api/Management/api_sysrole.js'
    import ApiFunction from '@/api/Management/api_sysfunction.js'
    import ApiDataset from '@/api/Management/api_sysdataset.js'

    import { reactive, ref, onMounted, toRefs } from 'vue'
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";

    const store = useStore();
    const router = useRouter()

    // Data
    const data = reactive({
        // 数据集
        dsetTreeData: [],
        checkDsetData: [],
        dsetDataSection: [],
        dsetDefaultProps: {
            parent: 'parentId',
            value: 'id',
            label: 'dsetName',
            children: 'children'
        },
        // 功能
        menuDefaultProps: {
            parent: 'parentId',
            value: 'funId',
            label: 'funName',
            children: 'children'
        },
        menuTreeData: [],
        checkMenuData: [],
        // 成员
        value: [],
        allRoles: [],
        activeName: "attribute",
        operateTitle: '新增',
        type: '',
        showBtn: true,
        disabled: false,
        id: 0,
        item: {},
        params: {
            roleId: '',
            roleCode: '',
            appId: '',
            roleName: '',
            orderCode: '',
            remark: '',
            createdBy: '',
            createTime: '',
            updatedBy: '',
            updatedTime: '',
            customRoleId: ''
        },
        showDialog: false,
        rules: {
          roleCode: [
              { required: true, message: '不能为空', trigger: 'blur' }
          ],
          roleName: [
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
    const handleDsetDataSelectionChange = (val) => {
        data.dsetDataSection = val;
        const ids = data.dsetDataSection.map(item => item.id).join(',');
        data.currentDset.dsetDataList = ids;
    }

    const handleClick = (tab: TabsPaneContext, event: Event) => {
        console.log(tab, event)
    }

    const handleFunNodeClick = (data, node, tree) => {
        console.log(data)
        console.log(node)
        if (!this.hasResType) return;
        const {funId, funType} = data;
        if (funType === '1') {
            // this.$message('');
            return;
        }
        this.currentFun = data;
        this.resData = [];
        listFunResTypesApi(funId).then(response => {
            this.resTypeData = response.data;
            const selResData = this.currentFun.selResData;
            if (selResData !== undefined || selResData === null) {
                this.$nextTick(() => {
                    this.resTypeData.forEach(row => {
                        const isFind = selResData.find(res => res.resTypeId === row.resTypeId);
                        if (isFind !== undefined) {
                            this.$refs.resTypeTable.toggleRowSelection(row);
                        }
                    });
                })
            }
            this.$nextTick(() => {
                this.resTypeData.forEach(row => {
                    row.permit = '1'
                });
            })
        });
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
            Api.sel4sysrole(data.id).then(res => {
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

        getFunctionData();
        getDatasetData();
        getRoleInheritanceData();
    }

    const getFunctionData = () => {
        const params = {
            roleId: data.id,
            appId: '1'
        }
        ApiFunction.listFunctionsByAccount(params).then(response => {
            console.log(response)
            if (response.code === 200){
                data.menuTreeData = response.data.menus;
                data.checkMenuData = response.data.checkMenuIds;
            }
        });
    }

    const getDatasetData = () => {
        const params = {
            roleId: data.id,
            appId: '1'
        }
        ApiDataset.listDatasetByRole(params).then(response => {
            console.log(response)
            if (response.code === 200){
                data.dsetTreeData = [];
                const root = {id: '0', dsetName: '数据集'};
                root.children = response.data.datasetList;
                data.dsetTreeData.push(root);
                data.checkDsetData = response.data.checkDataset;
            }
        });
    }

    const getRoleInheritanceData = () => {
        // 获取角色继承信息
        const params = {
            roleId: data.id,
            appId: "1"
        }
        Api.roleInheritance(params).then(res => {
            console.log(res)
            if (res.code === 200){
                data.value = res.data.inheritRoles
                data.allRoles = res.data.roles
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

    // 表单ref
    const dsetTree = ref();
    // 树形控件ref
    const tree = ref();
    const saveOrUpdate = () => {
        // 保存或更新操作
        if (data.type === 'update') {
            Api.update4sysrole(data.id, data.item).then(res => {
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
            const checkedNodes = tree.value.getCheckedNodes(false, true);
            const selFun = checkedNodes.map(node => {
                return node.funId
            });
            const dsetCheckedNodes = dsetTree.value.getCheckedNodes(false, false);
            const dataset = dsetCheckedNodes.filter(node => {
                if (node.id !== '0' && node.dsetDataList !== undefined) {
                    return true;
                } else {
                    return false;
                }
            }).map(node => {
                return node.id
            });
            data.item.functions = selFun;
            data.item.dataset = dataset;
            data.item.inheritRoles = data.value;
            Api.add4sysrole(data.item).then(res => {
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
