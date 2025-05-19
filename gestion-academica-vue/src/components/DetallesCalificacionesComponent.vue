<script setup>
import * as XLSX from 'xlsx';
import { ref } from 'vue';
import { onMounted, reactive } from 'vue';
import { useCalificacionStore } from '../stores/calificacionStore';
import { usePorcentajeStore } from '../stores/porcentajeStore';
import LeftArrowComponent from './LeftArrowComponent.vue';

const props = defineProps({
    id: Number
})

const calificaciones = reactive({
    id: '',
    modulo: '',
    ciclo: '',
    nombreProfesor: '',
    emailProfesor: '',
    nombreAlumno: '',
    emailAlumno: '',
    ra1: "",
    ra2: "",
    ra3: "",
    ra4: "",
    ra5: "",
    ra6: "",
    ra7: "",
    ra8: "",
    ra9: "",
})

const porcentajes = reactive({

    ra1: "",
    ra2: "",
    ra3: "",
    ra4: "",
    ra5: "",
    ra6: "",
    ra7: "",
    ra8: "",
    ra9: "",
})

const notaFinal = reactive({
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

const calificacionesStore = useCalificacionStore();
const porcentajesStore = usePorcentajeStore();

onMounted(async () => {
    const data = await calificacionesStore.getCalificacionById(props.id);
    calificaciones.id = data.id;
    calificaciones.modulo = data.moduloDto.nombre;
    calificaciones.ciclo = data.moduloDto.cicloDto.nombre;
    calificaciones.nombreProfesor = data.moduloDto.profesorDto.nombre + " " + data.moduloDto.profesorDto.apellidos;
    calificaciones.emailProfesor = data.moduloDto.profesorDto.email;
    calificaciones.nombreAlumno = data.alumnoDto.nombre + " " + data.alumnoDto.apellidos;
    calificaciones.emailAlumno = data.alumnoDto.email;
    calificaciones.ra1 = data.ra1;
    calificaciones.ra2 = data.ra2;
    calificaciones.ra3 = data.ra3;
    calificaciones.ra4 = data.ra4;
    calificaciones.ra5 = data.ra5;
    calificaciones.ra6 = data.ra6;
    calificaciones.ra7 = data.ra7;
    calificaciones.ra8 = data.ra8;
    calificaciones.ra9 = data.ra9;


    const listaPorcentajes = await porcentajesStore.getPorcentajes();

    for (const p of listaPorcentajes) {
        if (calificaciones.modulo == p.moduloDto.nombre) {
            porcentajes.ra1 = p.ra1;
            porcentajes.ra2 = p.ra2;
            porcentajes.ra3 = p.ra3;
            porcentajes.ra4 = p.ra4;
            porcentajes.ra5 = p.ra5;
            porcentajes.ra6 = p.ra6;
            porcentajes.ra7 = p.ra7;
            porcentajes.ra8 = p.ra8;
            porcentajes.ra9 = p.ra9;
        }
    }

    notaFinal.ra1 = (calificaciones.ra1 * porcentajes.ra1) / 100;
    notaFinal.ra2 = (calificaciones.ra2 * porcentajes.ra2) / 100;
    notaFinal.ra3 = (calificaciones.ra3 * porcentajes.ra3) / 100;
    notaFinal.ra4 = (calificaciones.ra4 * porcentajes.ra4) / 100;
    notaFinal.ra5 = (calificaciones.ra5 * porcentajes.ra5) / 100;
    notaFinal.ra6 = (calificaciones.ra6 * porcentajes.ra6) / 100;
    notaFinal.ra7 = (calificaciones.ra7 * porcentajes.ra7) / 100;
    notaFinal.ra8 = (calificaciones.ra8 * porcentajes.ra8) / 100;
    notaFinal.ra9 = (calificaciones.ra9 * porcentajes.ra9) / 100;
})

const generateExcel = async () => {

    const sumNotas = notaFinal.ra1 + notaFinal.ra2 + notaFinal.ra3 + notaFinal.ra4 + notaFinal.ra5 + notaFinal.ra6 + notaFinal.ra7 + notaFinal.ra8 + notaFinal.ra9;

    const data = [
        ["ID", "Profesor", "Email del profesor", "Alumno", "Email del alumno", "Módulo"],
        [calificaciones.id, calificaciones.nombreProfesor, calificaciones.emailProfesor, calificaciones.nombreAlumno, calificaciones.emailAlumno, calificaciones.modulo],
        [],
        ["RA", "Nota", "Porcentaje", "Nota Final"],
        ["RA1", calificaciones.ra1, porcentajes.ra1, notaFinal.ra1],
        ["RA2", calificaciones.ra2, porcentajes.ra2, notaFinal.ra2],
        ["RA3", calificaciones.ra3, porcentajes.ra3, notaFinal.ra3],
        ["RA4", calificaciones.ra4, porcentajes.ra4, notaFinal.ra4],
        ["RA5", calificaciones.ra5, porcentajes.ra5, notaFinal.ra5],
        ["RA6", calificaciones.ra6, porcentajes.ra6, notaFinal.ra6],
        ["RA7", calificaciones.ra7, porcentajes.ra7, notaFinal.ra7],
        ["RA8", calificaciones.ra8, porcentajes.ra8, notaFinal.ra8],
        ["RA9", calificaciones.ra9, porcentajes.ra9, notaFinal.ra9],
        ["Nota final", "", "", sumNotas]
    ];


    const worksheet = XLSX.utils.aoa_to_sheet(data);
    worksheet['!autofilter'] = { ref: "A4:D13" }

    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Calificación");
    XLSX.writeFile(workbook, `Calificaciones${calificaciones.nombreAlumno.replace(/\s/g, "")}.xlsx`);
}
</script>

<template>
    <LeftArrowComponent path="calificaciones" />

    <h2>Detalles de las calificaciones de {{ calificaciones.nombreAlumno }} en {{ calificaciones.modulo }}:</h2>

    <div class="card-container">
        <div class="card">
            <div class="grid">
                <div>
                    <p><strong>ID:</strong> {{ calificaciones.id }}</p>
                </div>
                <div>
                    <p><strong>El módulo pertenece al ciclo:</strong> {{ calificaciones.ciclo }}</p>
                </div>
                <div>
                    <p><strong>Módulo:</strong> {{ calificaciones.modulo }}</p>
                </div>
                <div>
                    <p><strong>Módulo impartido por:</strong> {{ calificaciones.nombreProfesor }}</p>
                </div>
                <div>
                    <p><strong>Email del profesor:</strong> {{ calificaciones.emailProfesor }}</p>
                </div>
                <div>
                    <p><strong>Alumno:</strong> {{ calificaciones.nombreAlumno }}</p>
                </div>
                <div>
                    <p><strong>Email del alumno:</strong> {{ calificaciones.emailAlumno }}</p>
                </div>
                <div>
                    <p><strong>Resultado de apendizaje 1 (RA 1): </strong>
                        <span v-if="calificaciones.ra1">{{ calificaciones.ra1 }} x {{ porcentajes.ra1 }}% = <strong>{{
                            notaFinal.ra1 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 2 (RA 2): </strong>
                        <span v-if="calificaciones.ra2">{{ calificaciones.ra2 }} x {{ porcentajes.ra2 }}% = <strong>{{
                            notaFinal.ra2 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 3 (RA 3): </strong>
                        <span v-if="calificaciones.ra3">{{ calificaciones.ra3 }} x {{ porcentajes.ra3 }}% = <strong>{{
                            notaFinal.ra3 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 4 (RA 4): </strong>
                        <span v-if="calificaciones.ra4">{{ calificaciones.ra4 }} x {{ porcentajes.ra4 }}% = <strong>{{
                            notaFinal.ra4 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 5 (RA 5): </strong>
                        <span v-if="calificaciones.ra5">{{ calificaciones.ra5 }} x {{ porcentajes.ra5 }}% = <strong>{{
                            notaFinal.ra5 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 6 (RA 6): </strong>
                        <span v-if="calificaciones.ra6">{{ calificaciones.ra6 }} x {{ porcentajes.ra6 }}% = <strong>{{
                            notaFinal.ra6 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 7 (RA 7): </strong>
                        <span v-if="calificaciones.ra7">{{ calificaciones.ra7 }} x {{ porcentajes.ra7 }}% = <strong>{{
                            notaFinal.ra7 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 8 (RA 8): </strong>
                        <span v-if="calificaciones.ra8">{{ calificaciones.ra8 }} x {{ porcentajes.ra8 }}% = <strong>{{
                            notaFinal.ra8 }}</strong></span>
                    </p>
                </div>
                <div>
                    <p><strong>Resultado de aprendizaje 9 (RA 9): </strong>
                        <span v-if="calificaciones.ra9">{{ calificaciones.ra9 }} x {{ porcentajes.ra9 }}% = <strong>{{
                            notaFinal.ra9 }}</strong></span>
                    </p>
                </div>

                <div class="full-width"><button class="crear-editar-input excel" @click="generateExcel">Generar excel</button>
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

.full-width {
    grid-column: 1 / span 2;
    display: flex;
    justify-content: center;
}
</style>