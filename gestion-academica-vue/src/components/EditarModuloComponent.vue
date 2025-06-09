<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useModuloStore } from '../stores/moduloStore';
import { useRouter } from 'vue-router';
import { useProfesroStore } from '../stores/profesorStore';
import { useCicloStore } from '../stores/cicloStore'
import LeftArrowComponent from './LeftArrowComponent.vue';

const moduloStore = useModuloStore();
const profesorStore = useProfesroStore();
const cicloStore = useCicloStore();
const router = useRouter();
let data = null;
const listaProfesores = ref([]);
const listaCiclos = ref([]);
const regExpNombre = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]{3,}$/

const props = defineProps({
    id: Number
})

onMounted(async () => {
    listaProfesores.value = await profesorStore.getProfesores();
    listaCiclos.value = await cicloStore.getCiclos();

    data = await moduloStore.getModuloById(props.id);

    modulo.nombreCiclo = data.cicloDto.nombre;
    modulo.emailProfesor = data.profesorDto.email;
    modulo.nombre = data.nombre;
})

const modulo = reactive({
    nombreCiclo: "",
    emailProfesor: "",
    nombre: ""
})

const handleSubmit = async () => {
    let pVacio = document.getElementById("vacio");
    let pError500 = document.getElementById("error500");

    if (!modulo.nombreCiclo || !modulo.nombre || !modulo.emailProfesor) {
        pError500.style.display = "none";
        pVacio.style.display = "block";
        return;
    }

    if (modulo.nombreCiclo && modulo.nombre && modulo.emailProfesor) {
        pVacio.style.display = "none";
        if (!regExpNombre.test(modulo.nombre)) {
            pVacio.style.display = "none";
            pError500.style.display = "block";

            return;
        }
    }

    try{
        await moduloStore.updateModulo(modulo, props.id);
        router.push("/modulos");
    }
    catch{
        pVacio.style.display = "none";
        pError500.style.display = "block";
    }
}
</script>

<template>
    <LeftArrowComponent path="modulos" />
    
    <div class="card-container">
        <div class="card">
            <h2>Modificación de módulo</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre del ciclo: </label>
                        <p>{{ modulo.nombreCiclo }}</p>
                    </div>
                    <div class="grid-item">
                        <label>Nombre del módulo: </label>
                        <input type="text" v-model="modulo.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Nombre del profesor: </label>
                        <select v-model="modulo.emailProfesor" class="editar-crear-input">
                            <option v-for="profesor in listaProfesores" :value="profesor.email">{{ profesor.nombre }} {{
                                profesor.apellidos }}</option>
                        </select>
                    </div>
                    <div class="grid-item">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>
            <p class="error" style="display: none;" id="vacio">Rellene todos los campos, por favor</p>
            <p class="error" style="display: none;" id="error500">Nombre del módulo con mínimo 3 caracteres alfabéticos
            </p>
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

p {
    margin-top: 1em;
}
</style>