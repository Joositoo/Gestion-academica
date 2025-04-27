<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useModuloStore } from '../stores/moduloStore';
import { useRouter } from 'vue-router';

let listaModulos = ref([]);
let modulo = reactive({});
let moduloId = ref(0);
const moduloStore = useModuloStore();
const router = useRouter();

onMounted(async () => {
    listaModulos.value = await moduloStore.getModulos();
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

const handleDelete = async () => {
    await moduloStore.deleteModulo(moduloId.value);
    window.location.reload();
}

</script>

<template>
            <h2>Historial de módulos: </h2>
        <div class="table-container">
            <div class="crear">
            <button @click="handleClick"> + Crear</button>
        </div>    
            <table class="content-table">
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
                    <tr v-for="modulo in listaModulos" :key="modulo.id">
                        <td>{{ modulo.id }}</td>
                        <td>{{ modulo.cicloDto.nombre }}</td>
                        <td>{{ modulo.nombre }}</td>
                        <td> {{ modulo.profesorDto.nombre }}  {{ modulo.profesorDto.apellidos }}</td>
                        <td>{{ modulo.profesorDto.email }}</td>
                        
                        <td><i class="bi bi-pencil" @click="handleEdit(modulo)"></i>
                        |
                        <i class="bi bi-trash" @click="openModal(modulo)" data-bs-toggle="modal"
                            data-bs-target="#myModal"></i></td>
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
    margin-right: 3.5em;
}

i {
    cursor: pointer;
    width: 50px;
    font-size: 18px;
    margin: 0 0.5em;
}
</style>