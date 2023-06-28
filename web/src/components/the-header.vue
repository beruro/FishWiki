<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">朝天门</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script lang="ts" setup>
import {defineProps, onMounted} from 'vue';
import axios from "axios";

  type Props={
    title:string
  }
  defineProps<Props>();
  onMounted(() => {
    axios.get('http://localhost:8880/ebook/list1?name=Spring').then((res) => {
      console.log(res)
    })
  })

</script>

<style scoped>

</style>