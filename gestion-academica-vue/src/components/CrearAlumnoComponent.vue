<script setup>
import { ref } from 'vue';
import { useAlumnoStore } from '../stores/alumnoStore';
import { useRouter } from 'vue-router';

const alumnoStore = useAlumnoStore();
const router = useRouter();
let alumnos = ref(null);

const handleChange = (e) => {
    alumnos.value = e.target.files[0];
}

const handleSubmit = async () => {
    let pVacio = document.getElementById("vacio");
    let pWrong = document.getElementById("wrongFile");

    if (!alumnos.value){
        pVacio.style.display = "block";
        return;
    }
    else if (alumnos.value.type != "text/csv" && !alumnos.value.name.toLowerCase().endsWith(".csv")){
        pVacio.style.display = "none";
        pWrong.style.display = "block";
        return;
    }

    await alumnoStore.saveAlumno(alumnos.value);
    router.push("/alumnos");
}
</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Registro de usuarios</h2>

            <div class="flex-container">
                <div>
                    <p>Asegúrate de que el archivo csv contenga la información en este orden: </p>
                    <hr>
                    <img src="../../registroAlumnos.png">
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
        </div>
    </div>
</template>

<style scoped>
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
</style>
