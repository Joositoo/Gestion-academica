<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useProfesroStore } from '../stores/profesorStore';
import { useRouter } from 'vue-router';
import { useCicloStore } from '../stores/cicloStore';

const props = defineProps({
    id: Number
})


const ciclo = reactive({
    nombre: '',
});
const cicloStore = useCicloStore();
const router = useRouter();
let data = null

onMounted(async () => {
    data = await cicloStore.getCicloById(props.id);

    ciclo.nombre = data.nombre;
});

const handleSubmit = async () => {

if (!ciclo.nombre){
    let p = document.querySelector(".error");
    p.style.display = "block";
    return;
}
    
await cicloStore.updateCiclo(ciclo, props.id);
router.push("/ciclos");

}

</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Modificación de profesor</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre: </label>
                        <input type="text" v-model="ciclo.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <button type="submit" class="crear-editar-button">Modificar</button>
                    </div>
                </div>
            </form>
            <p class="error" style="display: none;">Indique el nombre del ciclo, por favor</p>
        </div>
    </div>
</template>

<style scoped>
.grid-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(1, 150px);
    gap: 10px;
    padding: 10px;
}

.grid-item {
    /*background-color: #b0c4de;*/
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 1.2em;
    border-radius: 8px;
}
</style>