<script setup>
import { onMounted, reactive } from 'vue';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import { useModuloStore } from '../stores/moduloStore';

const props = defineProps({
    id: Number
});

const modulo = reactive({
    id: ''
});

const porcentajes = reactive({
    id: '',
    modulo: '',
    ciclo: '',
    nombreProfesor: '',
    emailProfesor: '',
    ra1: '',
    ra2: '',
    ra3: '',
    ra4: '',
    ra5: '',
    ra6: '',
    ra7: '',
    ra8: '',
    ra9: '',
})

const porcentajeStore = usePorcentajeStore();
const moduloStore = useModuloStore();

onMounted(async () => {
    const id = await moduloStore.getModuloById(props.id);
    modulo.id = id.id;
    console.log(modulo.id);
    console.log(id);
    
    const data = await porcentajeStore.getPorcentajeById(modulo.id);
    porcentajes.id = data.id;
    porcentajes.modulo = data.moduloDto.nombre;
    porcentajes.ciclo = data.moduloDto.cicloDto.nombre;
    porcentajes.nombreProfesor = data.moduloDto.profesorDto.nombre+ " " +data.moduloDto.profesorDto.apellidos;
    porcentajes.emailProfesor = data.moduloDto.profesorDto.email;
    porcentajes.ra1 = data.ra1;
    porcentajes.ra2 = data.ra2;
    porcentajes.ra3 = data.ra3;
    porcentajes.ra4 = data.ra4;
    porcentajes.ra5 = data.ra5;
    porcentajes.ra6 = data.ra6;
    porcentajes.ra7 = data.ra7;
    porcentajes.ra8 = data.ra8;
    porcentajes.ra9 = data.ra9;

    console.log(porcentajes);
    
})
</script>

<template>
    <h2>Detalles de los porcentajes de {{ porcentajes.modulo }}:</h2>

    <div class="card-container">
        <div class="card">
            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ porcentajes.id }}</p>
                </div>
                <div>
                    <p><strong>Modulo perteneciente al ciclo:</strong> {{ porcentajes.ciclo }}</p>
                </div>
                <div>
                    <p><strong>Porcentajes del módulo:</strong> {{ porcentajes.modulo }}</p>
                </div>
                <div>
                    <p><strong>Impartido por el profesor:</strong> {{ porcentajes.nombreProfesor }}</p>
                </div>
                <div>
                    <p><strong>Email del profesor:</strong> {{ porcentajes.emailProfesor }}</p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 1 (RA 1): </strong> {{ porcentajes.ra1 }}<span v-if="porcentajes.ra1">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 2 (RA 2): </strong> {{ porcentajes.ra2 }}<span v-if="porcentajes.ra2">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 3 (RA 3): </strong> {{ porcentajes.ra3 }}<span v-if="porcentajes.ra3">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 4 (RA 4): </strong> {{ porcentajes.ra4 }}<span v-if="porcentajes.ra4">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 5 (RA 5): </strong> {{ porcentajes.ra5 }}<span v-if="porcentajes.ra5">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 6 (RA 6): </strong> {{ porcentajes.ra6 }}<span v-if="porcentajes.ra6">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 7 (RA 7): </strong> {{ porcentajes.ra7 }}<span v-if="porcentajes.ra7">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 8 (RA 8): </strong> {{ porcentajes.ra8 }}<span v-if="porcentajes.ra8">%</span></p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 9 (RA 9): </strong> {{ porcentajes.ra9 }}<span v-if="porcentajes.ra9">%</span></p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
h2 {
    margin: 2em 3em 1.5em 3em;
}

.card-container {
    display: flex;
    justify-content: center;
    margin-top: 2em;
}

.grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(3, 1fr);
    grid-column-gap: 0px;
    grid-row-gap: 0px;
    gap: 3em
}
</style>