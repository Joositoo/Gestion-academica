<script setup>
import { useUsuarioStore } from '../stores/usuarioStore';

const usuarioStore = useUsuarioStore();

const usuario = usuarioStore.usuario;

let isAdmin = false;
if (usuario.rol == "admin"){
    isAdmin = true;
}
console.log(isAdmin)
const categorias = ["Profesores", "Alumnos", "Ciclos", "Módulos", "Matrículas", "Calificaciones"];
const paths = ["/profesores", "/alumnos", "/ciclos", "/modulos", "/matriculas", "/calificaciones"];
</script>

<template>
    <div class="footer">
        <div v-if="isAdmin">            //MODIFICAR CONDICIÓN PARA QUE SE MUESTRE CORRECTAMENTE
            <router-link to="/indexprof">
                <img src="../../public/logo.png">
            </router-link>
        </div>
        <div v-else>
            <div class="admin-container">
                    <router-link to="/indexadmin">
                    <img src="../../public/logo.png">
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
    margin: 0;
    padding: 4em 2em;
}

.admin-container{
    display: flex;
    flex-direction: row;
}

.grid-container{
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(3, auto);
    gap: 0 25%;
    padding:0 2em;
    margin-left: 15em;
}

p{
    margin: 0 6em
}
</style>