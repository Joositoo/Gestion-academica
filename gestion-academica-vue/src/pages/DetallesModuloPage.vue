<script setup>
import { useRoute } from 'vue-router';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import DetallesModuloComponent from '../components/DetallesModuloComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent /> 
    <DetallesModuloComponent :id="route.params.id" />
    <FooterComponent /> 
</template>

<style scoped></style>