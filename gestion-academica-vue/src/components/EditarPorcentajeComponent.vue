<script setup>
import { onMounted, reactive, ref } from 'vue';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import { useModuloStore } from '../stores/moduloStore';
import LeftArrowComponent from './LeftArrowComponent.vue';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: Number
})

const porcentajeStore = usePorcentajeStore();
const router = useRouter();
const regExp = /^[\wñÑáéíóúÁÉÍÓÚ ]{2,}$/;

onMounted(async () => {
    const data = await porcentajeStore.getPorcentajeById(props.id);
    
    porcentaje.nombreModulo = data.modulo.nombre;
    porcentaje.descripcion = data.descripcion;
    porcentaje.nombre = data.nombre;
    porcentaje.porcentaje = data.porcentaje;
});



let porcentaje = reactive({
    nombreModulo: "",
    descripcion: "",
    nombre: "",
    porcentaje: null,
})

const handleSubmit = async () => {
    let pVacio = document.getElementById("vacio");
    let pError500 = document.getElementById("error500");
    let pNombre = document.getElementById("nombre");
    let pDescripcion = document.getElementById("descripcion");
    let pPorcentaje = document.getElementById("porcentaje");

    if (!porcentaje.nombreModulo || !porcentaje.descripcion || !porcentaje.nombre || porcentaje.porcentaje === null) {
        pVacio.style.display = "block";
        pError500.style.display = "none";
    }

    if (porcentaje.nombreModulo && porcentaje.descripcion && porcentaje.nombre && porcentaje.porcentaje != null){
        pVacio.style.display = "none";

        if (!regExp.test(porcentaje.descripcion)) {
            pDescripcion.style.display = "block";
        }

        if (!regExp.test(porcentaje.nombre)) {
            pNombre.style.display = "block";
        }

        if (porcentaje.porcentaje < 0 || porcentaje.porcentaje > 100) {
            pPorcentaje.style.display = "block";
        } else {
            pPorcentaje.style.display = "none";
        }

        if (!regExp.test(porcentaje.descripcion) || !regExp.test(porcentaje.nombre) || porcentaje.porcentaje < 0 || porcentaje.porcentaje > 100) {
            return;
        }
    }

    try {        
        await porcentajeStore.updatePorcentaje(porcentaje, props.id);
        router.push("/porcentajes");
    } catch {
        pVacio.style.display = "none";
        pError500.style.display = "block";
        return;
    }
}
</script>

<template>
    <LeftArrowComponent path="porcentajes" />

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
                        <label>Descripción del RA:</label>
                        <span class="error" style="display: none;" id="descripcion">Descripción con caracteres alfanuméricos</span>
                        <input type="text" class="crear-editar-input" v-model="porcentaje.descripcion" />
                    </div>
                    <div class="grid-item">
                        <label>Nombre del RA:</label>
                        <span class="error" style="display: none;" id="nombre">Nombre con caracteres alfanuméricos</span>
                        <input type="text" class="crear-editar-input" v-model="porcentaje.nombre" />
                    </div>
                    <div class="grid-item">
                        <label>Porcentaje:</label>
                        <span class="error" style="display: none;" id="porcentaje">Porcentaje comprendido entre 1 y 99</span>
                        <input type="text" class="crear-editar-input" v-model.number="porcentaje.porcentaje" />
                    </div>

                    <div class="grid-item full-width">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>

            <p class="error" style="display: none;" id="vacio">Rellene todos los campos, por favor</p>
            <p class="error" style="display: none;" id="error500">Ya existen los porcentajes para este módulo</p>
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