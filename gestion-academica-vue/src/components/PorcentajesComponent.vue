<script setup>
import { onMounted, reactive, ref } from 'vue';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';
import { useUsuarioStore } from '../stores/usuarioStore';

const listaPorcentajes = ref([]);
const listaPorcentajesOriginal = ref([]);
const listaFiltrada = ref([]);
const listaFiltradaProf = ref([]);
const listaCiclos = ref([]);
const porcentajeId = ref(0);
const porcentaje = reactive({});
const porcentajesStore = usePorcentajeStore();
const ciclosStore = useCicloStore();
const usuarioStore = useUsuarioStore();
const router = useRouter();
const nombreFiltrado = ref("");
const cicloSeleccionado = ref("0");

const usuario = usuarioStore.usuario;
const isAdmin = ref(false);
if (usuario.rol == "admin") {
    isAdmin.value = true;
}

onMounted(async () => {
    listaPorcentajes.value = await porcentajesStore.getPorcentajes();
    listaPorcentajesOriginal.value = listaPorcentajes.value;
    listaFiltrada.value = await porcentajesStore.getPorcentajes();
    listaFiltrada.value.sort((a, b) => {
        const cicloA = a.modulo.cicloDto.nombre.toLowerCase();
        const cicloB = b.modulo.cicloDto.nombre.toLowerCase();
        const moduloA = a.modulo.nombre.toLowerCase();
        const moduloB = b.modulo.nombre.toLowerCase();

        if (cicloA < cicloB) return -1;
        if (cicloA > cicloB) return 1;

        return moduloA.localeCompare(moduloB);
    });
    listaFiltradaProf.value = listaFiltrada.value.filter(p => p.modulo.profesorDto.id == usuario.id);
    listaFiltradaProf.value.sort((a, b) => {
        const cicloA = a.modulo.cicloDto.nombre.toLowerCase();
        const cicloB = b.modulo.cicloDto.nombre.toLowerCase();
        const moduloA = a.modulo.nombre.toLowerCase();
        const moduloB = b.modulo.nombre.toLowerCase();

        if (cicloA < cicloB) return -1;
        if (cicloA > cicloB) return 1;

        return moduloA.localeCompare(moduloB);
    });
    listaCiclos.value = await ciclosStore.getCiclos();
});

const openModal = async (p) => {
    porcentaje.descripcion = p.descripcion;
    porcentajeId.value = p.id;
}

const handleDelete = async () => {
    await porcentajesStore.deletePorcentaje(porcentajeId.value);
    window.location.reload();
}

const handleDetails = (p) => {
    router.push(`/porcentajes/details/${p.id}`);
}

const handleEdit = (p) => {
    router.push(`/porcentajes/${p.id}`);
}

const handleClick = () => {
    router.push("porcentajes/crear");
}

const filterByNameAdmin = () => {
    filtrarPorcentajes();
}

const handleFilter = (e) => {
    filtrarPorcentajes();
}

const filtrarPorcentajes = () => {
    const filtroNombre = nombreFiltrado.value.toLowerCase();
    const filtroCiclo = cicloSeleccionado.value;

    listaFiltrada.value = listaPorcentajesOriginal.value.filter((p) => {
        const coincideNombre = p.modulo.nombre.toLowerCase().includes(filtroNombre);
        const coincideCiclo = filtroCiclo === "0" || p.modulo.cicloDto.nombre === filtroCiclo;
        return coincideNombre && coincideCiclo;
    });
};
</script>

<template>
    <div v-if="isAdmin">
        <h2>Listado de porcentajes</h2>
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
                    <input type="text" class="crear-editar-input" @input="filterByNameAdmin" v-model="nombreFiltrado" />
                </div>
                <div><button @click="handleClick" class="crearBtn"> + Crear</button></div>
            </div>

            <table class="content-table" v-if="listaFiltrada.length > 0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Profesor</th>
                        <th>Módulo</th>
                        <th>Descripción</th>
                        <th>Nombre</th>
                        <th>Porcentaje</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="porcentaje in listaFiltrada" :key="porcentaje.id">
                        <td>{{ porcentaje.id }}</td>
                        <td>{{ porcentaje.modulo.cicloDto.nombre }}</td>
                        <td>{{ porcentaje.modulo.profesorDto.nombre }} {{ porcentaje.modulo.profesorDto.apellidos }}
                        </td>
                        <td>{{ porcentaje.modulo.nombre }}</td>
                        <td>{{ porcentaje.descripcion }}</td>
                        <td>{{ porcentaje.nombre }}</td>
                        <td>{{ porcentaje.porcentaje }}%</td>
                        <td><i class="bi bi-card-list" @click="handleDetails(porcentaje)"></i>
                            |
                            <i class="bi bi-pencil" @click="handleEdit(porcentaje)"></i>
                            |
                            <i class="bi bi-trash" @click="openModal(porcentaje)" data-bs-toggle="modal"
                                data-bs-target="#myModal"></i>
                        </td>
                    </tr>
                </tbody>
            </table>

            <h2 v-else>No hay registro de porcentajes asignados a este ciclo o no existe el registro</h2>
        </div>


        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Eliminar porcentaje</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        ¿Estás seguro de eliminar el porcentaje de {{ porcentaje.descripcion }}?
                    </div>

                    <div class="modal-footer">
                        <button type="button" @click="handleDelete" class="btn btn-success"
                            data-bs-dismiss="modal">Sí</button>
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div v-else>
        <h2>Historial de porcentajes</h2>
        <div class="table-container">
            <div class="crear crearP">
                <div><button @click="handleClick" class="crearBtn"> + Crear</button></div>
            </div>

            <table class="content-table" v-if="listaFiltradaProf.length > 0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Profesor</th>
                        <th>Módulo</th>
                        <th>Descripción</th>
                        <th>Nombre</th>
                        <th>Porcentaje</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="porcentaje in listaFiltradaProf" :key="porcentaje.id">
                        <td>{{ porcentaje.id }}</td>
                        <td>{{ porcentaje.modulo.cicloDto.nombre }}</td>
                        <td>{{ porcentaje.modulo.profesorDto.nombre }} {{ porcentaje.modulo.profesorDto.apellidos }}
                        </td>
                        <td>{{ porcentaje.modulo.nombre }}</td>
                        <td>{{ porcentaje.descripcion }}</td>
                        <td>{{ porcentaje.nombre }}</td>
                        <td>{{ porcentaje.porcentaje }}%</td>
                        <td><i class="bi bi-card-list" @click="handleDetails(porcentaje)"></i>
                            |
                            <i class="bi bi-pencil" @click="handleEdit(porcentaje)"></i>
                            |
                            <i class="bi bi-trash" @click="openModal(porcentaje)" data-bs-toggle="modal"
                                data-bs-target="#myModal"></i>
                        </td>
                    </tr>
                </tbody>
            </table>

            <h2 v-else>No hay porcentajes asignados a este ciclo</h2>
        </div>


        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Eliminar porcentaje</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        ¿Estás seguro de eliminar el porcentaje de {{ porcentaje.descripcion }}?
                    </div>

                    <div class="modal-footer">
                        <button type="button" @click="handleDelete" class="btn btn-success"
                            data-bs-dismiss="modal">Sí</button>
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                    </div>

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

.crearP {
    justify-content: right;
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

.crearBtn {
    transition: background-color 0.3s ease;
}

.crearBtn:hover {
    background-color: #59c1ff;
    ;
}
</style>