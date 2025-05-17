<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useAlumnoStore } from '../stores/alumnoStore';
import { useProfesroStore } from '../stores/profesorStore';
import { useRouter } from 'vue-router';

const alumnoStore = useAlumnoStore();
const profesorStore = useProfesroStore();
const router = useRouter();
let alumno = reactive({});
let alumnoId = ref(0);
let listaAlumnos = ref([]);
let listaAlumnosOriginal = ref([]);
let listaFiltrada = ref([]);
let listaProfesores = ref([]);
let nombreFiltrado = ref("");

onMounted(async () => {
    listaAlumnos.value = await alumnoStore.getAlumnos();
    listaAlumnosOriginal.value = listaAlumnos.value;
    listaProfesores.value = await profesorStore.getProfesores();
    listaFiltrada.value = listaAlumnos.value;
});

const handleClick = () => {
    router.push("/alumnos/crear");
}

const openModal = async (a) => {
    alumno.nombre = a.nombre;
    alumno.apellidos = a.apellidos;
    alumnoId.value = a.id;
}

const handleEdit = (a) => {
    router.push(`/alumnos/${a.id}`);
}

const handleDetails = (a) => {
    router.push(`/alumnos/details/${a.id}`);
}

const handleDelete = async () => {
    await alumnoStore.deleteAlumno(alumnoId.value)
    window.location.reload();
}

const handleFilter = (e) => {
    if (e.target.value == 0) {
        listaFiltrada.value = listaAlumnos.value;
    }
    else {
        listaFiltrada.value = listaAlumnos.value.filter(a => a.profesorDto.email == e.target.value);
    }
}

const filterByName = () => {
    const filtro = nombreFiltrado.value.toLowerCase();
    listaFiltrada.value = listaAlumnosOriginal.value.filter((al) =>
        al.nombre.toLowerCase().includes(filtro)
    );
}
</script>

<template>
    <h2>Historial de alumnos: </h2>
    <div class="table-container">
        <div class="crear">
            <div>
                <p>Filtrar por tutor: </p>
                <select @change="handleFilter">
                    <option value="0">No filtrar</option>
                    <option v-for="profesor in listaProfesores" :value="profesor.email">{{ profesor.nombre }} {{ profesor.apellidos }}</option>
                </select>
            </div>
            <div>
                <p>Busca por nombre: </p>
                <input type="text" class="crear-editar-input" @input="filterByName" v-model="nombreFiltrado" />
            </div>
            <div><button @click="handleClick"> + Crear</button></div>
        </div>


        <table class="content-table" v-if="listaFiltrada.length > 0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Email</th>
                    <th>Tutor</th>
                    <th>Email del tutor</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="alumno in listaFiltrada" :key="alumno.id">
                    <td>{{ alumno.id }}</td>
                    <td>{{ alumno.nombre }}</td>
                    <td>{{ alumno.apellidos }}</td>
                    <td>{{ alumno.email }}</td>
                    <td>{{ alumno.profesorDto.nombre }} {{ alumno.profesorDto.apellidos }}</td>
                    <td>{{ alumno.profesorDto.email }}</td>
                    <td><i class="bi bi-card-list" @click="handleDetails(alumno)"></i>
                        |
                        <i class="bi bi-pencil" @click="handleEdit(alumno)"></i>
                        |
                        <i class="bi bi-trash" @click="openModal(alumno)" data-bs-toggle="modal"
                            data-bs-target="#myModal"></i>
                    </td>
                </tr>
            </tbody>
        </table>

        <h2 v-else >No hay alumnos asignados a este profesor o no existe el alumno</h2>
    </div>


    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Eliminar Alumno</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    ¿Estás seguro de eliminar al alumno {{ alumno.nombre }} {{ alumno.apellidos }}?
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