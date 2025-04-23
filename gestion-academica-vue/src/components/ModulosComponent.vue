<script setup>
import { onMounted, ref } from 'vue';

let listaModulos = ref([]);

onMounted(async () => {
    try {
        const response = await fetch("http://localhost:8080/modulos");
        if (!response.ok) {
            throw new Error("Ha ocurrido un error");
        }
        const data = await response.json();
        listaModulos.value = data;
    } catch (error) {
        console.error("Error al obtener módulos:", error);
    }
});

</script>

<template>
            <h2>Historial de módulos: </h2>
        <div class="table-container">
            <table class="content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Módulo</th>
                        <th>Profesor</th>
                        <th>Email del profesor</th>
                        <th>Ciclo</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="modulo in listaModulos" :key="modulo.id">
                        <td>{{ modulo.id }}</td>
                        <td>{{ modulo.nombre }}</td>
                        <td> {{ modulo.profesorDto.nombre }}  {{ modulo.profesorDto.apellidos }}</td>
                        <td>{{ modulo.profesorDto.email }}</td>
                        <td>{{ modulo.cicloDto.nombre }}</td>
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