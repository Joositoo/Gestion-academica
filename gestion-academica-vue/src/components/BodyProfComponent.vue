<script setup>
import { useUsuarioStore } from '../stores/usuarioStore';

const usuarioStore = useUsuarioStore();
const usuario = usuarioStore.usuario;

const categorias = ["Porcentajes", "Calificaciones"];

const imgs = ["porcentaje.png", "calificacion.png",];

const paths = ["/porcentajes", "/calificaciones"];
</script>

<template>
    <h2>Bienvenido, {{ usuario.nombre }} {{ usuario.apellidos }}. &#128075; ¿Qué quieres hacer?</h2>

    <div class="grid-container">
        <div class="card" v-for="(categoria, index) in categorias" :key="index"> 
            <router-link :to="paths[index]">
                <img :src="imgs[index]" >
                <p>{{ categoria }}</p>
            </router-link>
        </div>
    </div>   

</template>

<style scoped>
.grid-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);  
    grid-template-rows: auto auto; 
    gap: 80px;
    padding: 3em;
    background-color: rgb(219, 233, 255);
    place-items: center;
}

.flex-container{
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    padding: 3em;
}

h2 {
    background-color: rgb(219, 233, 255);
    margin: 0;
    padding-top: 2em;
    padding-left: 2em;
}

img {
    width: 100px;
}

.grid-container > .card:nth-child(n+5) {
    grid-column: span 1; 
}
</style>