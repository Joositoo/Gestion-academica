<script setup>
import { useRoute } from 'vue-router';
import EditarCicloComponent from '../components/EditarCicloComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import HeaderComponent from '../components/HeaderComponent.vue';

const route = useRoute();;

</script>

<template>
    <HeaderComponent />
    <EditarCicloComponent :id="route.params.id" />
    <FooterComponent />
</template>

<script></script>