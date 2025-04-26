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
import CrearCicloPage from "../pages/CrearCicloPage.vue";

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
            name: "ProfesorDetails",
            component: EditarProfesorPage
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
            path: "/ciclos",
            name: "CiclosPage",
            component: CiclosPage
        },
        {
            path: "/ciclos/:id",
            name: "CicloDetaiils",
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
            path: "/matriculas",
            name: "MatriculasPage",
            component: MatriculasPage
        },
        {
            path: "/calificaciones",
            name: "CalificacionesPage",
            component: CalificacionesPage
        }
    ]
})

export default router; 