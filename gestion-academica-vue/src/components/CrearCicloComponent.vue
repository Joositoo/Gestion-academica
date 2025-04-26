<script setup>
import { reactive } from 'vue';
import { useCicloStore } from '../stores/cicloStore';
import { useRouter } from 'vue-router';

let cicloStore = useCicloStore();
const router = useRouter();

let ciclo = reactive({
    nombre: "",
})

const handleSubmit = async() => {
    if (!ciclo.nombre){
        let p = document.querySelector(".error");
        p.style.display = "block";
        return;
    }
    await cicloStore.saveCiclo(ciclo);
    await cicloStore.getCiclos();
    router.push("/ciclos");
}
</script>

<template>
    <div class="card-container">
        <div class="card">
            <h2>Registro de ciclo</h2>

            <form @submit.prevent="handleSubmit">
                <div class="grid-container">
                    <div class="grid-item">
                        <label>Nombre: </label>
                        <input type="text" v-model="ciclo.nombre" class="crear-editar-input" />
                    </div>
                    <div class="grid-item">
                        <button type="submit" class="crear-editar-button">Registrar</button>
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

h2 {
    margin: 1.5em 2.5em;
}
</style>