<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useAlumnoStore } from '../stores/alumnoStore';
import { useProfesroStore } from '../stores/profesorStore';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: Number
})

let listaProfesores = ref([]);
const alumno = reactive({
    nombre: "",
    apellidos: "",
    email: "",
    emailProfesor: ""
})

const profesorStore = useProfesroStore();
const alumnoStore = useAlumnoStore();
const router = useRouter();
let data = null;

onMounted(async () => {
    data = await alumnoStore.getAlumnoById(props.id);
    listaProfesores.value = await profesorStore.getProfesores();

    alumno.nombre = data.nombre;
    alumno.apellidos = data.apellidos;
    alumno.email = data.email;
    alumno.emailProfesor = data.profesorDto.email;

    console.log(data);
    
})

const handleSubmit = async () => {
    if (!alumno.nombre || !alumno.apellidos || !alumno.email || !alumno.emailProfesor){
        let p = document.querySelector(".error");
        p.style.display = "block";
        return;
    }
    else{
        await alumnoStore.updateAlumno(alumno, props.id);
        router.push("/alumnos")
    }
}

</script>

<template>
<div class="card-container">
        <div class="card">
            <h2>Modificación de Alumno: </h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre: </label>
                        <input type="text" v-model="alumno.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Apellidos: </label>
                        <input type="text" v-model="alumno.apellidos" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Email: </label>
                        <input type="text" v-model="alumno.email" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Email del Profesor: </label>
                        <select v-model="alumno.emailProfesor">
                            <option v-for="profesor in listaProfesores" :value="profesor.email">{{ profesor.nombre }} {{ profesor.apellidos }}</option>
                        </select>
                    </div>
                    <div class="grid-item boton">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>
            <p class="error" style="display: none;">Rellene todos los campos, por favor</p>
        </div>
    </div>
</template>

<style scoped>
.grid-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(2, 150px);
    gap: 10px;
    padding: 10px;
}

.grid-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 1.2em;
    border-radius: 8px;
}

.boton {
    display: flex;
    justify-content: center;
    align-items: center; 
    grid-column: span 2; 
}
</style>