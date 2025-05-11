<script setup>
import { onMounted, reactive, ref } from 'vue';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: Number
})
let data = ref({});

const porcentajeStore = usePorcentajeStore();
const router = useRouter();

onMounted(async () => {
    data.value = await porcentajeStore.getPorcentajeById(props.id);

    porcentaje.nombreModulo = data.value.nombreModulo;
    porcentaje.ra1 = data.value.ra1;
    porcentaje.ra2 = data.value.ra2;
    porcentaje.ra3 = data.value.ra3;
    porcentaje.ra4 = data.value.ra4;
    porcentaje.ra5 = data.value.ra5;
    porcentaje.ra6 = data.value.ra6;
    porcentaje.ra7 = data.value.ra7;
    porcentaje.ra8 = data.value.ra8;
    porcentaje.ra9 = data.value.ra8;
});

const porcentaje = reactive({
    nombreModulo: "",
    ra1: "",
    ra2: "",
    ra3: "",
    ra4: "",
    ra5: "",
    ra6: "",
    ra7: "",
    ra8: "",
    ra9: "",
})

const handleSubmit = async () => {
    let pVacio = document.getElementById("vacio");

    if (!porcentaje.nombreModulo || !porcentaje.ra1 || !porcentaje.ra2 || !porcentaje.ra3 || !porcentaje.ra4 || !porcentaje.ra5 ||
        !porcentaje.ra6 || !porcentaje.ra7 || !porcentaje.ra8 || !porcentaje.ra9) {
        pVacio.style.display = "block";
        return;
    }

    await porcentajeStore.updatePorcentaje(porcentaje, props.id);
    router.push("/porcentajes");
}
</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Modificación de porcentajes</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">

                    <div class="grid-item">
                        <label>Nombre del módulo: </label>
                        <p>{{ porcentaje.nombreModulo }}</p>
                    </div>

                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 1 (RA 1):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra1" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 2 (RA 2):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra2" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 3 (RA 3):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra3" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 4 (RA 4):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra4" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 5 (RA 5):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra5" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 6 (RA 6):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra6" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 7 (RA 7):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra7" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 8 (RA 8):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra8" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de Aprendizaje 9 (RA 9):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra9" />
                    </div>

                    <div class="grid-item full-width">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>

            <p class="error" style="display: none;" id="vacio">Rellene todos los campos, por favor</p>
        </div>
    </div>
</template>

<style>
.full-width {
    grid-column: 1 / -1;
    display: flex;
    justify-content: center;
    margin-top: 0.8em;
}
</style>