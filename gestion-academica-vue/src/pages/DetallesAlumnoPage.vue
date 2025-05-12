<script setup>
import { useRoute } from 'vue-router';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import DetallesAlumnoComponent from '../components/DetallesAlumnoComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent /> 
    <DetallesAlumnoComponent :id="route.params.id" />
    <FooterComponent /> 
</template>

<style scoped></style>