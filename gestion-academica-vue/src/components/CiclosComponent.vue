<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';

let listaCiclos = ref([]);
let ciclo = reactive({});
let cicloId = ref(0);
const ciclosStore = useCicloStore();
const router = useRouter();

onMounted(async () => {
    listaCiclos.value = await ciclosStore.getCiclos();
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

const handleDelete = async () => {
    await ciclosStore.deleteCiclo(cicloId.value);
    window.location.reload();
}

</script>

<template>
    <h2>Historial de ciclos: </h2>
    <div class="table-container">
        <div class="crear">
            <button @click="handleClick"> + Crear</button>
        </div>
        <table class="content-table">
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
                    <td><i class="bi bi-pencil" @click="handleEdit(ciclo)"></i>
                        |
                        <i class="bi bi-trash" @click="openModal(ciclo)" data-bs-toggle="modal"
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
    margin-right: 11em;
}

i {
    cursor: pointer;
    width: 50px;
    font-size: 18px;
    margin: 0 0.5em;
}
</style>