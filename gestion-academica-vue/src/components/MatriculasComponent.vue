<script setup>
import { onMounted, ref } from 'vue';
import { useMatriculaStore } from '../stores/matriculaStore';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';

const matriculaStore = useMatriculaStore();
const cicloStore = useCicloStore();
const router = useRouter();

let listaMatriculas = ref([]);
let listaFiltrada = ref([]);
let listaCiclos = ref([]);
let matriculaId = ref(0);
let matricula = ref({});

onMounted(async () => {
    listaMatriculas.value = await matriculaStore.getMatriculas();
    listaCiclos.value = await cicloStore.getCiclos();
    listaFiltrada.value = listaMatriculas.value;
});

const handleFilter = (e) => {
    if (e.target.value == 0) {
        listaFiltrada.value = listaMatriculas.value;
    }
    else {
        listaFiltrada.value = listaMatriculas.value.filter(m => m.cicloDto.nombre == e.target.value);
    }    
}

const handleClick = () => {
    router.push("/matriculas/crear");
}

const openModal = async (m) => {
    matriculaId.value = m.id;
}

const handleEdit = (m) => {
    router.push(`/matriculas/${m.id}`)
}

const handleDetails = (m) => {
    router.push(`/matriculas/details/${m.id}`);
}

const handleDelete = async () => {
    await matriculaStore.deleteMatricula(matriculaId.value);
    window.location.reload();
}

</script>

<template>
    <h2>Historial de matrículas: </h2>
    <div class="table-container">
        <div class="crear">
            <div>
                <p>Filtrar por ciclo: </p>
                <select @change="handleFilter">
                    <option value="0">No filtrar</option>
                    <option v-for="ciclo in listaCiclos" :value="ciclo.nombre">{{ ciclo.nombre }}</option>
                </select>
            </div>
            <div><button @click="handleClick"> + Crear</button></div>
        </div>


        <table class="content-table" v-if="listaFiltrada.length > 0">
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
                <tr v-for="matricula in listaFiltrada" :key="matricula.id">
                    <td>{{ matricula.id }}</td>
                    <td>{{ matricula.cicloDto.nombre }}</td>
                    <td>{{ matricula.alumnoDto.nombre }} {{ matricula.alumnoDto.apellidos }}</td>
                    <td>{{ matricula.alumnoDto.email }}</td>
                    <td><i class="bi bi-card-list" @click="handleDetails(matricula)"></i>
                        |
                        <i class="bi bi-pencil" @click="handleEdit(matricula)"></i>
                        |
                        <i class="bi bi-trash" @click="openModal(matricula)" data-bs-toggle="modal"
                            data-bs-target="#myModal"></i>
                    </td>
                </tr>
            </tbody>
        </table>

        <h2 v-else>No hay alumnos matriculados en este ciclo</h2>
    </div>


    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Eliminar matrícula</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    ¿Estás seguro de eliminar la matrícula con ID {{ matriculaId }}?
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
    margin-right: 3.5em;
    justify-content: space-between;
}

.crear div {
    display: flex;
    align-items: center;
}

.crear div p {
    margin: 0;
    padding-right: 0.5em;
    margin-left: 1.9em;
}

.table-container h2 {
    text-decoration: none;
}
</style>