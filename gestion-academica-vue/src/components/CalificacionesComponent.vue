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
    listaCalificacionesOriginal.value = calificacionesFiltradas.value;

    calificacionesProfesor.value = calificaciones.value.filter(c => c.moduloDto.profesorDto.id == usuario.id);
    if (calificacionesProfesor.value.length > 0) {
        hasCalificaciones.value = true;
    }
    calificacionesProfesorOriginal.value = calificacionesProfesor.value;
})

const isAdmin = ref(false);
if (usuario.rol == "admin") {
    isAdmin.value = true;
}

const handleFilter = async (e) => {
    if (e.target.value == 0) {
        calificacionesFiltradas.value = calificaciones.value;
    }
    else {
        calificacionesFiltradas.value = calificaciones.value.filter(c => c.moduloDto.nombre == e.target.value);
    }
}

const handleFilterProf = async (e) => {
    if (e.target.value == 0) {
        calificacionesProfesor.value = calificaciones.value.filter(c => c.moduloDto.profesorDto.id == usuario.id);
    }
    else {
        calificacionesProfesor.value = calificaciones.value.filter(c => c.moduloDto.nombre == e.target.value);
    }
}

const openModal = async (c) => {
    calificacionId.value = c.id;

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
    const filtro = nombreFiltrado.value
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");

    calificacionesFiltradas.value = listaCalificacionesOriginal.value.filter((c) => {
        const nombreAlumno = c.alumnoDto.nombre
            .toLowerCase()
            .normalize("NFD")
            .replace(/[\u0300-\u036f]/g, "");

        return nombreAlumno.includes(filtro);
    });
};

const filterByNameProf = () => {
    const filtro = nombreFiltrado.value
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");

    calificacionesProfesor.value = calificacionesProfesorOriginal.value.filter((c) => {
        const nombreAlumno = c.alumnoDto.nombre
            .toLowerCase()
            .normalize("NFD")
            .replace(/[\u0300-\u036f]/g, "");

        return nombreAlumno.includes(filtro);
    });
}

const generateExcel = (array) => {
    const porcentaje = reactive({
        ra1: 0,
        ra2: 0,
        ra3: 0,
        ra4: 0,
        ra5: 0,
        ra6: 0,
        ra7: 0,
        ra8: 0,
        ra9: 0,
    })

    const notaPonderada = reactive({
        ra1: 0,
        ra2: 0,
        ra3: 0,
        ra4: 0,
        ra5: 0,
        ra6: 0,
        ra7: 0,
        ra8: 0,
        ra9: 0,
    })

    const data = [
        ["ID", "Ciclo", "Módulo", "Alumno", "Email del alumno", "RA 1", "RA 2", "RA 3", "RA 4", "RA 5", "RA 6", "RA 7", "RA 8", "RA 9", "Nota final"],
    ];
    for (const c of array) {
        for (const p of listaPorcentajes.value) {
            if (c.moduloDto.nombre == p.moduloDto.nombre){                
                porcentaje.ra1 = p.ra1;
                porcentaje.ra2 = p.ra2;
                porcentaje.ra3 = p.ra3;
                porcentaje.ra4 = p.ra4;
                porcentaje.ra5 = p.ra5;
                porcentaje.ra6 = p.ra6;
                porcentaje.ra7 = p.ra7;
                porcentaje.ra8 = p.ra8;
                porcentaje.ra9 = p.ra9;
                break;                
            }
        }
        notaPonderada.ra1 = (c.ra1 * porcentaje.ra1 / 100);
        notaPonderada.ra2 = (c.ra2 * porcentaje.ra2 / 100);
        notaPonderada.ra3 = (c.ra3 * porcentaje.ra3 / 100);
        notaPonderada.ra4 = (c.ra4 * porcentaje.ra4 / 100);
        notaPonderada.ra5 = (c.ra5 * porcentaje.ra5 / 100);
        notaPonderada.ra6 = (c.ra6 * porcentaje.ra6 / 100);
        notaPonderada.ra7 = (c.ra7 * porcentaje.ra7 / 100);
        notaPonderada.ra8 = (c.ra8 * porcentaje.ra8 / 100);
        notaPonderada.ra9 = (c.ra9 * porcentaje.ra9 / 100);
        
        let notaFinal = (notaPonderada.ra1 + notaPonderada.ra2 + notaPonderada.ra3 + notaPonderada.ra4 + notaPonderada.ra5 + 
                        notaPonderada.ra6 + notaPonderada.ra7 + notaPonderada.ra8 + notaPonderada.ra9);

        data.push([c.id, c.moduloDto.cicloDto.nombre, c.moduloDto.nombre, c.alumnoDto.nombre+ " " +c.alumnoDto.apellidos, c.alumnoDto.email, notaPonderada.ra1, 
                    notaPonderada.ra2, notaPonderada.ra3, notaPonderada.ra4, notaPonderada.ra5, notaPonderada.ra6, notaPonderada.ra7, notaPonderada.ra8, notaPonderada.ra9, notaFinal]);
    }

    const worksheet = XLSX.utils.aoa_to_sheet(data);
    worksheet['!autofilter'] = { ref: 'A1:O1' }

    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Calificaciones");
    XLSX.writeFile(workbook, 'ListaDeCalificaciones.xlsx');
}
</script>

