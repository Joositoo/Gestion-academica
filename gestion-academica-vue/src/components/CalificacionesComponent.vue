<script setup>
import * as XLSX from 'xlsx';
import { onMounted, reactive, ref } from 'vue';
import { useCalificacionStore } from '../stores/calificacionStore';
import { useUsuarioStore } from '../stores/usuarioStore';
import { useModuloStore } from '../stores/moduloStore'
import { useRouter } from 'vue-router';
import { usePorcentajeStore } from '../stores/porcentajeStore';

const router = useRouter();
const usuarioStore = useUsuarioStore();
const moduloStore = useModuloStore();
const porcentajesStore = usePorcentajeStore();
const usuario = usuarioStore.usuario;

const calificacionesStore = useCalificacionStore();
const calificaciones = ref([]);
const calificacionesProfesor = ref([]);
const listaModulos = ref([]);
const listaCalificacionesOriginal = ref([]);
const listaModulosProf = ref([]);
const calificacionesProfesorOriginal = ref([]);
const listaPorcentajes = ref([]);
const nombreFiltrado = ref("");

let moduloSeleccionado = ref("0");
let calificacionesFiltradas = ref([]);
let hasCalificaciones = ref(false);

let calificacionId = ref(0);
let calificacion = reactive({
    nombre: "",
    apellidos: "",
    modulo: ""
})

onMounted(async () => {
    const data = await calificacionesStore.getCalificaciones();
    listaModulos.value = await moduloStore.getModulos();
    listaPorcentajes.value = await porcentajesStore.getPorcentajes();


    listaModulosProf.value = listaModulos.value.filter(m => m.profesorDto.id == usuario.id);

    calificaciones.value = data;
    calificacionesFiltradas.value = calificaciones.value;
    calificacionesFiltradas.value = calificaciones.value.sort((a, b) => {
        const moduloA = a.moduloDto.nombre.toLowerCase();
        const moduloB = b.moduloDto.nombre.toLowerCase();

        if (moduloA < moduloB) return -1;
        if (moduloA > moduloB) return 1;

        const alumnoA = a.alumnoDto.nombre.toLowerCase();
        const alumnoB = b.alumnoDto.nombre.toLowerCase();

        return alumnoA.localeCompare(alumnoB);
    });
    listaCalificacionesOriginal.value = calificacionesFiltradas.value;

    calificacionesProfesor.value = calificaciones.value
    .filter(c => c.moduloDto.profesorDto.id == usuario.id)
    .sort((a, b) => {
        const moduloA = a.moduloDto.nombre.toLowerCase();
        const moduloB = b.moduloDto.nombre.toLowerCase();

        if (moduloA < moduloB) return -1;
        if (moduloA > moduloB) return 1;

        const alumnoA = a.alumnoDto.nombre.toLowerCase();
        const alumnoB = b.alumnoDto.nombre.toLowerCase();

        return alumnoA.localeCompare(alumnoB);
    });
    if (calificacionesProfesor.value.length > 0) {
        hasCalificaciones.value = true;
    }
    calificacionesProfesorOriginal.value = calificacionesProfesor.value;
})

const isAdmin = ref(false);
if (usuario.rol == "admin") {
    isAdmin.value = true;
}

const openModal = async (c) => {
    calificacionId.value = c.id;
    calificacion.descripcion = c.porcentajesRaDto.descripcion;
    calificacion.nombre = c.alumnoDto.nombre;
    calificacion.apellidos = c.alumnoDto.apellidos;
    calificacion.modulo = c.moduloDto.nombre
}

const handleDelete = async () => {
    await calificacionesStore.deleteCalificacion(calificacionId.value);
    window.location.reload();
}

const handleEdit = (c) => {
    router.push(`/calificaciones/${c.id}`)
}

const handleDetails = (c) => {
    router.push(`/calificaciones/details/${c.id}`);
}

const handleClick = () => {
    router.push("/calificaciones/crear");
}

const filterByNameAdmin = () => {
    filtrarCalificaciones();
};

const handleFilter = async (e) => {
    filtrarCalificaciones();
}

const filterByNameProf = () => {
    filtrarCalificacionesProfesor();
}

const handleFilterProf = async (e) => {
    moduloSeleccionado.value = e.target.value;
    filtrarCalificacionesProfesor();
}

