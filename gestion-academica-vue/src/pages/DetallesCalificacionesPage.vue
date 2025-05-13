<script setup>
import { useRoute } from 'vue-router';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import DetallesCalificacionesComponent from '../components/DetallesCalificacionesComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent /> 
    <DetallesCalificacionesComponent :id=" route.params.id" />
    <FooterComponent />
</template>

<style></style>