<template>
    <div v-if="isAdmin">
        <h2>Historial de calificaciones: </h2>
        <div class="table-container">
            <div class="crear">
                <div>
                    <p>Filtrar por módulo: </p>
                    <select @change="handleFilter">
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
                    <button @click="handleClick"> + Crear</button>
                </div>
            </div>


            <table class="content-table" v-if="calificacionesFiltradas.length > 0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ciclo</th>
                        <th>Módulo</th>
                        <!--<th>Profesor</th>-->
                        <th>Alumno</th>
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
                    <tr v-for="calificacion in calificacionesFiltradas" :key="calificacion.id">
                        <td>{{ calificacion.id }}</td>
                        <td>{{ calificacion.moduloDto.cicloDto.nombre }}</td>
                        <td>{{ calificacion.moduloDto.nombre }}</td>
                        <!--<td>{{ calificacion.moduloDto.profesorDto.nombre }} {{
                            calificacion.moduloDto.profesorDto.apellidos }}</td>-->
                        <td>{{ calificacion.alumnoDto.nombre }} {{ calificacion.alumnoDto.apellidos }}</td>
                        <td>{{ calificacion.ra1 }}</td>
                        <td>{{ calificacion.ra2 }}</td>
                        <td>{{ calificacion.ra3 }}</td>
                        <td>{{ calificacion.ra4 }}</td>
                        <td>{{ calificacion.ra5 }}</td>
                        <td>{{ calificacion.ra6 }}</td>
                        <td>{{ calificacion.ra7 }}</td>
                        <td>{{ calificacion.ra8 }}</td>
                        <td>{{ calificacion.ra9 }}</td>
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

    <div v-else>
        <div v-if="!hasCalificaciones" class="sin-calificaciones">
            <h2>Historial de calificaciones: </h2>
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
                        <p>Busca por módulo: </p>
                        <input type="text" class="crear-editar-input" @input="filterByNameProf"
                            v-model="nombreFiltrado" />
                    </div>
                    <div class="botones">
                        <button class="excel" @click="generateExcel(calificacionesProfesor)">Generar Excel</button>
                        <button @click="handleClick"> + Crear</button>
                    </div>
                </div>


                <table class="content-table" v-if="calificacionesProfesor.length > 0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Ciclo</th>
                            <th>Módulo</th>
                            <!--<th>Profesor</th>-->
                            <th>Alumno</th>
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
                        <tr v-for="calificacion in calificacionesProfesor" :key="calificacion.id">
                            <td>{{ calificacion.id }}</td>
                            <td>{{ calificacion.moduloDto.cicloDto.nombre }}</td>
                            <td>{{ calificacion.moduloDto.nombre }}</td>
                            <!--<td>{{ calificacion.moduloDto.profesorDto.nombre }} {{
                            calificacion.moduloDto.profesorDto.apellidos }}</td>-->
                            <td>{{ calificacion.alumnoDto.nombre }} {{ calificacion.alumnoDto.apellidos }}</td>
                            <td>{{ calificacion.ra1 }}</td>
                            <td>{{ calificacion.ra2 }}</td>
                            <td>{{ calificacion.ra3 }}</td>
                            <td>{{ calificacion.ra4 }}</td>
                            <td>{{ calificacion.ra5 }}</td>
                            <td>{{ calificacion.ra6 }}</td>
                            <td>{{ calificacion.ra7 }}</td>
                            <td>{{ calificacion.ra8 }}</td>
                            <td>{{ calificacion.ra9 }}</td>
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

.botones{
    display: flex;
    gap: 20px;
}

.excel:hover {
    background-color: #17d586;
}
</style>