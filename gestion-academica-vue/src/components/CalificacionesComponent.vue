<script setup>
import { onMounted, ref } from 'vue';
import { useCalificacionStore } from '../stores/calificacionStore';
import { useUsuarioStore } from '../stores/usuarioStore';

const usuarioStore = useUsuarioStore();
const usuario = usuarioStore.usuario;

const calificacionesStore = useCalificacionStore();
const calificaciones = ref([]);
const calificacionesProfesor = ref([]);
let hasCalificaciones = ref(false);

onMounted(async () => {
    const data = await calificacionesStore.getCalificaciones();
    calificaciones.value = data;

    calificaciones.value.forEach(c => {
        if (c.moduloDto.profesorDto.id == usuario.id){
            calificacionesProfesor.value.push(c);
        }
    });

    if (calificacionesProfesor.value.length > 0){
    hasCalificaciones.value = true;
}
})

const isAdmin = ref(false);
if (usuario.rol == "admin"){
    isAdmin.value = true;
}
</script>

<template>
    <div v-if="isAdmin">
        <h2>Historial de calificaciones: </h2>
        <div class="table-container">
            <table class="content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Módulo</th>
                        <th>Profesor</th>
                        <th>Alumno</th>
                        <th>RA 1</th>
                        <th>RA 2</th>
                        <th>RA 3</th>
                        <th>RA 4</th>
                        <th>RA 5</th>
                        <th>RA 6</th>
                        <th>RA 7</th>
                        <th>RA 8</th>
                        <th>RA 9</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="calificacion in calificaciones" :key="calificacion.id">
                        <td>{{ calificacion.id }}</td>
                        <td>{{ calificacion.moduloDto.cicloDto.nombre }}</td>
                        <td>{{ calificacion.moduloDto.nombre }}</td>
                        <td>{{ calificacion.moduloDto.profesorDto.nombre }} {{ calificacion.moduloDto.profesorDto.apellidos }} <br> {{ calificacion.moduloDto.profesorDto.email }}</td>
                        <td>{{ calificacion.alumnoDto.nombre }} {{ calificacion.alumnoDto.apellidos }} <br> {{ calificacion.alumnoDto.email }}</td>
                        <td>{{ calificacion.ra1 }}</td>
                        <td>{{ calificacion.ra2 }}</td>
                        <td>{{ calificacion.ra3 }}</td>
                        <td>{{ calificacion.ra4 }}</td>
                        <td>{{ calificacion.ra5 }}</td>
                        <td>{{ calificacion.ra6 }}</td>
                        <td>{{ calificacion.ra7 }}</td>
                        <td>{{ calificacion.ra8 }}</td>
                        <td>{{ calificacion.ra9 }}</td>
                        <td>Modificar | Eliminar</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div v-else>
        <div v-if="!hasCalificaciones" class="sin-calificaciones">
            <h2 class="no-calificaciones"> Lo sentimos, {{ usuario.nombre }} {{ usuario.apellidos }}.  Todavía no tienes calificaciones asignadas</h2>
        </div>
        <div v-else>
            <div class="table-container">
            <h2 class="bienvenida">Bienvenido, {{ usuario.nombre }} {{ usuario.apellidos }}. &#128075;</h2>
            <table class="content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Módulo</th>
                        <th>Alumno</th>
                        <th>RA 1</th>
                        <th>RA 2</th>
                        <th>RA 3</th>
                        <th>RA 4</th>
                        <th>RA 5</th>
                        <th>RA 6</th>
                        <th>RA 7</th>
                        <th>RA 8</th>
                        <th>RA 9</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="calificacion in calificacionesProfesor" :key="calificacion.id">
                        <td>{{ calificacion.id }}</td>
                        <td>{{ calificacion.moduloDto.cicloDto.nombre }}</td>
                        <td>{{ calificacion.moduloDto.nombre }}</td>
                        <td>{{ calificacion.alumnoDto.nombre }} {{ calificacion.alumnoDto.apellidos }} <br> {{ calificacion.alumnoDto.email }}</td>
                        <td>{{ calificacion.ra1 }}</td>
                        <td>{{ calificacion.ra2 }}</td>
                        <td>{{ calificacion.ra3 }}</td>
                        <td>{{ calificacion.ra4 }}</td>
                        <td>{{ calificacion.ra5 }}</td>
                        <td>{{ calificacion.ra6 }}</td>
                        <td>{{ calificacion.ra7 }}</td>
                        <td>{{ calificacion.ra8 }}</td>
                        <td>{{ calificacion.ra9 }}</td>
                        <td>Modificar | Eliminar</td>
                    </tr>
                </tbody>
            </table>
        </div>
        </div>
    </div>
</template>

<style scoped>
h2{
    text-align: center;
    margin: 2em 0 0 0;
    text-decoration: underline;
}

.sin-calificaciones {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.table-container{
    display: flex;
    flex-direction: column;
}

.bienvenida{
    text-decoration: none;
    text-align: left;
    margin-bottom: 1.2em;
}
</style>