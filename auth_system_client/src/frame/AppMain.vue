<template>
    <router-view v-slot="{ Component }" :key="key">
        <keep-alive>
            <component :is="Component" />
        </keep-alive>
    </router-view>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import {onMounted} from "vue";
import { useStore } from "vuex";
import { useRouter } from 'vue-router'

const store = useStore();
const router = useRouter();

// Mounted
onMounted(() => {

})

const cachedViews = computed(() => {
    // return store.state.tagsView.cachedViews
})

const key = computed(() => {
    return router
})
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.fixed-header+.app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    min-height: calc(100vh - 84px);
  }

  .fixed-header+.app-main {
    padding-top: 84px;
  }
}
</style>

<style lang="scss">
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
