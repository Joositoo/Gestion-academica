<script setup>
import { useRoute } from 'vue-router';
import DetallesPorcentajesComponent from '../components/DetallesPorcentajesComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

const route = useRoute();

</script>

<template>
    <HeaderComponent />
    <DetallesPorcentajesComponent :id="route.params.id" />
    <FooterComponent />
</template>

<style scoped></style>