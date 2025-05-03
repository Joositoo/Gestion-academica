<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useModuloStore } from '../stores/moduloStore';
import { useRouter } from 'vue-router';
import { useProfesroStore } from '../stores/profesorStore';
import { useCicloStore } from '../stores/cicloStore'

const moduloStore = useModuloStore();
const profesorStore = useProfesroStore();
const cicloStore = useCicloStore();
const router = useRouter();
let data = null;
const listaProfesores = ref([]);
const listaCiclos = ref([]);

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
    if (!modulo.nombreCiclo || !modulo.nombre || !modulo.emailProfesor){
        let p = document.querySelector(".error");
        p.style.display = "block";
        return;
    }

    await moduloStore.updateModulo(modulo, props.id);
    router.push("/modulos");
}
</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Modificación de módulo</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre del ciclo: </label>
                        <select v-model="modulo.nombreCiclo">
                            <option v-for="ciclo in listaCiclos" :value="ciclo.nombre">{{ ciclo.nombre }}</option>
                        </select>
                    </div>
                    <div class="grid-item">
                        <label>Nombre del módulo: </label>
                        <input type="text" v-model="modulo.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Email del profesor: </label>
                        <select v-model="modulo.emailProfesor" class="editar-crear-input">
                            <option v-for="profesor in listaProfesores" :value="profesor.email">{{ profesor.nombre }} {{ profesor.apellidos }}</option>
                        </select>
                    </div>
                    <div class="grid-item">
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
    /*background-color: #b0c4de;*/
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 1.2em;
    border-radius: 8px;
}
</style>