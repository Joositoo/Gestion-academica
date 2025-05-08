<script setup>
import { useRoute } from 'vue-router';
import EditarPorcentajeComponent from '../components/EditarPorcentajeComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent />
    <EditarPorcentajeComponent :id="route.params.id" />
    <FooterComponent />
</template>

<style scoped></style>