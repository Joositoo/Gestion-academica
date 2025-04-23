<script setup>
import { onMounted, ref } from 'vue';

let listaProfesores = ref([]);

onMounted(async () => {
    try {
        const response = await fetch("http://localhost:8080/profesores");
        if (!response.ok) {
            throw new Error("Ha ocurrido un error");
        }
        const data = await response.json();
        listaProfesores.value = data;
    } catch (error) {
        console.error("Error al obtener profesores:", error);
    }
});

</script>

<template>
            <h2>Historial de profesores: </h2>
        <div class="table-container">
            <table class="content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Email</th>
                        <th>Rol</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="profesor in listaProfesores" :key="profesor.id">
                        <td>{{ profesor.id }}</td>
                        <td>{{ profesor.nombre }}</td>
                        <td>{{ profesor.apellidos }}</td>
                        <td>{{ profesor.email }}</td>
                        <td>{{ profesor.rol }}</td>
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