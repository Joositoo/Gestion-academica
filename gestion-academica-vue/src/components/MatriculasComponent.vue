<script setup>
import { onMounted, ref } from 'vue';
import { useMatriculaStore } from '../stores/matriculaStore';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';

const matriculaStore = useMatriculaStore();
const cicloStore = useCicloStore();
const router = useRouter();

let listaMatriculas = ref([]);
let listaMatriculasOriginal = ref([]);
let listaFiltrada = ref([]);
let listaCiclos = ref([]);
let matriculaId = ref(0);
let nombreMatricula = ref("");
let cicloMatricula = ref("");
let cicloSeleccionado = ref("0");
let nombreFiltrado = ref("");

onMounted(async () => {
    listaMatriculas.value = await matriculaStore.getMatriculas();
    listaMatriculasOriginal.value = listaMatriculas.value;
    listaCiclos.value = await cicloStore.getCiclos();
    listaFiltrada.value = listaMatriculas.value;    
    listaFiltrada.value.sort((a, b) => {
        const cicloA = a.cicloDto.nombre.toLowerCase();
        const cicloB = b.cicloDto.nombre.toLowerCase();
        const nombreA = a.alumnoDto.nombre.toLowerCase();
        const nombreB = b.alumnoDto.nombre.toLowerCase();

        if (cicloA < cicloB) return -1;
        if (cicloA > cicloB) return 1;

        return nombreA.localeCompare(nombreB);
    });
});

const handleClick = () => {
    router.push("/matriculas/crear");
}

const openModal = async (m) => {
    matriculaId.value = m.id;
    nombreMatricula.value = m.alumnoDto.nombre + " " + m.alumnoDto.apellidos;
    cicloMatricula.value = m.cicloDto.nombre;
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

const filterByName = () => {
    filtrarMatriculas();
}

const handleFilter = (e) => {
    filtrarMatriculas();    
}

const quitarTildes = (texto) => {
    return texto.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
};

const filtrarMatriculas = () => {
    const filtroNombre = quitarTildes(nombreFiltrado.value.toLowerCase());
    const filtroCiclo = cicloSeleccionado.value;

    listaFiltrada.value = listaMatriculasOriginal.value.filter((m) => {
        const nombreAlumno = quitarTildes(m.alumnoDto.nombre.toLowerCase());
        const coincideNombre = nombreAlumno.includes(filtroNombre);
        const coincideCiclo = filtroCiclo === "0" || m.cicloDto.nombre === filtroCiclo;
        return coincideNombre && coincideCiclo;
    });
};

</script>

<template>
    <h2>Listado de matrículas: </h2>
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
                <p>Busca por nombre: </p>
                <input type="text" class="crear-editar-input" @input="filterByName" v-model="nombreFiltrado" />
            </div>
            <div><button @click="handleClick" class="crearBtn"> + Crear</button></div>
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

        <h2 v-else>No hay alumnos matriculados en este ciclo o no existe el alumno</h2>
    </div>


    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Eliminar matrícula</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    ¿Estás seguro de eliminar la matrícula de {{ nombreMatricula }} en {{ cicloMatricula }}?
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

.crearBtn{
    transition: background-color 0.3s ease;
}

.crearBtn:hover{
    background-color: #59c1ff;;
}
</style>