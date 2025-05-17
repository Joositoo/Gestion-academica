<script setup>
import { onMounted, reactive } from 'vue';
import { useCicloStore } from '../stores/cicloStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
});

const ciclo = reactive({
    id: '',
    nombre: ''
});

const cicloStore = useCicloStore();

onMounted(async () => {
    const data = await cicloStore.getCicloById(props.id);
    ciclo.id = data.id;
    ciclo.nombre = data.nombre;
});
</script>

<template>
    <LeftArrowComponent path="ciclos" />

    <h2>Detalles del ciclo {{ ciclo.nombre }}:</h2>

    <div class="card-container">
        <div class="card">
            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ ciclo.id }}</p>
                </div>
                <div><p><strong>Nombre: </strong>{{ ciclo.nombre }}</p></div>
            </div>
        </div>
    </div>
</template>

<style scoped>
h2 {
    margin: 2em 3em 1.5em 3em;
}

.card-container {
    display: flex;
    justify-content: center;
    margin-top: 2em;
}

.grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(1, 1fr);
    grid-column-gap: 0px;
    grid-row-gap: 0px;
    gap: 3em
}
</style>