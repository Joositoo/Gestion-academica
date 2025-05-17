<script setup>
import { onMounted, reactive } from 'vue';
import { useMatriculaStore } from '../stores/matriculaStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
})

const matricula = reactive({
    id: '',
    nombreCiclo: '',
    tutor: '',
    emailTutor: '',
    nombreAlumno: '',
    apellidosAlumno: '',
    emailAlumno: '',
})

const matriculaStore = useMatriculaStore();

onMounted(async () => {
    const data = await matriculaStore.getMatriculaById(props.id);
    matricula.id = data.id;
    matricula.nombreCiclo = data.cicloDto.nombre;
    matricula.tutor = data.alumnoDto.profesorDto.nombre+ " " +data.alumnoDto.profesorDto.apellidos;
    matricula.emailTutor = data.alumnoDto.profesorDto.email;
    matricula.nombreAlumno = data.alumnoDto.nombre;
    matricula.apellidosAlumno = data.alumnoDto.apellidos;
    matricula.emailAlumno = data.alumnoDto.email;
    console.log(matricula);
    
});
</script>

<template>
    <LeftArrowComponent path="matriculas" />

    <h2>Detalles de la matrícula de {{ matricula.nombreAlumno }} {{ matricula.apellidosAlumno }}:</h2>
    <div class="card-container">
        <div class="card">
            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ matricula.id }}</p>
                </div>
                <div>
                    <p><strong>Ciclo matriculado:</strong> {{ matricula.nombreCiclo }}</p>
                </div>
                <div>
                    <p><strong>Nombre del alumno matriculado:</strong> {{ matricula.nombreAlumno }}</p>
                </div>
                <div>
                    <p><strong>Apellidos del alumno matriculado:</strong> {{ matricula.apellidosAlumno }}</p>
                </div>
                <div>
                    <p><strong>Email del alumno matriculado:</strong> {{ matricula.emailAlumno }}</p>
                </div>
                <div>
                    <p><strong>Tutor:</strong> {{ matricula.tutor }}</p>
                </div>
                <div>
                    <p><strong>Email del tutor:</strong> {{ matricula.emailTutor }}</p>
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