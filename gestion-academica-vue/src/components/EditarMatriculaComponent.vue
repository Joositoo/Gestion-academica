<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useMatriculaStore } from '../stores/matriculaStore';
import { useCicloStore } from '../stores/cicloStore';
import LeftArrowComponent from './LeftArrowComponent.vue';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: Number
})
let data = ref({});

const matriculaStore = useMatriculaStore();
const cicloStore = useCicloStore();
const router = useRouter();
const listaCiclos = ref([]);

onMounted(async () => {
    data.value = await matriculaStore.getMatriculaById(props.id);
    listaCiclos.value = await cicloStore.getCiclos();
    
    matricula.emailAlumno = data.value.alumnoDto.email;
    matricula.nombreCiclo = data.value.cicloDto.nombre;
    matricula.apellidosAlumno = data.value.alumnoDto.apellidos;
    matricula.nombreAlumno = data.value.alumnoDto.nombre
})

const matricula = reactive({
    emailAlumno: "",
    nombreCiclo: "",
    nombreAlumno: "",
    apellidosAlumno: ""
})

const enviaMatricula = reactive({
    emailAlumno: "",
    nombreCiclo: ""
})

const handleSubmit = async () => {
    enviaMatricula.emailAlumno = matricula.emailAlumno;
    enviaMatricula.nombreCiclo = matricula.nombreCiclo;

    await matriculaStore.updateMatricula(enviaMatricula, props.id);
    router.push("/matriculas");
}
</script>

<template>
    <LeftArrowComponent path="matriculas" />

<div class="card-container">
        <div class="card">
            <h2>Modificación de matrícula</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">

                    <div class="grid-item">
                        <label>Nombre del alumno: </label>
                        <p>{{ matricula.nombreAlumno }} {{ matricula.apellidosAlumno }}</p>
                    </div>
                    <div class="grid-item">
                        <label>Email del alumno: </label>
                        <p>{{ matricula.emailAlumno }}</p>
                    </div>
                    <div class="grid-item">
                        <label>Nombre del ciclo: </label>
                        <select  class="crear-editar-input" v-model="matricula.nombreCiclo">
                            <option v-for="ciclo in listaCiclos" :value="ciclo.nombre">{{ ciclo.nombre }}</option>
                        </select>
                    </div>
                    <div class="grid-item">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped></style>