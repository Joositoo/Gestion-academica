<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useAlumnoStore } from '../stores/alumnoStore';
import { useProfesroStore } from '../stores/profesorStore';
import LeftArrowComponent from './LeftArrowComponent.vue';
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

const regExpNombreApellidos = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]{3,}$/;
const regExpEmail = /^[\wñÑ._%+-]{5,30}@gmail\.com$/;

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
    let pVacio = document.getElementById("vacio");
    let pEmail = document.getElementById("email");
    let pNombre = document.getElementById("nombre");
    let pApellidos = document.getElementById("apellido");
    let pError500 = document.getElementById("error500");

    if (!alumno.nombre || !alumno.apellidos || !alumno.email || !alumno.emailProfesor){
        pEmail.style.display = "none";
        pNombre.style.display = "none";
        pApellidos.style.display = "none";
        pError500.style.display = "none";
        pVacio.style.display = "block";
        return;
    }

    if (alumno.nombre && alumno.apellidos && alumno.email && alumno.emailProfesor){
        pVacio.style.display = "none";

        if (!regExpNombreApellidos.test(alumno.nombre)) {
            pNombre.style.display = "block";
        } else {
            pNombre.style.display = "none";
        }

        if (!regExpNombreApellidos.test(alumno.apellidos)) {
            pApellidos.style.display = "block";
        } else {
            pApellidos.style.display = "none";
        }

        if (!regExpEmail.test(alumno.email)) {
            pEmail.style.display = "block";
        } else {
            pEmail.style.display = "none";
        }

        if (!regExpNombreApellidos.test(alumno.nombre) || !regExpNombreApellidos.test(alumno.apellidos) || !regExpEmail.test(alumno.email)) {
            return;
        }
    }
    
    try{
        await alumnoStore.updateAlumno(alumno, props.id);
        router.push("/alumnos")
    }
    catch{
        pVacio.style.display = "none";
        pEmail.style.display = "none";
        pNombre.style.display = "none";
        pApellidos.style.display = "none";
        pError500.style.display = "block";
        return;
    }
}

</script>

<template>
    <LeftArrowComponent path="alumnos" />

<div class="card-container">
        <div class="card">
            <h2>Modificación de Alumno: </h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre: </label>
                        <span class="error" style="display: none;" id="nombre">Nombre solo con letras y
                            espacios</span>
                        <input type="text" v-model="alumno.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Apellidos: </label>
                        <span class="error" style="display: none;" id="apellido">Apellidos solo con letras y
                            espacios</span>
                        <input type="text" v-model="alumno.apellidos" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Email: </label>
                                                <span class="error" style="display: none;" id="email">Email incorrecto</span>
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
            <p class="error" style="display: none;" id="vacio">Rellene todos los campos, por favor</p>
            <p class="error" style="display: none;" id="error500">El email del alumno ya está registrado</p>
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