<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useProfesroStore } from '../stores/profesorStore';

const props = defineProps({
    id: Number
});
const profesor = reactive({
    id: '',
    nombre: '',
    apellidos: '',
    email: '',
    rol: ''
})
const profesorStore = useProfesroStore();

onMounted(async () => {
    const data = await profesorStore.getProfesorById(props.id);
    profesor.id = data.id;
    profesor.nombre = data.nombre;
    profesor.apellidos = data.apellidos;
    profesor.email = data.email;
    profesor.rol = data.rol
});
</script>

<template>
    <h2>Detalles de {{ profesor.nombre }} {{ profesor.apellidos }}:</h2>

    <div class="card-container">
        <div class="card">
            <img src="../../avatar.png">

            <div class="grid">                
                <div>
                    <p><strong>ID:</strong> {{ profesor.id }}</p>
                </div>
                <div>
                    <p><strong>Nombre:</strong> {{ profesor.nombre }}</p>
                </div>
                <div>
                    <p><strong>Apellidos:</strong> {{ profesor.apellidos }}</p>
                </div>
                <div>
                    <p><strong>Email:</strong> {{ profesor.email }}</p>
                </div>
                <div>
                    <p><strong>Rol:</strong> {{ profesor.rol }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
h2 {
    margin: 2em 3em 1.5em 3em;
}

.card-container {
    display: flex;
    justify-content: center;
    margin-top: 2em;
}

.grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(3, 1fr);
    grid-column-gap: 0px;
    grid-row-gap: 0px;
    gap: 3em
}

img {
    width: 80px;
    height: auto;
    margin-bottom: 2.5em;
}
</style>