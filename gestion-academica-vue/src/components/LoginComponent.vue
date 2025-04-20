<script setup>
import { reactive, ref } from 'vue';
import { useUsuarioStore } from '../stores/usuarioStore';
import router from '../router';

const usuarioStore = useUsuarioStore();

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
    <div class="container">
        <div class="card">
            <img src="../../public/logo.png" class="logo">
            <form @submit.prevent="handleSubmit" id="loginForm">
                <input type="text" placeholder="email" v-model="usuario.email" />
                <input type="password" placeholder="contraseña" v-model="usuario.password" />
                <button type="submit">Iniciar sesión</button>

                <p class="error" style="display: none;" id="errorLogin">Rellene todos los campos, por favor</p>
                <p class="error" style="display: none;" id="errorCredentials">Credenciales incorrectas</p>
            </form>
        </div>
    </div>
</template>

<style scoped>
.container{
    margin: 0;
    background-color: rgb(219, 233, 255);
    display: flex;
    height: 100vh;
    justify-content: center;
    align-items: center;
    font-family: sans-serif;
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
    background-color: rgb(219, 233, 255);
    font-size: 1.2em;
}

button{
    background-color: rgba(49, 142, 201, 0.8);
}

button:hover{
    background-color: #59c1ff;
    cursor: pointer;
}

.logo{
    margin-bottom: 1.8em;
    width: 280px;
}

.error{
    color: #EF4444;
    font-size: 1.3em;
}
</style>