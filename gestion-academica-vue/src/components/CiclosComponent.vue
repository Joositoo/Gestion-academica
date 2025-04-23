<script setup>
import { onMounted, ref } from 'vue';

let listaCiclos = ref([]);

onMounted(async () => {
    try {
        const response = await fetch("http://localhost:8080/ciclos");
        if (!response.ok) {
            throw new Error("Ha ocurrido un error");
        }
        const data = await response.json();
        listaCiclos.value = data;
    } catch (error) {
        console.error("Error al obtener ciclos:", error);
    }
});

</script>

<template>
            <h2>Historial de ciclos: </h2>
        <div class="table-container">
            <table class="content-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="ciclo in listaCiclos" :key="ciclo.id">
                        <td>{{ ciclo.id }}</td>
                        <td>{{ ciclo.nombre }}</td>
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