<script setup>
import router from '../router';

import { useUsuarioStore } from '../stores/usuarioStore';

const usuarioStore = useUsuarioStore();

const usuario = usuarioStore.usuario;

const handleLogin = () => {
    usuarioStore.logout();
    router.push("/")
}
</script>

<template>
    <h2>Información de {{ usuario.nombre }}:</h2>

    <div class="card-container">
        <div class="card">
            <div class="item">
                <img src="../../avatar.png">
                <p><b>Id: </b> {{ usuario.id }}</p>
            </div>

            <div class="item">
                <p><b>Nombre: </b> {{ usuario.nombre }}</p>
                <p><b>Apellidos: </b> {{ usuario.apellidos }}</p>
            </div>

            <div class="item">
                <p><b>Email: </b> {{ usuario.email }}</p>
                <p><b>Rol: </b> {{ usuario.rol }}</p>
            </div>

            <button @click="handleLogin">Cerrar sesión</button>
        </div>
    </div>
</template>

<style scoped>
.login-container {
    display: flex;
    justify-content: center;
    margin: 3em;
}

ul {
    list-style-type: none;
}

p {
    display: inline-block;
    margin: 1.5em 1.5em;
}

h1 {
    margin: 1.2em 2em;
    padding: 21.em;
}

button {
    color: #EF4444;
    background-color: rgba(49, 142, 201, 0.8);
    padding: 1em;
    border: none;
    border: 1px solid black;
    border-radius: 50px;
    font-size: 1.2em;
    margin-top: 1.2em;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #59c1ff;
    cursor: pointer;
}

img {
    width: 80px;
    height: auto;
}

.item {
    display: flex;
    flex-direction: row;
    margin: 0 1.5em;
    margin-bottom: 1.5em;
}
</style>