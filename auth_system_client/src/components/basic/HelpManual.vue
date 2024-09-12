<template>
  <div title="帮助手册">
    <svg-icon icon-class="help" @click="click"/>
  </div>
</template>

<script>

  export default {
    name: 'HelpManual',
    methods: {
      click() {
        window.open('manual.pdf', '_blank');
      }
    }
  }
</script>

<style scoped>

</style>
