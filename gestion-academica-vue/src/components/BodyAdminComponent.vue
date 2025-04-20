<script setup>
import { useUsuarioStore } from '../stores/usuarioStore';

const usuarioStore = useUsuarioStore();
const usuario = usuarioStore.usuario;

const categorias = ["Profesores", "Alumnos", "Ciclos", "Módulos", "Matrículas", "Calificaciones"];
const imgs = ["profesor.png", "alumnos.png", "ciclo.png", "modulo.png", "matricula.png", "calificacion.png"];
const paths = ["/profesores", "/alumnos", "/ciclos", "/modulos", "/matriculas", "/calificaciones"];
</script>

<template>
    <h2>Bienvenido, {{ usuario.nombre }} {{ usuario.apellidos }}. &#128075; ¿Qué quieres hacer?</h2>

    <div class="grid-container">
        <div class="card" v-for="(categoria, index) in categorias" :key="index"> 
            <router-link :to="paths[index]">
                <img :src="'../../public/' +imgs[index]" >
                <p>{{ categoria }}</p>
            </router-link>
        </div>
    </div>
    

</template>

<style scoped>
.grid-container{
    display: grid;
    grid-template-columns: repeat(3, 1fr); 
    grid-template-rows: repeat(2, auto); ;
    gap: 80px;
    padding: 3em;;
    background-color: rgb(219, 233, 255);
    place-items: center;
}

h2{
    background-color: rgb(219, 233, 255);
    margin: 0;
    padding-top: 2em;
    padding-left: 2em;
}

img{
    width: 100px
}
</style>