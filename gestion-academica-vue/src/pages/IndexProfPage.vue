<script setup>
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';
import CalificacionesComponent from '../components/CalificacionesComponent.vue';
import { onMounted, ref } from 'vue';
import { useCalificacionStore } from '../stores/calificacionStore';
import { useUsuarioStore } from '../stores/usuarioStore';
import BodyProfComponent from '../components/BodyProfComponent.vue';

const usuarioStore = useUsuarioStore();
const usuario = usuarioStore.usuario;

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
    <BodyProfComponent />
    <FooterComponent />
</template>

<style></style>