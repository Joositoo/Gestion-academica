<script setup>
import { onMounted, reactive } from 'vue';
import { useModuloStore } from '../stores/moduloStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
});

const modulo = reactive({
    id: '',
    nombre: '',
    nombreCiclo: '',
    nombreProfesor: '',
    emailProfesor: ''
});

const moduloStore = useModuloStore();

onMounted(async () => {
    const data = await moduloStore.getModuloById(props.id);
    modulo.id = data.id;
    modulo.nombre = data.nombre;
    modulo.nombreCiclo = data.cicloDto.nombre;
    modulo.nombreProfesor = data.profesorDto.nombre+ " " +data.profesorDto.apellidos;
    modulo.emailProfesor = data.profesorDto.email;
});
</script>

<template>
    <LeftArrowComponent path="modulos" />

    <h2>Detalles del módulo {{ modulo.nombre }}:</h2>

    <div class="card-container">
        <div class="card">
            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ modulo.id }}</p>
                </div>
                <div>
                    <p><strong>Nombre del módulo:</strong> {{ modulo.nombre }}</p>
                </div>
                <div>
                    <p><strong>Pertenece al ciclo:</strong> {{ modulo.nombreCiclo }}</p>
                </div>
                <div>
                    <p><strong>Impartido por el profesor:</strong> {{ modulo.nombreProfesor }}</p>
                </div>
                <div>
                    <p><strong>Email del profesor:</strong> {{ modulo.emailProfesor }}</p>
                </div>
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
    grid-template-rows: repeat(3, 1fr);
    grid-column-gap: 0px;
    grid-row-gap: 0px;
    gap: 3em
}

img {
    width: 80px;
    height: auto;
    margin-bottom: 2.5em;
}
</style>