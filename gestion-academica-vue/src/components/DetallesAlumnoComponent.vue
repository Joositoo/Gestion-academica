<script setup>
import { onMounted, reactive } from 'vue';
import { useAlumnoStore } from '../stores/alumnoStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
})

const alumno = reactive({
    id: '',
    nombre: '',
    apellidos: '',
    emailAlumno: '',
    nombreProfesor: '',
    emailProfesor: ''
})

const alumnoStore = useAlumnoStore();

onMounted(async () => {
    const data = await alumnoStore.getAlumnoById(props.id);
    alumno.id = data.id;
    alumno.nombre = data.nombre;
    alumno.apellidos = data.apellidos;
    alumno.emailAlumno = data.email;
    alumno.nombreProfesor = data.profesorDto.nombre + " " + data.profesorDto.apellidos;
    alumno.emailProfesor = data.profesorDto.email;

    console.log(alumno);

})

</script>

<template>
    <LeftArrowComponent path="alumnos" />

    <h2>Detalles de {{ alumno.nombre }} {{ alumno.apellidos }}:</h2>

    <div class="card-container">
        <div class="card">
            <img src="../../avatar.png">

            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ alumno.id }}</p>
                </div>
                <div>
                    <p><strong>Nombre:</strong> {{ alumno.nombre }}</p>
                </div>
                <div>
                    <p><strong>Apellidos:</strong> {{ alumno.apellidos }}</p>
                </div>
                <div>
                    <p><strong>Email:</strong> {{ alumno.emailAlumno }}</p>
                </div>
                <div>
                    <p><strong>Tutor:</strong> {{ alumno.nombreProfesor }}</p>
                </div>
                <div>
                    <p><strong>Email del tutor:</strong> {{ alumno.emailProfesor }}</p>
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