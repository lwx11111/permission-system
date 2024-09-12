<template>
    <el-dialog
        title="【账号】权限设置"
        v-model="data.dialogVisible"
        append-to-body
        width="800px"
        :before-close="handleCancel">
        <el-form ref="form"
                 :model="data.user"
                 :rules="data.rules"
                 label-width="70px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="用户名:" prop="cn">
                      <el-input v-model="data.user.userName" :disabled="data.isEdit"/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="账号:">
                      <el-input v-model="data.account.accountName" :disabled="data.isEdit"/>
                    </el-form-item>
                </el-col>
              </el-row>
            <el-row>
                <el-col :span="24">
                    <el-tabs v-model="data.activeName">
                        <el-tab-pane label="角色分配" name="role">
                            <el-transfer
                              v-model="data.checkRoles"
                              :data="data.roles"
                              filterable
                              :titles="['待选角色', '已选角色']"
                              :props="{
                                key: 'roleId',
                                label: 'roleName'
                              }"
                            />
                        </el-tab-pane>
                        <el-tab-pane label="组分配" name="group">
                          <el-transfer
                            v-model="data.checkGroups"
                            :props="{
                              key: 'groupId',
                              label: 'groupName'
                            }"
                            :data="data.groups"
                            filterable
                            :titles="['待选用户组', '已选用户组']"/>
                        </el-tab-pane>
                        <el-tab-pane label="功能分配" name="private">
                            <div style="height: 303px;overflow: auto">
                                <el-tree
                                        ref="tree"
                                        :data="data.menuTreeData"
                                        highlight-current
                                        show-checkbox
                                        :default-checked-keys="data.checkMenuData"
                                        :props="data.menuDefaultProps">
                                </el-tree>
                            </div>
                        </el-tab-pane>
                    </el-tabs>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button v-loading="data.loading" type="danger" @click="handleSubmit">保存</el-button>
          <el-button @click="handleCancel">取消</el-button>
        </div>
    </el-dialog>
</template>

<script lang="ts" setup>
import Api from '@/api/Management/api_sysaccount.js'
import ApiFunction from '@/api/Management/api_sysfunction.js'

import { reactive, watch, ref, defineProps, toRefs, onMounted} from 'vue'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
import {ElMessage, ElMessageBox} from "element-plus";

const store = useStore();
const router = useRouter()

// Data
const data = reactive({
    id: '',
    dialogVisible: false,
    appId: '1',
    menuDefaultProps: {
        parent: 'parentId',
        value: 'funId',
        label: 'funName',
        children: 'children'
    },
    allowTime: [],
    account: {},
    menuTreeData: [],
    checkMenuData: [],
    user: {
        userId: '',
        cn: '',
        sn: '',
        email: '',
        mobile: '',
        telephoneNumber: '',
        status: ''
    },
    allowWeek: ['1', '2', '3', '4', '5', '6'],
    loading: false,
    isEdit: false,
    activeName: 'role',
    checkRoles: [],
    roles: [],
    checkGroups: [],
    groups: [],
    rules: {}
})

// Methods
const init = (accountId) => {
    data.id = accountId;
    data.dialogVisible = true;
    getData(data.id);
    data.isEdit = true;
    getFunctionData();
}

const getFunctionData = () => {
    const params = {
        accountId: data.id,
        appId: data.appId
    }
    ApiFunction.listFunctionsByAccount(params).then(response => {
        console.log(response)
        if (response.code === 200){
            data.menuTreeData = response.data.menus;
            data.checkMenuData = response.data.checkMenuIds;
        }
    });
}

const getRoleData = () => {
    const params = {
        accountId: data.id,
        appId: data.appId
    }
    Api.getRoleByAccountId(params).then(response => {
        console.log(response)
        if (response.code === 200){
            data.checkRoles = response.data.checkRoles.map(role => role.roleId);
            data.roles = response.data.roles;
        }
    });
}

const getGroupData = () => {
    const params = {
        accountId: data.id,
        appId: data.appId
    }
    Api.listGroupByAccountId(params).then(response => {
        if (response.code === 200){
            const checkGroupIds = response.data.checkGroups.map(group => group.groupId);
            data.checkGroups = checkGroupIds;
            data.groups = response.data.groups;
        }
    });
}

const getData = (id) => {
    Api.getUserAndAccountByAccountId(id).then(response => {
        console.log(response)
        if (response.code === 200){
            data.user = response.data.user;
            data.account = response.data.account;
            getRoleData();
            getGroupData();
        }

    });
}

const handleCancel = () => {
    data.dialogVisible = false;
}

// 表单ref
const form = ref();
// 树形控件ref
const tree = ref();
const handleSubmit = () =>{
    form.value.validate(valid => {
        if (valid) {
            data.loading = true;
            const checkedNodes = tree.value.getCheckedNodes(false, true);
            const privateMenus = checkedNodes.map(node => {
                return node.funId
            });
            const postData = {
                accountId: data.id,
                checkRoles: data.checkRoles,
                checkGroups: data.checkGroups,
                privateMenus: privateMenus
            };
            Api.savePerm(postData).then(response => {
                if (response.code === 200){
                    ElMessage({
                        message: '保存成功',
                        type: 'success',
                    })
                }
                data.dialogVisible = false;
            });
            data.loading = false
        } else {
            return false
        }
    })
}

defineExpose({
    init,
});
</script>

<style lang="scss" scoped>
  .custom-tree-node {
    font-size: 14px;
  }

  .el-divider--horizontal {
    margin: 10px 0px 24px 0px;
  }
</style>
