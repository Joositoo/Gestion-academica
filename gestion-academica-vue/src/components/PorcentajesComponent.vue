<script setup>
import { onMounted, reactive, ref } from 'vue';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';
import { useUsuarioStore } from '../stores/usuarioStore';

const listaPorcentajes = ref([]);
const listaFiltrada = ref([]);
const listaFiltradaProf = ref([]);
const listaCiclos = ref([]);
const porcentajeId = ref(0);
const porcentaje = reactive({});
const porcentajesStore = usePorcentajeStore();
const ciclosStore = useCicloStore();
const usuarioStore = useUsuarioStore();
const router = useRouter();


const usuario = usuarioStore.usuario;
const isAdmin = ref(false);
if (usuario.rol == "admin") {
    isAdmin.value = true;
}

onMounted(async () => {
    listaPorcentajes.value = await porcentajesStore.getPorcentajes();
    listaFiltrada.value = await porcentajesStore.getPorcentajes();
    listaFiltradaProf.value = listaFiltrada.value.filter(p => p.moduloDto.profesorDto.id == usuario.id);
    listaCiclos.value = await ciclosStore.getCiclos();
});

const openModal = async (p) => {
    porcentaje.nombre = p.moduloDto.nombre;
    porcentajeId.value = p.id;
}

const handleDelete = async () => {
    await porcentajesStore.deletePorcentaje(porcentajeId.value);
    window.location.reload();
}

const handleFilter = (e) => {
    if (e.target.value == 0) {
        listaFiltrada.value = listaPorcentajes.value;
    }
    else {
        listaFiltrada.value = listaPorcentajes.value.filter(p => p.moduloDto.cicloDto.nombre == e.target.value);
    }
}

const handleDetails = (p) => {
    router.push(`/porcentajes/details/${p.moduloDto.id}`);
}

const handleEdit = (p) => {
    router.push(`/porcentajes/${p.id}`);
}

const handleClick = () => {
    router.push("porcentajes/crear");
}
</script>

<template>
    <div v-if="isAdmin">
        <h2>Historial de porcentajes</h2>
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
                        <th>Módulo</th>
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
                    <tr v-for="porcentaje in listaFiltrada" :key="porcentaje.id">
                        <td>{{ porcentaje.id }}</td>
                        <td>{{ porcentaje.moduloDto.cicloDto.nombre }}</td>
                        <td> {{ porcentaje.moduloDto.nombre }}</td>
                        <td>{{ porcentaje.ra1 }}</td>
                        <td>{{ porcentaje.ra2 }}</td>
                        <td>{{ porcentaje.ra3 }}</td>
                        <td>{{ porcentaje.ra4 }}</td>
                        <td>{{ porcentaje.ra5 }}</td>
                        <td>{{ porcentaje.ra6 }}</td>
                        <td>{{ porcentaje.ra7 }}</td>
                        <td>{{ porcentaje.ra8 }}</td>
                        <td>{{ porcentaje.ra9 }}</td>
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
                        ¿Estás seguro de eliminar los porcentajes de {{ porcentaje.nombre }}?
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
                <div><button @click="handleClick"> + Crear</button></div>
            </div>

            <table class="content-table" v-if="listaFiltradaProf.length > 0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Módulo</th>
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
                    <tr v-for="porcentaje in listaFiltradaProf" :key="porcentaje.id">
                        <td>{{ porcentaje.id }}</td>
                        <td>{{ porcentaje.moduloDto.cicloDto.nombre }}</td>
                        <td> {{ porcentaje.moduloDto.nombre }}</td>
                        <td>{{ porcentaje.ra1 }}</td>
                        <td>{{ porcentaje.ra2 }}</td>
                        <td>{{ porcentaje.ra3 }}</td>
                        <td>{{ porcentaje.ra4 }}</td>
                        <td>{{ porcentaje.ra5 }}</td>
                        <td>{{ porcentaje.ra6 }}</td>
                        <td>{{ porcentaje.ra7 }}</td>
                        <td>{{ porcentaje.ra8 }}</td>
                        <td>{{ porcentaje.ra9 }}</td>
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
                        ¿Estás seguro de eliminar los porcentajes de {{ porcentaje.nombre }}?
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
</style>