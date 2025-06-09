<script setup>
import { onMounted, reactive } from 'vue';
import { useCalificacionStore } from '../stores/calificacionStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
})

const calificaciones = reactive({
    id: '',
    modulo: '',
    ciclo: '',
    nombreProfesor: '',
    emailProfesor: '',
    nombreAlumno: '',
    emailAlumno: '',
    descripcionRa: "",
    nombreRa: "",
    porcentaje: null,
    nota: null,
    notaFinal: null
})


const calificacionesStore = useCalificacionStore();
onMounted(async () => {
    const data = await calificacionesStore.getCalificacionById(props.id);
    calificaciones.id = data.id;
    calificaciones.modulo = data.moduloDto.nombre;
    calificaciones.ciclo = data.moduloDto.cicloDto.nombre;
    calificaciones.nombreProfesor = data.moduloDto.profesorDto.nombre + " " + data.moduloDto.profesorDto.apellidos;
    calificaciones.emailProfesor = data.moduloDto.profesorDto.email;
    calificaciones.nombreAlumno = data.alumnoDto.nombre + " " + data.alumnoDto.apellidos;
    calificaciones.emailAlumno = data.alumnoDto.email;
    calificaciones.descripcionRa = data.porcentajesRaDto.descripcion;
    calificaciones.nombreRa = data.porcentajesRaDto.nombre;
    calificaciones.porcentaje = data.porcentajesRaDto.porcentaje;
    calificaciones.nota = data.nota;
    calificaciones.notaFinal = ((calificaciones.porcentaje / 100) * calificaciones.nota).toFixed(2);
})
</script>

<template>
    <LeftArrowComponent path="calificaciones" />

    <h2>Detalles de las calificaciones de {{ calificaciones.nombreAlumno }} en {{ calificaciones.modulo }}:</h2>

    <div class="card-container">
        <div class="card">
            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ calificaciones.id }}</p>
                </div>
                <div>
                    <p><strong>El módulo pertenece al ciclo:</strong> {{ calificaciones.ciclo }}</p>
                </div>
                <div>
                    <p><strong>Módulo:</strong> {{ calificaciones.modulo }}</p>
                </div>
                <div>
                    <p><strong>Módulo impartido por:</strong> {{ calificaciones.nombreProfesor }}</p>
                </div>
                <div>
                    <p><strong>Email del profesor:</strong> {{ calificaciones.emailProfesor }}</p>
                </div>
                <div>
                    <p><strong>Alumno:</strong> {{ calificaciones.nombreAlumno }}</p>
                </div>
                <div>
                    <p><strong>Email del alumno:</strong> {{ calificaciones.emailAlumno }}</p>
                </div>
                <div>
                    <p><strong>Descripción del RA:</strong> {{ calificaciones.descripcionRa }}</p>
                </div>
                <div>
                    <p><strong>Nombre del Ra:</strong> {{ calificaciones.nombreRa }}</p>
                </div>
                <div>
                    <p><strong>Porcentaje:</strong> {{ calificaciones.porcentaje }}%</p>
                </div>
                <div>
                    <p><strong>Nota: </strong>{{ calificaciones.nota }}</p>
                </div>
                <div>
                    <p><strong>Nota con el porcentaje aplicado:</strong> {{ calificaciones.notaFinal }}</p>
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

.full-width {
    grid-column: 1 / span 2;
    display: flex;
    justify-content: center;
}
</style>