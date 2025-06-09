<script setup>
import { useRouter } from 'vue-router';
import { useModuloStore } from '../stores/moduloStore'
import LeftArrowComponent from './LeftArrowComponent.vue';
import { ref } from 'vue';
const moduloStore = useModuloStore();
const router = useRouter();
let modulos = ref(null);

const handleChange = (e) => {
    modulos.value = e.target.files[0];
}

const handleSubmit = async() => {
    let pVacio = document.getElementById("vacio");
    let pWrong = document.getElementById("wrongFile");
    let pError500 = document.getElementById("error500");
    
    if (!modulos.value){
        pWrong.style.display = "none";
        pError500.style.display = "none";
        pVacio.style.display = "block";
        return;
    }
    else if (modulos.value.type != "text/csv" && !modulos.value.name.toLowerCase().endsWith(".csv")){
        pVacio.style.display = "none";
        pError500.style.display = "none";
        pWrong.style.display = "block";
        return;
    }

    try{
        await moduloStore.saveModulo(modulos.value);
        router.push("/modulos");
    }
    catch{
        pVacio.style.display = "none";
        pWrong.style.display = "none";
        pError500.style.display = "block";
        return;
    }
}

const generateCSV = () => {
    const encabezado = ["nombreCiclo", "emailProfesor", "nombreModulo"];
    const contenidoCSV = encabezado.join(",")+ "\n";

    const blob = new Blob([contenidoCSV], {type: "text/csv;charset=utf-8"});
    const url = URL.createObjectURL(blob);

    const enlace = document.createElement("a");
    enlace.href = url;
    enlace.download = "altaModulos.csv";
    document.body.appendChild(enlace);
    enlace.click();
    document.body.removeChild(enlace);
}
</script>

<template>
    <LeftArrowComponent path="modulos" />

    <div class="card-container">
        <div class="card">
            <h2>Registro de módulos</h2>

            <div class="flex-container">
                <div>
                    <p>Asegúrate de que el archivo csv contenga la información en este orden: </p>
                    <hr>
                    <img src="../../registroModulos.png">
                    <p><button class="crear-editar-input excel" @click="generateCSV">Generar plantilla CSV</button></p>
                </div>
                <div>
                    <form @submit.prevent="handleSubmit">
                        <label for="file"></label>
                        <input type="file" id="file" @change="handleChange">
                        <p><button type="submit" class="crear-editar-input">Registrar</button></p>
                    </form>
                </div>
            </div>
            <p class="error" id="vacio" style="display: none;">Seleccione un archivo, por favor</p>
            <p class="error" id="wrongFile" style="display: none;">El archivo no tiene extensión .csv</p>
            <p class="error" id="error500" style="display: none;">El archivo no tiene todas las columnas y/o los emails no existen </p>
        </div>
    </div>
</template>

<style>
.flex-container {
    display: flex;
    flex-direction: row;
    gap: 5em;
}

.flex-container div{
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.error{
    margin-top: 1.22em;
}

form p{
    display: block;
    text-align: center;
    font-size: 1.1em;
}

.crear-editar-input{
    transition: background-color 0.3s ease;
}

.excel{
    transition: background-color 0.3s ease;
}

.excel:hover {
    background-color: #17d586;
}
</style>