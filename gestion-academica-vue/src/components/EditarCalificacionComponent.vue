<script setup>
import { computed, onMounted, reactive, ref } from 'vue';
import { useCalificacionStore } from '../stores/calificacionStore';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import LeftArrowComponent from './LeftArrowComponent.vue';
import { useRouter } from 'vue-router';

const props = defineProps({
    id: Number
})

const router = useRouter();
const calificacionStore = useCalificacionStore();
const porcentajeStore = usePorcentajeStore();
const calificacion = reactive({
    nombreModulo: '',
    emailAlumno: '',
    nombreAlumno: '',
    apellidosAlumno: '',
    nombreProfesor: '',
    apellidosProfesor: '',
    ra1: null,
    ra2: null,
    ra3: null,
    ra4: null,
    ra5: null,
    ra6: null,
    ra7: null,
    ra8: null,
    ra9: null,
})
const listaPorcentajes = ref([]);
const porcentaje = reactive({});

onMounted(async () => {
    const data = await calificacionStore.getCalificacionById(props.id);
    calificacion.nombreModulo = data.moduloDto.nombre;
    calificacion.nombreAlumno = data.alumnoDto.nombre;
    calificacion.apellidosAlumno = data.alumnoDto.apellidos;
    calificacion.nombreProfesor = data.moduloDto.profesorDto.nombre;
    calificacion.apellidosProfesor = data.moduloDto.profesorDto.apellidos;
    calificacion.emailAlumno = data.alumnoDto.email;
    calificacion.ra1 = data.ra1;
    calificacion.ra2 = data.ra2;
    calificacion.ra3 = data.ra3;
    calificacion.ra4 = data.ra4;
    calificacion.ra5 = data.ra5;
    calificacion.ra6 = data.ra6;
    calificacion.ra7 = data.ra7;
    calificacion.ra8 = data.ra8;
    calificacion.ra9 = data.ra9;

    listaPorcentajes.value = await porcentajeStore.getPorcentajes();
    for (const p of listaPorcentajes.value) {
        if (p.nombreModulo === calificacion.nombreModulo) {
            Object.assign(porcentaje, p);
            break;
        }
    }
});

const notasParciales = {};
for (let i = 1; i <= 9; i++) {
    notasParciales[`ra${i}`] = computed(() => {
        const nota = calificacion[`ra${i}`];
        const porc = porcentaje[`ra${i}`];

        if (nota != null && porc != null) {
            return ((nota * porc) / 100).toFixed(2);
        }
        return `0.00`;
    });
}

const handleSubmit = async () => {
    let pError500 = document.getElementById("error500");
    const modificaCalificacion = reactive({
        nombreModulo: '',
        emailAlumno: '',
        ra1: null,
        ra2: null,
        ra3: null,
        ra4: null,
        ra5: null,
        ra6: null,
        ra7: null,
        ra8: null,
        ra9: null,
    });

    modificaCalificacion.nombreModulo = calificacion.nombreModulo;
    modificaCalificacion.emailAlumno = calificacion.emailAlumno;
    modificaCalificacion.ra1 = calificacion.ra1;
    modificaCalificacion.ra2 = calificacion.ra2;
    modificaCalificacion.ra3 = calificacion.ra3;
    modificaCalificacion.ra4 = calificacion.ra4;
    modificaCalificacion.ra5 = calificacion.ra5;
    modificaCalificacion.ra6 = calificacion.ra6;
    modificaCalificacion.ra7 = calificacion.ra7;
    modificaCalificacion.ra8 = calificacion.ra8;
    modificaCalificacion.ra9 = calificacion.ra9;

    if (modificaCalificacion.ra1 < 0 || modificaCalificacion.ra1 > 10 ||
        modificaCalificacion.ra2 < 0 || modificaCalificacion.ra2 > 10 ||
        modificaCalificacion.ra3 < 0 || modificaCalificacion.ra3 > 10 ||
        modificaCalificacion.ra4 < 0 || modificaCalificacion.ra4 > 10 ||
        modificaCalificacion.ra5 < 0 || modificaCalificacion.ra5 > 10 ||
        modificaCalificacion.ra6 < 0 || modificaCalificacion.ra6 > 10 ||
        modificaCalificacion.ra7 < 0 || modificaCalificacion.ra7 > 10 ||
        modificaCalificacion.ra8 < 0 || modificaCalificacion.ra8 > 10 ||
        modificaCalificacion.ra9 < 0 || modificaCalificacion.ra9 > 10) {
        pError500.style.display = "block";
        return;
    } 
    else {
        pError500.style.display = "none";
    }

    if (!calificacion.ra1 || !calificacion.ra2 || !calificacion.ra3 ||
        !calificacion.ra4 || !calificacion.ra5 || !calificacion.ra6 ||
        !calificacion.ra7 || !calificacion.ra8 || !calificacion.ra9) {
        alert("Las calificaciones vacías no se tendrán en cuanta al actualizar");
    }

    try{
        await calificacionStore.updateCalificacion(modificaCalificacion, props.id);
        router.push("/calificaciones");
    }
    catch{
        pError500.style.display = "block";
        return;
    }
}
</script>

