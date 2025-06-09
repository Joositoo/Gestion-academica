<script setup>
import { useUsuarioStore } from '../stores/usuarioStore';

const usuarioStore = useUsuarioStore();

const usuario = usuarioStore.usuario;

let isAdmin = false;

if (usuario.rol == "admin"){
    isAdmin = true;
}
</script>

<template>
    <div class="header">
        <div v-if="isAdmin">
            <router-link to="/indexadmin">
                <img src="../../logo.png">
            </router-link>
        </div>
        <div v-else>
            <router-link to="/indexprof">
                <img src="../../logo.png">
            </router-link>
        </div>
            
        <div class="flex-item">
            <router-link to="/account">
                <img v-if="!usuario.img" src="../../iconoUsuario.png" class="iconoUsuario">
                <img v-else :src="`data:image/png;base64,${usuario.img}`" class="iconoUsuario rounded">
            </router-link>
        </div>
    </div>
</template>

<style scoped>
.header{
    background-color: #a9d4ff;
    padding: 2em;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    border-bottom: 1px solid black;
}

.iconoUsuario{
    width: 4em;
    height: auto;
}

.flex-item{
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>