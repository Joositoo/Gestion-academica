<script setup>
import { onMounted, reactive, ref } from 'vue';
import LeftArrowComponent from './LeftArrowComponent.vue';
import { useProfesroStore } from '../stores/profesorStore';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: Number
})

const regExpNombreApellidos = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ ]{3,}$/;
const regExpEmail = /^[\wñÑ._%+-]{5,30}@gmail\.com$/;
const regExpPassword = /^[\wñÑ.-]{5,}$/;

const password = ref(null);
const profesor = reactive({
    nombre: '',
    apellidos: '',
    email: '',
    password: '',
    rol: '',
    img: null
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
    let pVacio = document.getElementById("vacio");
    let pEmail = document.getElementById("email");
    let pNombre = document.getElementById("nombre");
    let pApellidos = document.getElementById("apellidos");
    let pPassword = document.getElementById("password");
    let pError500 = document.getElementById("error500");

    if (!profesor.nombre || !profesor.apellidos || !profesor.email || !password.value || !profesor.rol) {
        let p = document.querySelector(".error");
        pVacio.style.display = "block";
        return;
    }

    if (profesor.nombre && profesor.apellidos && profesor.email && profesor.password && profesor.rol) {
        pVacio.style.display = "none";

        if (!regExpNombreApellidos.test(profesor.nombre)) {
            pNombre.style.display = "block";
        } else {
            pNombre.style.display = "none";
        }

        if (!regExpNombreApellidos.test(profesor.apellidos)) {
            pApellidos.style.display = "block";
        } else {
            pApellidos.style.display = "none";
        }

        if (!regExpEmail.test(profesor.email)) {
            pEmail.style.display = "block";
        } else {
            pEmail.style.display = "none";
        }

        if (!regExpPassword.test(password.value)) {
            pPassword.style.display = "block";
        } else {
            pPassword.style.display = "none";
        }

        if (!regExpNombreApellidos.test(profesor.nombre) || !regExpNombreApellidos.test(profesor.apellidos) || !regExpEmail.test(profesor.email) || !regExpPassword.test(password.value)) {
            return;
        }
    }

    try {
        profesor.password = password.value;

        await profesorStore.updateProfesor(profesor, props.id);
        router.push("/profesores");
    }
    catch {
        pVacio.style.display = "none";
        pNombre.style.display = "none";
        pApellidos.style.display = "none";
        pEmail.style.display = "none";
        pPassword.style.display = "none";
        pError500.style.display = "block";
        return;
    }
}

const handlePasswordVisualization = () => {
    const seePasswordIcon = document.querySelector("#seePassword i");
    const passwordInput = document.getElementById("passwordInput");

    if (passwordInput.type == "password") {
        passwordInput.type = "text";
        seePasswordIcon.classList.remove("bi-eye-slash");
        seePasswordIcon.classList.add("bi-eye");
    }
    else {
        passwordInput.type = "password";
        seePasswordIcon.classList.remove("bi-eye");
        seePasswordIcon.classList.add("bi-eye-slash");
    }
}
</script>

<template>
    <LeftArrowComponent path="profesores" />

    <div class="card-container">
        <div class="card">
            <h2>Modificación de profesor</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre: </label>
                        <span class="error" style="display: none;" id="nombre">Nombre solo con letras y
                            espacios</span>
                        <input type="text" v-model="profesor.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Apellidos: </label>
                        <span class="error" style="display: none;" id="apellidos">Apellido solo con letras
                            y espacios</span>
                        <input type="text" v-model="profesor.apellidos" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Email: </label>
                        <span class="error" style="display: none;" id="email">Email incorrecto</span>
                        <input type="text" v-model="profesor.email" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <label>Contraseña nueva: </label>
                        <span class="error" style="display: none;" id="password">Contraseña con mínimo 5 caracteres
                            alfanuméricos</span>
                        <div class="password-wrapper">
                            <input type="password" id="passwordInput" v-model="password" class="crear-editar-input" />
                            <span id="seePassword" @click="handlePasswordVisualization">
                                <i class="bi bi-eye-slash"></i>
                            </span>
                        </div>
                    </div>
                    <div class="grid-item">
                        <label>Rol: </label>
                        <select v-model="profesor.rol">
                            <option value="profesor">Profesor</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>

                    <div class="grid-item">
                        <label>Foto de perfil: </label>
                        <input type="file" @change="e => profesor.img = e.target.files[0]" class="crear-editar-input">
                    </div>

                    <div class="grid-item boton-centro">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>
            <p class="error" style="display: none;" id="vacio">Rellene todos los campos, por favor</p>
            <p class="error" style="display: none;" id="error500">Asegúrese de que el email no esté dado de alta</p>
        </div>
    </div>
</template>

<style scoped>
.password-wrapper {
    position: relative;
    display: flex;
    align-items: center;
    margin: 1em 1.5em;
}

.password-wrapper input {
    flex: 1;
    padding-right: 2.5em;
}

.password-wrapper span {
    position: absolute;
    right: 2em;
    cursor: pointer;
    color: #333;
    font-size: 1.2em;
}

.boton-centro {
    grid-column: 1 / -1; 
    display: flex;
    justify-content: center;
}
</style>