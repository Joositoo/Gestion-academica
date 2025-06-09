<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useModuloStore } from '../stores/moduloStore';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';

let listaModulos = ref([]);
let listaModulosOriginal = ref([]);
let listaCiclos = ref([]);
let listaFiltrada = ref([]);
let modulo = reactive({});
let moduloId = ref(0);
let nombreFiltrado = ref("");
let cicloSeleccionado = ref("0");
const moduloStore = useModuloStore();
const cicloStore = useCicloStore();
const router = useRouter();

onMounted(async () => {
    listaModulos.value = await moduloStore.getModulos();
    listaModulosOriginal.value = listaModulos.value;
    listaFiltrada.value = listaModulos.value;
    listaFiltrada.value.sort((a, b) => {
        const cicloA = a.cicloDto.nombre.toLowerCase();
        const cicloB = b.cicloDto.nombre.toLowerCase();
        const moduloA = a.nombre.toLowerCase();
        const moduloB = b.nombre.toLowerCase();

        if (cicloA < cicloB) return -1;
        if (cicloA > cicloB) return 1;

        return moduloA.localeCompare(moduloB);
    })
    listaCiclos.value = await cicloStore.getCiclos();
});

const handleClick = () => {
    router.push("/modulos/crear");
}

const openModal = async (m) => {
    modulo.nombre = m.nombre;
    moduloId.value = m.id;
}

const handleEdit = (m) => {
    router.push(`/modulos/${m.id}`);
}

const handleDetails = (m) => {
    router.push(`/modulos/details/${m.id}`);
}

const handleDelete = async () => {
    await moduloStore.deleteModulo(moduloId.value);
    window.location.reload();
}

const handleFilter = (e) => {
    filtrarModulos();
}

const filterByName = () => {
    filtrarModulos();
}

const filtrarModulos = () => {
    const filtroNombre = nombreFiltrado.value.toLowerCase();
    const filtroCiclo = cicloSeleccionado.value;

    listaFiltrada.value = listaModulosOriginal.value.filter((m) => {
        const coincideNombre = m.nombre.toLowerCase().includes(filtroNombre);
        const coincideCiclo = filtroCiclo === "0" || m.cicloDto.nombre === filtroCiclo;
        return coincideNombre && coincideCiclo;
    });
};
</script>

<template>
    <h2>Listado de módulos: </h2>
    <div class="table-container">
        <div class="crear">
            <div>
                <p>Filtrar por ciclo: </p>
                <select @change="handleFilter" v-model="cicloSeleccionado">
                    <option value="0">No filtrar</option>
                    <option v-for="ciclo in listaCiclos" :value="ciclo.nombre">{{ ciclo.nombre }}</option>
                </select>
            </div>
            <div>
                <p>Busca por módulo: </p>
                <input type="text" class="crear-editar-input" @input="filterByName" v-model="nombreFiltrado" />
            </div>
            <div><button @click="handleClick" class="crearBtn"> + Crear</button></div>
        </div>

        <table class="content-table" v-if="listaFiltrada.length > 0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ciclo</th>
                    <th>Módulo</th>
                    <th>Profesor</th>
                    <th>Email del profesor</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="modulo in listaFiltrada" :key="modulo.id">
                    <td>{{ modulo.id }}</td>
                    <td>{{ modulo.cicloDto.nombre }}</td>
                    <td>{{ modulo.nombre }}</td>
                    <td> {{ modulo.profesorDto.nombre }} {{ modulo.profesorDto.apellidos }}</td>
                    <td>{{ modulo.profesorDto.email }}</td>
                    <td><i class="bi bi-card-list" @click="handleDetails(modulo)"></i>
                        |
                        <i class="bi bi-pencil" @click="handleEdit(modulo)"></i>
                        |
                        <i class="bi bi-trash" @click="openModal(modulo)" data-bs-toggle="modal"
                            data-bs-target="#myModal"></i>
                    </td>
                </tr>
            </tbody>
        </table>

        <h2 v-else>No hay asignaturas asignadas a este módulo o no existe el módulo</h2>
    </div>


    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Eliminar ciclo</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    ¿Estás seguro de eliminar el módulo {{ modulo.nombre }}?
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
    margin-right: 5em;
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

i {
    cursor: pointer;
    width: 50px;
    font-size: 18px;
    margin: 0 0.5em;
}

.table-container h2 {
    text-decoration: none;
}

.crearBtn{
    transition: background-color 0.3s ease;
}

.crearBtn:hover{
    background-color: #59c1ff;;
}
</style>