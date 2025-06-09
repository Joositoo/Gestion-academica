<script setup>
import { onMounted, reactive } from 'vue';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import { useModuloStore } from '../stores/moduloStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
});

const modulo = reactive({
    id: ''
});

const porcentajes = reactive({
    id: '',
    modulo: '',
    ciclo: '',
    nombreProfesor: '',
    emailProfesor: '',
    descripcion: "",
    nombre: "",
    porcentaje: null,
})

const porcentajeStore = usePorcentajeStore();
const moduloStore = useModuloStore();

onMounted(async () => {   
    const data = await porcentajeStore.getPorcentajeById(props.id);
    porcentajes.id = data.id;
    porcentajes.modulo = data.modulo.nombre;
    porcentajes.ciclo = data.modulo.cicloDto.nombre;
    porcentajes.nombreProfesor = data.modulo.profesorDto.nombre+ " " +data.modulo.profesorDto.apellidos;
    porcentajes.emailProfesor = data.modulo.profesorDto.email;
    porcentajes.descripcion = data.descripcion;
    porcentajes.nombre = data.nombre;
    porcentajes.porcentaje = data.porcentaje;
})
</script>

<template>
    <LeftArrowComponent path="porcentajes" />

    <h2>Detalles de los porcentajes de {{ porcentajes.modulo }}:</h2>

    <div class="card-container">
        <div class="card">
            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ porcentajes.id }}</p>
                </div>
                <div>
                    <p><strong>Modulo perteneciente al ciclo:</strong> {{ porcentajes.ciclo }}</p>
                </div>
                <div>
                    <p><strong>Porcentaje del módulo:</strong> {{ porcentajes.modulo }}</p>
                </div>
                <div>
                    <p><strong>Impartido por el profesor:</strong> {{ porcentajes.nombreProfesor }}</p>
                </div>
                <div>
                    <p><strong>Email del profesor:</strong> {{ porcentajes.emailProfesor }}</p>
                </div>
                <div>
                    <p><strong>Nombre del RA:</strong> {{ porcentajes.nombre }}</p>
                </div>
                <div>
                    <p><strong>Descripción:</strong> {{ porcentajes.descripcion }}</p>
                </div>
                <div>
                    <p><strong>Porcentaje asignado:</strong> {{ porcentajes.porcentaje }}%</p>
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
</style>