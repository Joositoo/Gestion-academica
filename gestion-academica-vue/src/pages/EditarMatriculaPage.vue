<script setup>
import { useRoute } from 'vue-router';
import EditarMatriculaComponent from '../components/EditarMatriculaComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

const route = useRoute();


</script>

<template>
    <HeaderComponent /> 
    <EditarMatriculaComponent :id="route.params.id" /> 
    <FooterComponent />
</template>

<style scoped></style>