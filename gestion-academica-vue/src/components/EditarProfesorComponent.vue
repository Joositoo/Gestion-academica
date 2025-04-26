<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useProfesroStore } from '../stores/profesorStore';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: Number
})

const password = ref(null);
const profesor = reactive({
    nombre: '',
    apellidos: '',
    email: '',
    password: '',
    rol: ''
});
const profesorStore = useProfesroStore();
const router = useRouter();
let data = null

onMounted(async () => {
    data = await profesorStore.getProfesorById(props.id);

    profesor.nombre = data.nombre;
    profesor.apellidos = data.apellidos;
    profesor.email = data.email;
    profesor.password = data.password;
    profesor.rol = data.rol;
});

const handleSubmit = async () => {

if (!profesor.nombre || !profesor.apellidos || !profesor.email || !password.value || !profesor.rol){
    let p = document.querySelector(".error");
    p.style.display = "block";
    return;
}
else{
    profesor.password = password.value;
    
    await profesorStore.updateProfesor(profesor, props.id);
    router.push("/profesores");
}    
}

</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Modificación de profesor</h2>

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
                        <label>Contraseña nueva: </label>
                        <input type="text" v-model="password" class="crear-editar-input" value="" />
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

<style></style>