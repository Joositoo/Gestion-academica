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
const calificacion = reactive({
    nombreModulo: '',
    emailAlumno: '',
    nombreAlumno: '',
    apellidosAlumno: '',
    nombreProfesor: '',
    apellidosProfesor: '',
    descripcionRa: '',
    idRa: null,
    porcentajeRa: null,
    nota: null
})
const porcentaje = reactive({});

onMounted(async () => {
    const data = await calificacionStore.getCalificacionById(props.id);
    calificacion.nombreModulo = data.moduloDto.nombre;
    calificacion.nombreAlumno = data.alumnoDto.nombre;
    calificacion.apellidosAlumno = data.alumnoDto.apellidos;
    calificacion.nombreProfesor = data.moduloDto.profesorDto.nombre;
    calificacion.apellidosProfesor = data.moduloDto.profesorDto.apellidos;
    calificacion.emailAlumno = data.alumnoDto.email;
    calificacion.descripcionRa = data.porcentajesRaDto.descripcion;
    calificacion.idRa = data.porcentajesRaDto.id;
    calificacion.porcentajeRa = data.porcentajesRaDto.porcentaje;
    calificacion.nota = data.nota;

    console.log(calificacion);

});



const handleSubmit = async () => {
    let pError500 = document.getElementById("error500");
    let pVacio = document.getElementById("vacio");

    const modificaCalificacion = reactive({
        nombreModulo: calificacion.nombreModulo,
        emailAlumno: calificacion.emailAlumno,
        idRa: calificacion.idRa,
        nota: calificacion.nota
    });

    console.log(modificaCalificacion)
    if (modificaCalificacion.nota < 0 || modificaCalificacion.nota > 10) {
        pVacio.style.display = "none";
        pError500.style.display = "block";
        return;
    }
    else {
        pError500.style.display = "none";
    }

    if (!calificacion.nota) {
        pError500.style.display = "none";
        pVacio.style.display = "block";
        return;
    }
    else {
        pVacio.style.display = "none";
    }

    try {
        await calificacionStore.updateCalificacion(modificaCalificacion, props.id);
        router.push("/calificaciones");
    }
    catch {
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
                    <label>Resultado de Aprendizaje</label>
                    <p>{{ calificacion.descripcionRa }} ({{ calificacion.porcentajeRa }}%)</p>
                </div>
                <div class="grid-item">
                    <label>Nota</label>
                    <input type="text" class="crear-editar-input" v-model.number="calificacion.nota">
                </div>
                <div class="grid-item">
                    <label>Nota del RA {{ calificacion.descripcionRa }}</label>
                    <p>{{ ((calificacion.porcentajeRa / 100) * calificacion.nota).toFixed(2) }}</p>
                </div>

                <div class="full-width">
                    <p id="error500" style="display: none; color: red;">La nota debe estar comprendida entre 0 y 10</p>
                    <p id="vacio" style="display: none; color: red;">Indique la nueva nota, por favor</p>
                </div>
                <div class="full-width">
                    <p id="error500" style="display: none; color: red;">Nota comprendida entre 0 y 10</p>
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

.crear-editar-button {
    padding: 18px;
}
</style>
