<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useProfesroStore } from '../stores/profesorStore';
import router from '../router';

let profesorStore = useProfesroStore();
let listaProfesores = ref([]);
let profesor = reactive({});
let profesorId = ref(0);

onMounted(async () => {
    await profesorStore.getProfesores();
    listaProfesores.value = await profesorStore.getProfesores();
});

const handleClick = () => {
    router.push("/profesores/crear");
}

const openModal = async(prof) => {
    profesor.nombre = prof.nombre;
    profesor.apellidos = prof.apellidos;

    profesorId.value = prof.id;
}

const handleEdit = (prof) => {
    router.push(`/profesores/${prof.id}`);
}

const handleDelete = async () => {
    await profesorStore.deleteProfesor(profesorId.value);
    window.location.reload();
}

const handleDetails = (prof) => {
    router.push(`/profesores/details/${prof.id}`);
}
</script>

<template>
    <h2>Historial de profesores: </h2>
    <div class="table-container">
        <div class="crear">
            <button @click="handleClick"> + Crear</button>
        </div>
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
                    <td><i class="bi bi-card-list" @click="handleDetails(profesor)"></i>
                        |
                        <i class="bi bi-pencil" @click="handleEdit(profesor)"></i>
                        |
                        <i class="bi bi-trash" @click="openModal(profesor)" data-bs-toggle="modal"
                            data-bs-target="#myModal"></i>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>


    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Eliminar profesor</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    ¿Estás seguro de eliminar al profesor {{ profesor.nombre }} {{ profesor.apellidos }}?
                </div>

                <div class="modal-footer">
                    <button type="button" @click="handleDelete" class="btn btn-success"
                        data-bs-dismiss="modal">Sí</button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                </div>

            </div>
        </div>
    </div>
</template>

<style scoped>
h2 {
    text-align: center;
    margin: 2em 0 0 0;
    text-decoration: underline;
}

.crear {
    margin-right: 8.5em;
}

i {
    cursor: pointer;
    width: 50px;
    font-size: 18px;
    margin: 0 0.5em;
}
</style>
