<script setup>
import { onMounted, ref } from 'vue';

let listaAlumnos = ref([]);

onMounted(async () => {
    try {
        const response = await fetch("http://localhost:8080/alumnos");
        if (!response.ok) {
            throw new Error("Ha ocurrido un error");
        }
        const data = await response.json();
        listaAlumnos.value = data;
    } catch (error) {
        console.error("Error al obtener alumnos:", error);
    }
});

</script>

<template>
            <h2>Historial de alumnos: </h2>
        <div class="table-container">
            <table class="content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Email</th>
                        <th>Tutor</th>
                        <th>Email del tutor</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="alumno in listaAlumnos" :key="alumno.id">
                        <td>{{ alumno.id }}</td>
                        <td>{{ alumno.nombre }}</td>
                        <td>{{ alumno.apellidos }}</td>
                        <td>{{ alumno.email }}</td>
                        <td>{{ alumno.profesorDto.nombre }}  {{ alumno.profesorDto.apellidos }}</td>
                        <td>{{ alumno.profesorDto.email }}</td>
                        <td>Modificar | Eliminar</td>
                    </tr>
                </tbody>
            </table>
        </div>
</template>

<style scoped>
h2{
    text-align: center;
    margin: 2em 0 0 0;
    text-decoration: underline;
}
</style>