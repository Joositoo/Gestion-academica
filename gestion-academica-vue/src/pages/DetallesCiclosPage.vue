<script setup>
import { useRoute } from 'vue-router';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import DetallesCicloComponent from '../components/DetallesCicloComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent /> 
    <DetallesCicloComponent :id="route.params.id" />
    <FooterComponent /> 
</template>

<style scoped></style>