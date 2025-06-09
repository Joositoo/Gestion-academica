<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';

let listaCiclos = ref([]);
let listaCiclosOriginal = ref([]);
let ciclo = reactive({});
let cicloId = ref(0);
let nombreFiltrado = ref("");
const ciclosStore = useCicloStore();
const router = useRouter();

onMounted(async () => {
    listaCiclos.value = await ciclosStore.getCiclos();
        listaCiclos.value.sort((a, b) => {
        const nombreA = a.nombre.toLowerCase();
        const nombreB = b.nombre.toLowerCase();
        if (nombreA < nombreB) return -1;
        if (nombreA > nombreB) return 1;
        return 0;
    });
    listaCiclosOriginal.value = listaCiclos.value;
});

const handleClick = () => {
    router.push("/ciclos/crear");
}

const openModal = async (c) => {
    ciclo.id = c.id;
    ciclo.nombre = c.nombre;

    cicloId.value = c.id;
}

const handleEdit = (c) => {
    router.push(`/ciclos/${c.id}`);
}

const handleDetails = (c) => {
    router.push(`/ciclos/details/${c.id}`);
}

const handleDelete = async () => {
    await ciclosStore.deleteCiclo(cicloId.value);
    window.location.reload();
}

const filterByName = () => {
    const filtro = nombreFiltrado.value.toLowerCase();
    listaCiclos.value = listaCiclosOriginal.value.filter((c) =>
        c.nombre.toLowerCase().includes(filtro)
    );
}

</script>

<template>
    <h2>Listado de ciclos: </h2>
    <div class="table-container">
        <div class="crear">
            <div>
                <p>Busca por nombre: </p>
                <input type="text" class="crear-editar-input" @input="filterByName" v-model="nombreFiltrado" />
            </div>
            <div><button @click="handleClick" class="crearBtn"> + Crear</button></div>
        </div>
        <table class="content-table" v-if="listaCiclos.length > 0">
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
                    <td><i class="bi bi-card-list" @click="handleDetails(ciclo)"></i>
                        |
                        <i class="bi bi-pencil" @click="handleEdit(ciclo)"></i>
                        |
                        <i class="bi bi-trash" @click="openModal(ciclo)" data-bs-toggle="modal"
                            data-bs-target="#myModal"></i>
                    </td>
                </tr>
            </tbody>
        </table>
        <h2 v-else class="noExiste">No existe el ciclo</h2>
    </div>


    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Eliminar ciclo</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    ¿Estás seguro de eliminar el ciclo {{ ciclo.nombre }}?
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
    margin-right: 15em;
}

.crear {
    margin-right: 14.5em;
    justify-content: space-between;
}

.crear div {
    display: flex;
    align-items: center;
}

.crear p {
    margin: 0;
}

i {
    cursor: pointer;
    width: 50px;
    font-size: 18px;
    margin: 0 0.5em;
}

.noExiste{
    text-decoration: none;
}

.crearBtn{
    transition: background-color 0.3s ease;
}

.crearBtn:hover{
    background-color: #59c1ff;;
}
</style>