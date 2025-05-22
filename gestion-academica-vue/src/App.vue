


<script setup>
import { RouterView } from 'vue-router';
</script>

<template>
  <div id="app">
    <RouterView />
  </div>
</template>

<style>
html, body {
  height: 100%;
  margin: 0;
}

#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

#app > *:first-child {
  flex: 1;
}
</style>

