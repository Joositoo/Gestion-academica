<script setup>
import { useRoute } from 'vue-router';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import EditarAlumnoComponent from '../components/EditarAlumnoComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent />
    <EditarAlumnoComponent :id="route.params.id" />
    <FooterComponent />
</template>

<style></style>