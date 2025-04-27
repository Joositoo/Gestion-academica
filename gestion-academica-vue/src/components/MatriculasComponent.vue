<script setup>
import { onMounted, ref } from 'vue';

let listaMatriculas = ref([]);

onMounted(async () => {
    try {
        const response = await fetch("http://localhost:8080/matriculas");
        if (!response.ok) {
            throw new Error("Ha ocurrido un error");
        }
        const data = await response.json();
        listaMatriculas.value = data;
    } catch (error) {
        console.error("Error al obtener matrículas:", error);
    }
});

</script>

<template>
            <h2>Historial de matrículas: </h2>
        <div class="table-container">
            <table class="content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Alumno</th>
                        <th>Email del alumno</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="matricula in listaMatriculas" :key="matricula.id">
                        <td>{{ matricula.id }}</td>
                        <td>{{ matricula.cicloDto.nombre }}</td>
                        <td>{{ matricula.alumnoDto.nombre }} {{ matricula.alumnoDto.apellidos }}</td>
                        <td>{{ matricula.alumnoDto.email }}</td>
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