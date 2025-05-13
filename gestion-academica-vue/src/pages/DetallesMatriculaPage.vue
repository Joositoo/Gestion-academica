<script setup>
import { useRoute } from 'vue-router';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import DetallesMatriculaComponent from '../components/DetallesMatriculaComponent.vue';

const route = useRoute();
</script>

<template>
    <HeaderComponent />
    <DetallesMatriculaComponent :id="route.params.id" />
    <FooterComponent />
</template>

<style></style>