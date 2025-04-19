<script setup>
import { reactive, ref } from 'vue';
import { useUsuarioStore } from '../stores/usuarioStore';
import router from '../router';

const usuarioStore = useUsuarioStore();

const usuarioIniciado = ref(null);
const usuario = reactive({
email: "",
password: ""
})

const handleSubmit = async () => {
let p = document.getElementById("errorLogin");
let pCredentials = document.getElementById("errorCredentials");

if (!usuario.email || !usuario.password){
    p.style.display = "block";
    return;
}

try{
    const usuarioIniciado = await usuarioStore.getUsuario(usuario);
    console.log("Usuario logueado: ", usuarioIniciado);

    redirecciona(usuarioIniciado);
}
catch (error){
    p.style.display = "none";
    pCredentials.style.display = "block";
}

function redirecciona(usuarioIniciado) {
    if (usuarioIniciado.rol == "profesor"){
        router.push("/indexprof");
    }
    else{
        router.push("/indexadmin");
    }
}
}

</script>

<template>
    <div class="card">
        <form @submit.prevent="handleSubmit" id="loginForm">
            <input type="text" placeholder="usuario" v-model="usuario.email" />
            <input type="text" placeholder="contraseña" v-model="usuario.password" />
            <button type="submit">Iniciar sesión</button>

            <p class="error" style="display: none;" id="errorLogin">Rellene todos los campos, por favor</p>
            <p class="error" style="display: none;" id="errorCredentials">Credenciales incorrectas</p>
        </form>

        <p v-if="usuarioIniciado">{{ usuarioIniciado }}</p>
    </div>
</template>

<style>
.card{
    border: 1px solid white;
    border-radius: 2em;
    padding: 4em;
    box-shadow: 10px 10px 10px rgb(255, 255, 255, 0.5);
}

form{
    display: flex;
    flex-direction: column;
    gap: 2em;
    align-items: center;
}

input,
button{
    width: 25rem;
    padding: 1em;
    border: none;
    border: 1px solid black;
    border-radius: 50px;
}

.error{
    color: red
}
</style>