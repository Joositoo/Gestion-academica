<script setup>
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

import { onMounted, ref } from 'vue';
import { useCalificacionStore } from '../stores/calificacionStore';

const calificacionesStore = useCalificacionStore();

const calificaciones = ref([]);

onMounted(async () => {
    const data = await calificacionesStore.getCalificaciones();
    calificaciones.value = data;

    console.log(calificaciones);
})

</script>

<template>
    <HeaderComponent />
    <p>{{ calificaciones }}</p>
    <FooterComponent />
</template>

<style></style>