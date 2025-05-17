<script setup>
import { useUsuarioStore } from '../stores/usuarioStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const usuarioStore = useUsuarioStore();
const usuario = usuarioStore.usuario;

const categorias = ["Profesores", "Alumnos", "Ciclos", "Módulos"];
const categorias2 = ["Matrículas", "Porcentajes","Calificaciones"]

const imgs = ["profesor.png", "alumnos.png", "ciclo.png", "modulo.png"];
const imgs2 = ["matricula.png", "porcentaje.png","calificacion.png"];

const paths = ["/profesores", "/alumnos", "/ciclos", "/modulos"];
const paths2 = ["/matriculas", "/porcentajes","/calificaciones"];
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

    <div class="flex-container">
        <div class="card" v-for="(categoria, index) in categorias2" :key="index">
            <router-link :to="paths2[index]">
                <img :src="imgs2[index]">
                <p>{{ categoria }}</p>
            </router-link>
        </div>
    </div>
</template>

<style scoped>
.grid-container {
    display: grid;
    grid-template-columns: repeat(4, 1fr);  
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