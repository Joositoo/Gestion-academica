<script setup>
import { useRoute } from 'vue-router';
import EditarModuloComponent from '../components/EditarModuloComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent />
    <EditarModuloComponent :id="route.params.id"/>
    <FooterComponent />
</template>

<style></style>