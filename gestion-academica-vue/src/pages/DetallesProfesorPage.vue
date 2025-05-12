<script setup>
import { useRoute } from 'vue-router';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import DetallesProfesorComponent from '../components/DetallesProfesorComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent />
    <DetallesProfesorComponent :id="route.params.id" />
    <FooterComponent />
</template>

<style scoped></style>