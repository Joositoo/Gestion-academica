import { createRouter, createWebHistory } from "vue-router";
import LoginPage from "../pages/LoginPage.vue";
import IndexProfPage from "../pages/IndexProfPage.vue";
import IndexAdminPage from "../pages/IndexAdminPage.vue";
import AccountPage from "../pages/AccountPage.vue";
import ProfesoresPage from "../pages/ProfesoresPage.vue";
import AlumnosPage from "../pages/AlumnosPage.vue";
import CiclosPage from "../pages/CiclosPage.vue";
import ModulosPage from "../pages/ModulosPage.vue";
import MatriculasPage from "../pages/MatriculasPage.vue";
import CalificacionesPage from "../pages/CalificacionesPage.vue";
import CrearProfesorPage from "../pages/CrearProfesorPage.vue";
import EditarProfesorPage from "../pages/EditarProfesorPage.vue";
import EditarCicloPage from "../pages/EditarCicloPage.vue";
import EditarModuloPage from "../pages/EditarModuloPage.vue";
import EditarAlumnoPage from "../pages/EditarAlumnoPage.vue";
import CrearAlumnoPage from "../pages/CrearAlumnoPage.vue";
import CrearCicloPage from "../pages/CrearCicloPage.vue";
import CrearModuloPage from "../pages/CrearModuloPage.vue";
import CrearMatriculaPage from "../pages/CrearMatriculaPage.vue";
import EditarMatriculaPage from "../pages/EditarMatriculaPage.vue";
import EditarCalificacionPage from "../pages/EditarCalificacionPage.vue";
import CrearCalificacionPage from "../pages/CrearCalificacionPage.vue";
import PorcentajesPage from "../pages/PorcentajesPage.vue";
import CrearPorcentajePage from "../pages/CrearPorcentajePage.vue";
import EditarPorcentajePage from "../pages/EditarPorcentajePage.vue";
import DetallesProfesorPage from "../pages/DetallesProfesorPage.vue";
import DetallesAlumnoPage from "../pages/DetallesAlumnoPage.vue";
import DetallesCiclosPage from "../pages/DetallesCiclosPage.vue";


const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "Login",
            component: LoginPage
        },
        {
            path: "/indexprof",
            name: "IndexProf",
            component: IndexProfPage
        },
        {
            path: "/indexadmin",
            name: "IndexAdmin",
            component: IndexAdminPage
        },
        {
            path: "/account",
            name: "Account",
            component: AccountPage
        },
        {
            path: "/profesores",
            name: "Profesores",
            component: ProfesoresPage
        },
        {
            path: "/profesores/:id",
            name: "UpdateProfesor",
            component: EditarProfesorPage
        },
        {
            path: "/profesores/details/:id",
            name: "ProfesorDetails",
            component: DetallesProfesorPage
        },
        {
            path: "/profesores/crear",
            name: "CrearProfesor",
            component: CrearProfesorPage
        },
        {
            path: "/alumnos",
            name: "AlumnosPage",
            component: AlumnosPage
        },
        {
            path: "/alumnos/details/:id",
            name: "AlumnoDetails",
            component: DetallesAlumnoPage
        },
        {
            path: "/alumnos/:id",
            name: "updateAlumnos",
            component: EditarAlumnoPage
        },
        {
            path: "/alumnos/crear",
            name: "CrearAlumno",
            component: CrearAlumnoPage
        },
        {
            path: "/ciclos",
            name: "CiclosPage",
            component: CiclosPage
        },
        {
            path: "/ciclos/details/:id",
            name: "CicloDetails",
            component: DetallesCiclosPage
        },
        {
            path: "/ciclos/:id",
            name: "updateCiclos",
            component: EditarCicloPage
        },
        {
            path: "/ciclos/crear",
            name: "CrearCiclo",
            component: CrearCicloPage
        },
        {
            path: "/modulos",
            name: "ModulosPage",
            component: ModulosPage
        },
        {
            path: "/modulos/:id",
            name: "ModuloDetails",
            component: EditarModuloPage
        },
        {
            path: "/modulos/crear",
            name: "CrearModulo",
            component: CrearModuloPage
        },
        {
            path: "/matriculas",
            name: "MatriculasPage",
            component: MatriculasPage
        },
        {
            path: "/matriculas/crear",
            name: "CrearMatricula",
            component: CrearMatriculaPage
        },
        {
            path: "/matriculas/:id",
            name:"MatriculaDetails",
            component: EditarMatriculaPage
        },
        {
            path: "/porcentajes",
            name: "PorcentajesPage",
            component: PorcentajesPage
        },
        {
            path: "/porcentajes/crear",
            name: "CrearPorcentaje",
            component: CrearPorcentajePage
        },
        {
            path: "/porcentajes/:id",
            name: "PorcentajeDetails",
            component: EditarPorcentajePage
        },
        {
            path: "/calificaciones",
            name: "CalificacionesPage",
            component: CalificacionesPage
        },
        {
            path: "/calificaciones/crear",
            name: "CrearCalificacion",
            component: CrearCalificacionPage
        },
        {
            path: "/calificaciones/:id",
            name: "CalificacionDetails",
            component: EditarCalificacionPage
        }
    ]
})

export default router; 