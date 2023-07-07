<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/">
        <router-link to="/">首页</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/ebook">
        <router-link to="/admin/ebook">电子书管理</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/category">
        <router-link to="/admin/category">分类管理</router-link>
      </a-menu-item>
      <a-menu-item key="/about">
        <router-link to="/about">关于我们</router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script lang="ts" setup>
import {defineProps, onMounted, ref} from 'vue';
import axios from "axios";

  type Props={
    title:string
  }
  defineProps<Props>();
  const ebook = ref()
  onMounted(() => {
    axios.get('http://localhost:8880/ebook/list1?name=Spring').then((res) => {
      const data = res.data
      ebook.value = data.content
    })
  })

</script>

<style scoped>

</style>