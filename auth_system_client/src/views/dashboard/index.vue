<template>
  <div class="app-container">
    <div style="width: 300px;float: left;margin-right: 20px">
      <user-card/>
    </div>
    <div>
      <user-overview height="312px" id="userChart"/>
      <login-overview height="312px" id="loginChart"/>
    </div>
  </div>
</template>

<script lang="ts" setup>
import UserCard from './components/UserCard.vue'
import UserOverview from './components/UserOverview.vue'
import LoginOverview from './components/LoginOverview.vue'
import { reactive, ref, defineProps, toRefs, onMounted} from 'vue'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'

const store = useStore();
const router = useRouter()

// Data
const data = reactive({
    activeTab: 'role'
})

</script>
<style lang="scss" scoped>

</style>

