<template>
    <el-card style="margin-bottom:20px;">
        <div class="user-profile">
            <div class="box-center">
<!--                <pan-thumb :image="require('@/assets/profile.png')" :height="'100px'" :width="'100px'" :hoverable="false">-->
<!--                    <div>{{ name }}</div>-->
<!--                </pan-thumb>-->
            </div>
            <div class="box-center">
                <div class="user-name text-center">{{ name }}</div>
            </div>
        </div>

        <div class="user-bio">
            <div class="user-education user-bio-section">
                <div class="user-bio-section-header">
                    <svg-icon icon-class="user"/>
                    <span>登录用户</span>
                </div>
                <div class="user-bio-section-body">
                    <div class="text-muted">
                        <el-row :gutter="10">
                            <el-col :span="24" class="margin5"><span class="info-item">用户标识</span><span class="maohao">:</span>{{ data.user.userId }}</el-col>
                            <el-col :span="24" class="margin5"><span class="info-item">用户名</span><span class="maohao">:</span>{{ data.user.cn }}</el-col>
<!--                            <el-col :span="24" class="margin5"><span class="info-item">状态</span><span class="maohao">:</span>{{ status(user.status) }}</el-col>-->
<!--                            <el-col :span="24" class="margin5"><span class="info-item">用户类型</span><span class="maohao">:</span>{{ employeeType(user.employeeType) }}</el-col>-->
                            <div v-for="field in data.fields">
                                <el-col :key="field.fieldId" :span="24" class="margin5"><span class="info-item">{{ field.displayName }}</span><span class="maohao">:</span>{{ field.value }}</el-col>
                            </div>
                            <div v-if="data.user.employeeType==='1'">
                                <el-col :span="24" class="margin5"><span class="info-item">公司名称</span><span class="maohao">:</span>{{ data.user.companyName }}</el-col>
                                <el-col :span="24" class="margin5"><span class="info-item">部门名称</span><span class="maohao">:</span>{{ data.user.orgName }}</el-col>
                                <el-col :span="24" class="margin5"><span class="info-item">联系方式</span><span class="maohao">:</span>{{ data.user.mobile }}</el-col>
                                <el-col :span="24" class="margin5"><span class="info-item">邮箱</span><span class="maohao">:</span>{{ data.user.email }}</el-col>
                            </div>
                            <div v-if="data.user.employeeType==='2'">
                                <el-col :span="24" class="margin5"><span class="info-item">公司名称</span><span class="maohao">:</span>{{ data.user.supporterCorpName }}</el-col>
                                <el-col :span="24" class="margin5"><span class="info-item">部门名称</span><span class="maohao">:</span>{{ data.user.supporterDept }}</el-col>
                                <el-col :span="24" class="margin5"><span class="info-item">公司联系人</span><span class="maohao">:</span>{{ data.user.supporterCorpContact }}</el-col>
                                <el-col :span="24" class="margin5"><span class="info-item">负责人账号</span><span class="maohao">:</span>{{ data.user.supervisor }}</el-col>
                                <el-col :span="24" class="margin5"><span class="info-item">外部用户类型</span><span class="maohao">:</span>{{ data.user.post }}</el-col>
                            </div>
                        </el-row>
                    </div>
                </div>
            </div>
        </div>
    </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref, defineProps, toRefs, onMounted, computed} from 'vue'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
import PanThumb from '@/components/PanThumb/index.vue'
// import {getUserApi} from '@/api/user'
// import {getUserFieldsApi} from '@/api/user-field-config'
import {mapGetters} from 'vuex'

const store = useStore();
const router = useRouter()

// Data
const data = reactive({
    fields: [],
    user: {},
    appId:'1',
    userId:'1'
})

// Props
const props = defineProps({

})

// Mounted
onMounted(() => {
    fetchData(data.userId);
    fetchUserFieldsData(data.appId, data.userId);
})

// Computed
let testComputed = computed<number>({
    //1.get是读取
    //2.当testComputed 依赖的值改变时，就会触发get方法，
    get() {
        return number1.value + number2.value;
    },
    //1.set是修改
    //2.当改变整个testComputed时，才会触发set方法，如果testComputed是对象改变对象的某个属性的值时，不会触发set方法
    //3.返回值val可以赋值给其他，不可以赋值给自己
    set(val) {
        console.log('触发了set', val);
    }
});


const fetchData = (userId) => {
    // getUserApi(userId).then(response => {
    //     user = response.data;
    // });
}

const fetchUserFieldsData = (appId, userId) => {
    // getUserFieldsApi(appId, userId).then(response => {
    //     fields = response.data;
    // });
}

// todo 计算属性
const status = (status) => {
    let str = '';
    if (status === '0') {
        str += '正常状态';
    } else if (status === '1') {
        str += '锁定状态';
    } else if (status === '2') {
        str += '删除状态';
    }
    return str;
}

const employeeType = (employeeType) => {
    let str = '';
    if (employeeType === '1') {
        str += '内部用户';
    } else if (employeeType === '2') {
        str += '外部用户';
    }
    return str;
}
</script>

<style lang="scss" scoped>
  .box-center {
    margin: 0 auto;
    display: table;
  }

  .text-muted {
    color: #777;
    font-size: 13px;
  }

  .info-item {
    display: inline-block;
    width: 85px;
    text-align: justify;
    text-align-last: justify;
    font-weight: bold;
  }

  .maohao {
    font-weight: bold;
    padding-right: 5px;
  }

  .margin5 {
    margin: 5px 0px;
  }

  .user-profile {
    .user-name {
      font-weight: bold;
    }

    .box-center {
      padding-top: 10px;
    }

    .user-role {
      padding-top: 10px;
      font-weight: 400;
      font-size: 14px;
    }

    .box-social {
      padding-top: 30px;

      .el-table {
        border-top: 1px solid #dfe6ec;
      }
    }

    .user-follow {
      padding-top: 20px;
    }
  }

  .user-bio {
    margin-top: 20px;
    color: #606266;
    height: calc(100vh - 296px);

    span {
      padding-left: 4px;
    }

    .user-bio-section {
      font-size: 14px;
      padding: 15px 0;

      .user-bio-section-header {
        border-bottom: 1px solid #dfe6ec;
        padding-bottom: 10px;
        margin-bottom: 10px;
        font-weight: bold;
      }
    }
  }
</style>