const filtrarCalificaciones = () => {
    const filtroNombre = nombreFiltrado.value
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");

    const filtroModulo = moduloSeleccionado.value;

    calificacionesFiltradas.value = listaCalificacionesOriginal.value.filter((c) => {
        const nombreAlumno = c.alumnoDto.nombre
            .toLowerCase()
            .normalize("NFD")
            .replace(/[\u0300-\u036f]/g, "");

        const coincideNombre = nombreAlumno.includes(filtroNombre);
        const coincideModulo = filtroModulo === "0" || c.moduloDto.nombre === filtroModulo;

        return coincideNombre && coincideModulo;
    });
};

const filtrarCalificacionesProfesor = () => {
    const filtroNombre = nombreFiltrado.value
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");

    const filtroModulo = moduloSeleccionado.value;

    calificacionesProfesor.value = calificacionesProfesorOriginal.value.filter((c) => {
        const nombreAlumno = c.alumnoDto.nombre
            .toLowerCase()
            .normalize("NFD")
            .replace(/[\u0300-\u036f]/g, "");

        const coincideNombre = nombreAlumno.includes(filtroNombre);
        const coincideModulo = filtroModulo === "0" || c.moduloDto.nombre === filtroModulo;

        return coincideNombre && coincideModulo;
    });
};

const generateExcel = (array) => {
    const data = [
        ["ID", "Ciclo", "Módulo", "Descripción del RA", "Alumno", "Email del alumno", "Nota", "Porcentaje", "Nota final"],
    ];

    for (const c of array) {
        data.push([c.id, c.moduloDto.cicloDto.nombre, c.moduloDto.nombre, c.porcentajesRaDto.descripcion,
        `${c.alumnoDto.nombre} ${c.alumnoDto.apellidos}`, c.alumnoDto.email, c.nota,
        `${c.porcentajesRaDto.porcentaje}%`, ((c.porcentajesRaDto.porcentaje / 100) * c.nota).toFixed(2)]);
    }

    const worksheet = XLSX.utils.aoa_to_sheet(data);
    worksheet['!autofilter'] = { ref: 'A1:H1' }

    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Calificaciones");
    XLSX.writeFile(workbook, 'ListaDeCalificacionesPrueba.xlsx');
}
</script>

