<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useProfesroStore } from '../stores/profesorStore';
import { useRouter } from 'vue-router';
import { useCicloStore } from '../stores/cicloStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
})


const ciclo = reactive({
    nombre: '',
});
const cicloStore = useCicloStore();
const router = useRouter();
let data = null;
const regexp = /[\wñÑáéíóúÁÉÍÓÚ ]{5,}$/;

onMounted(async () => {
    data = await cicloStore.getCicloById(props.id);

    ciclo.nombre = data.nombre;
});

const handleSubmit = async () => {
    let pVacio = document.querySelector(".error");
    let pRegexp = document.getElementById("wrongRegexp");
    let pError = document.getElementById("error500");

    if (!ciclo.nombre) {
        pRegexp.style.display = "none";
        pError.style.display = "none";
        pVacio.style.display = "block";
        return;
    }
    else {
        if (!regexp.test(ciclo.nombre)) {
            pVacio.style.display = "none";
            pRegexp.style.display = "block";
            return;
        }
    }

    try {
        await cicloStore.updateCiclo(ciclo, props.id);
        router.push("/ciclos");
    }catch{
        pVacio.style.display = "none";
        pRegexp.style.display = "none";
        pError.style.display = "block";
    }

}

</script>

<template>
    <LeftArrowComponent path="ciclos" />

    <div class="card-container">
        <div class="card">
            <h2>Modificación de profesor</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre: </label>
                        <input type="text" v-model="ciclo.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>
            <p class="error" style="display: none;">Indique el nombre del ciclo, por favor</p>
            <p class="error" style="display: none;" id="error500">El ciclo que quiere modificar ya existe</p>
            <p class="error" style="display: none;" id="wrongRegexp">El nombre del ciclo debe tener mínimo 5 caracteres
                alfanuméricos</p>
        </div>
    </div>
</template>

<style scoped>
.grid-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(1, auto);
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
</style>