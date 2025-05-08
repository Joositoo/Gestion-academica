<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useModuloStore } from '../stores/moduloStore';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import { useRouter } from 'vue-router';

const moduloStore = useModuloStore();
const porcentajeStore = usePorcentajeStore();
const router = useRouter()
const listaModulos = ref([]);

let porcentaje = reactive({
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

onMounted(async () => {
    listaModulos.value = await moduloStore.getModulos();
});

const handleSubmit = async() => {
    let pVacio = document.getElementById("vacio");

    if (!porcentaje.nombreModulo || !porcentaje.ra1 || !porcentaje.ra2 || !porcentaje.ra3 || !porcentaje.ra4 || !porcentaje.ra5 || 
        !porcentaje.ra6 || !porcentaje.ra7 || !porcentaje.ra8 || !porcentaje.ra9){
        pVacio.style.display = "block";
        return;
    }

    await porcentajeStore.savePorcentaje(porcentaje);
    router.push("/porcentajes")
}

</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Registro de porcentajes</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre del módulo: </label>
                        <select v-model="porcentaje.nombreModulo">
                            <option value=""></option>
                            <option v-for="modulo in listaModulos">{{ modulo.nombre }}</option>
                        </select>
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 1 (RA 1):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra1" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 2 (RA 2):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra2" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 3 (RA 3):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra3" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 4 (RA 4):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra4" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 5 (RA 5):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra5" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 6 (RA 6):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra6" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 7 (RA 7):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra7" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 8 (RA 8):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra8" />
                    </div>
                    <div class="grid-item">
                        <label>Resultado de aprendizaje 9 (RA 9):</label>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.ra9" />
                    </div>

                    <div class="grid-item full-width">
                        <button type="submit" class="crear-editar-button">Registrar</button>
                    </div>
                </div>
            </form>
            <p class="error" style="display: none;" id="vacio">Rellene todos los campos, por favor</p>
        </div>
    </div>


</template>

<style scoped>
.grid-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(3, 150px);
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

h2 {
    margin: 1.5em 2.5em;
}

.full-width {
    grid-column: 1 / -1;
    display: flex;
    justify-content: center;
    margin-top: 0.8em;
}
</style>