<template>
    <div v-if="isAdmin">
        <h2>Listado de calificaciones: </h2>
        <div class="table-container">
            <div class="crear">
                <div>
                    <p>Filtrar por módulo: </p>
                    <select @change="handleFilter" v-model="moduloSeleccionado">
                        <option value="0">No filtrar</option>
                        <option v-for="modulo in listaModulos" :value="modulo.nombre">{{ modulo.nombre }}</option>
                    </select>
                </div>
                <div>
                    <p>Busca por alumno: </p>
                    <input type="text" class="crear-editar-input" @input="filterByNameAdmin" v-model="nombreFiltrado" />
                </div>
                <div class="botones">
                    <button class="excel" @click="generateExcel(calificacionesFiltradas)">Generar Excel</button>
                    <button @click="handleClick" class="crearBtn"> + Crear</button>
                </div>
            </div>


            <table class="content-table" v-if="calificacionesFiltradas.length > 0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Módulo</th>
                        <th>RA</th>
                        <th>Alumno</th>
                        <th>Nota</th>
                        <th>Porcentaje</th>
                        <th>Nota final</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="calificacion in calificacionesFiltradas" :key="calificacion.id">
                        <td>{{ calificacion.id }}</td>
                        <td>{{ calificacion.moduloDto.cicloDto.nombre }}</td>
                        <td>{{ calificacion.moduloDto.nombre }}</td>
                        <td>{{ calificacion.porcentajesRaDto.descripcion }}</td>
                        <td>{{ calificacion.alumnoDto.nombre }} {{ calificacion.alumnoDto.apellidos }}</td>
                        <td>{{ calificacion.nota }}</td>
                        <td>{{ calificacion.porcentajesRaDto.porcentaje }}%</td>
                        <td>{{ ((calificacion.porcentajesRaDto.porcentaje / 100) * calificacion.nota).toFixed(2) }}</td>
                        <td><i class="bi bi-card-list" @click="handleDetails(calificacion)"></i>
                            |
                            <i class="bi bi-pencil" @click="handleEdit(calificacion)"></i>
                            |
                            <i class="bi bi-trash" @click="openModal(calificacion)" data-bs-toggle="modal"
                                data-bs-target="#myModal"></i>
                        </td>
                    </tr>
                </tbody>
            </table>

            <h2 v-else>No hay calificaciones en este módulo o no existe el alumno</h2>
        </div>


        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Eliminar calificación</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        ¿Estás seguro de eliminar la calificación de {{ calificacion.nombre }} {{ calificacion.apellidos
                        }} en {{ calificacion.descripcion }}?
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
        <div v-if="!hasCalificaciones" class="sin-calificaciones">
            <h2>Listado de calificaciones: </h2>
            <div class="btn-container">
                <div class="crear btn-wrapper">
                    <div>
                        <button @click="handleClick"> + Crear</button>
                    </div>
                </div>
            </div>
            <h2 class="no-calificaciones"> Lo sentimos, {{ usuario.nombre }} {{ usuario.apellidos }}. Todavía no tienes
                calificaciones asignadas</h2>
        </div>
        <div v-else>
            <h2>Historial de calificaciones: </h2>
            <div class="table-container">
                <div class="crear">
                    <div>
                        <p>Filtrar por módulo: </p>
                        <select @change="handleFilterProf">
                            <option value="0">No filtrar</option>
                            <option v-for="modulo in listaModulosProf" :value="modulo.nombre">{{ modulo.nombre }}
                            </option>
                        </select>
                    </div>
                    <div>
                        <p>Busca por alumno: </p>
                        <input type="text" class="crear-editar-input" @input="filterByNameProf"
                            v-model="nombreFiltrado" />
                    </div>
                    <div class="botones">
                        <button class="excel" @click="generateExcel(calificacionesProfesor)">Generar Excel</button>
                        <button @click="handleClick" class="crearBtn"> + Crear</button>
                    </div>
                </div>


                <table class="content-table" v-if="calificacionesProfesor.length > 0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Ciclo</th>
                            <th>Módulo</th>
                            <th>RA</th>
                            <th>Alumno</th>
                            <th>Nota</th>
                            <th>Porcentaje</th>
                            <th>Nota final</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="calificacion in calificacionesProfesor" :key="calificacion.id">
                            <td>{{ calificacion.id }}</td>
                            <td>{{ calificacion.moduloDto.cicloDto.nombre }}</td>
                            <td>{{ calificacion.moduloDto.nombre }}</td>
                            <td>{{ calificacion.porcentajesRaDto.descripcion }}</td>
                            <td>{{ calificacion.alumnoDto.nombre }} {{ calificacion.alumnoDto.apellidos }}</td>
                            <td>{{ calificacion.nota }}</td>
                            <td>{{ calificacion.porcentajesRaDto.porcentaje }}%</td>
                            <td>{{ ((calificacion.porcentajesRaDto.porcentaje / 100) * calificacion.nota).toFixed(2) }}
                            </td>
                            <td><i class="bi bi-card-list" @click="handleDetails(calificacion)"></i>
                                |
                                <i class="bi bi-pencil" @click="handleEdit(calificacion)"></i>
                                |
                                <i class="bi bi-trash" @click="openModal(calificacion)" data-bs-toggle="modal"
                                    data-bs-target="#myModal"></i>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <h2 v-else>No hay calificaciones en este módulo o no existe el alumno</h2>
            </div>


            <div class="modal" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h4 class="modal-title">Eliminar calificación</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                        <div class="modal-body">
                            ¿Estás seguro de eliminar la calificación de {{ calificacion.nombre }} {{
                                calificacion.apellidos
                            }} en {{ calificacion.modulo }}?
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
    </div>
</template>

<style scoped>
h2 {
    text-align: center;
    margin: 2em 0 0 0;
    text-decoration: underline;
}

.sin-calificaciones {
    display: flex;
    flex-direction: column;
    gap: 3.5em;
    justify-content: center;
    align-items: center;
    height: 50vh;
    margin-bottom: 2em;
}

.table-container {
    display: flex;
    flex-direction: column;
}

.bienvenida {
    text-decoration: none;
    text-align: left;
    margin-bottom: 1.2em;
}

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

.btn-container {
    display: flex;
    justify-content: flex-end;
    width: 100%;
    padding-right: 3.5em;
}

.btn-wrapper {
    display: flex;
    align-items: center;
}

.botones {
    display: flex;
    gap: 20px;
}

.excel {
    transition: background-color 0.3s ease;
}

.excel:hover {
    background-color: #17d586;
}

.crearBtn {
    transition: background-color 0.3s ease;
}

.crearBtn:hover {
    background-color: #59c1ff;
    ;
}
</style>