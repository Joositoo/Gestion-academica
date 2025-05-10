<script setup>
import { useRoute } from 'vue-router';
import EditarCalificacionComponent from '../components/EditarCalificacionComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent />
    <EditarCalificacionComponent :id="route.params.id" /> 
    <FooterComponent />
</template>

<style scoped></style>