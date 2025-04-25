<script setup>
import { reactive } from 'vue';
import { useProfesroStore } from '../stores/profesorStore';
import router from '../router';

let profesorStore = useProfesroStore();

let profesor = reactive({
    nombre: "",
    apellidos: "",
    email: "",
    password: "",
    rol: ""
})

const handleSubmit = async() => {
    if (!profesor.nombre || !profesor.apellidos || !profesor.email || !profesor.password || !profesor.rol){
        let p = document.querySelector(".error");
        p.style.display = "block";
        return;
    }
    await profesorStore.saveProfesor(profesor);
    await profesorStore.getProfesores();
    router.push("/profesores");
}
</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Registro de profesor</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre: </label>
                        <input type="text" v-model="profesor.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Apellidos: </label>
                        <input type="text" v-model="profesor.apellidos" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Email: </label>
                        <input type="text" v-model="profesor.email" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Contraseña: </label>
                        <input type="text" v-model="profesor.password" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Rol: </label>
                        <select v-model="profesor.rol">
                            <option value="profesor">Profesor</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>
                    <div class="grid-item">
                        <button type="submit" class="crear-editar-button">Registrar</button>
                    </div>
                </div>
            </form>
            <p class="error" style="display: none;">Rellene todos los campos, por favor</p>
        </div>
    </div>
</template>

<style>
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
</style>