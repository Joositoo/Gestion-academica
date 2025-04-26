<script setup>
import { useRoute } from 'vue-router';
import EditarProfesorComponent from '../components/EditarProfesorComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent />
    <EditarProfesorComponent :id="route.params.id" />
    <FooterComponent />
</template>

<style></style>