<template>
    <LeftArrowComponent path="calificaciones" />

    <div class="card-container">
        <div class="card">
            <h2>Modificación de calificaciones</h2>

            <form @submit.prevent="handleSubmit" class="form-grid">
                <div class="grid-item">
                    <label>Módulo</label>
                    <p>{{ calificacion.nombreModulo }}</p>
                </div>

                <div class="grid-item">
                    <label>Alumno</label>
                    <p>{{ calificacion.nombreAlumno }} {{ calificacion.apellidosAlumno }}</p>
                </div>

                <div class="grid-item">
                    <label>Profesor</label>
                    <p>{{ calificacion.nombreProfesor }} {{ calificacion.apellidosProfesor }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 1</label>
                    <p>{{ porcentaje.ra1 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 1</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra1">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 1</label>
                    <p>{{ notasParciales.ra1 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 2</label>
                    <p>{{ porcentaje.ra2 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 2</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra2">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 2</label>
                    <p>{{ notasParciales.ra2 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 3</label>
                    <p>{{ porcentaje.ra3 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 3</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra3">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 3</label>
                    <p>{{ notasParciales.ra3 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 4</label>
                    <p>{{ porcentaje.ra4 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 4</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra4">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 4</label>
                    <p>{{ notasParciales.ra4 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 5</label>
                    <p>{{ porcentaje.ra5 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 5</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra5">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 5</label>
                    <p>{{ notasParciales.ra5 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 6</label>
                    <p>{{ porcentaje.ra6 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 6</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra6">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 6</label>
                    <p>{{ notasParciales.ra6 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 7</label>
                    <p>{{ porcentaje.ra7 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 7</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra7">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 7</label>
                    <p>{{ notasParciales.ra7 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 8</label>
                    <p>{{ porcentaje.ra8 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 8</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra8">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 8</label>
                    <p>{{ notasParciales.ra8 }}</p>
                </div>

                <div class="grid-item">
                    <label>Porcentaje del RA 9</label>
                    <p>{{ porcentaje.ra9 }}%</p>
                </div>
                <div class="grid-item">
                    <label>Calificación del RA 9</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.ra9">
                </div>
                <div class="grid-item">
                    <label>Nota del RA 9</label>
                    <p>{{ notasParciales.ra9 }}</p>
                </div>

                <div class="grid-item full-width">
                    <label>Nota final: {{ (
                        Number(notasParciales.ra1.value) +
                        Number(notasParciales.ra2.value) +
                        Number(notasParciales.ra3.value) +
                        Number(notasParciales.ra4.value) +
                        Number(notasParciales.ra5.value) +
                        Number(notasParciales.ra6.value) +
                        Number(notasParciales.ra7.value) +
                        Number(notasParciales.ra8.value) +
                        Number(notasParciales.ra9.value)
                    ).toFixed(2) }}</label>

                    <p class="error" style="display: none;" id="error500">Las calificaciones deben estar comprendidas entre 0 y 10</p>
                </div>

                <div class="full-width">
                    <button type="submit" class="crear-editar-button">Modificar</button>                    
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped>
.form-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 1rem;
    align-items: start;
}

.full-width {
    grid-column: 1 / -1;
    /* Hace que ocupe las 3 columnas */
    text-align: right;
}

p {
    margin-top: 1.5em;
}

label {
    font-weight: bold;
}

div {
    margin-bottom: 1.5em;
}

.crear-editar-button{
    padding: 18px;
}
</style>
