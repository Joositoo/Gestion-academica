<script setup>
import { useUsuarioStore } from '../stores/usuarioStore';

const usuarioStore = useUsuarioStore();

const usuario = usuarioStore.usuario;

let isAdmin = false;
if (usuario.rol == "admin"){
    isAdmin = true;
}

const categorias = ["Profesores", "Alumnos", "Ciclos", "Módulos", "Matrículas", "Porcentajes", "Calificaciones"];
const categoriasProf = ["Porcentajes", "Calificaciones"];

const paths = ["/profesores", "/alumnos", "/ciclos", "/modulos", "/matriculas", "/porcentajes", "/calificaciones"];
const pathsProf = ["/porcentajes", "/calificaciones"];
</script>

<template>
    <div class="footer">
        <div v-if="!isAdmin" class="prof-container">      
            <router-link to="/indexprof">
                <img src="../../logo.png">
            </router-link>

            <div class="grid-container">
                    <p v-for="(categoria, index) in categoriasProf" :key="index">
                        <router-link :to="pathsProf[index]">
                            {{ categoria }}
                        </router-link>
                    </p>
                </div>
        </div>


        <div v-else>
            <div class="admin-container">
                    <router-link to="/indexadmin">
                    <img src="../../logo.png">
                </router-link>

                <div class="grid-container">
                    <p v-for="(categoria, index) in categorias" :key="index">
                        <router-link :to="paths[index]">
                            {{ categoria }}
                        </router-link>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.footer{
    background-color: #a9d4ff;
    border-top: 1px solid black;
    margin: 1em 0 0 0;
    padding: 4em 2em;
}

.admin-container,
.prof-container{
    display: flex;
    flex-direction: row;
}

.prof-container{
    align-items: center;
}

.grid-container{
    display: grid;
    grid-template-columns: repeat(3, 1fr); 
    grid-template-rows: repeat(3, auto);   
    gap: 0 15%;                             
    padding: 0 2em;
    margin-left: 8em;
}

p{
    margin: 0 6em
}
</style>