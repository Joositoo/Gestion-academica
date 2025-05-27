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
import DetallesModuloPage from "../pages/DetallesModuloPage.vue";
import DetalllesMatriculaPage from "../pages/DetallesMatriculaPage.vue";
import DetallesPorcentajesPage from "../pages/DetallesPorcentajesPage.vue";
import DetallesCalificacionesPage from "../pages/DetallesCalificacionesPage.vue";

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
            component: IndexProfPage,
            meta: { requiresAuth: true, rol: "profesor" }
        },
        {
            path: "/indexadmin",
            name: "IndexAdmin",
            component: IndexAdminPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/account",
            name: "Account",
            component: AccountPage,
            meta: { requiresAuth: true, rol: ["profesor", "admin"] }
        },
        {
            path: "/profesores",
            name: "Profesores",
            component: ProfesoresPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/profesores/:id",
            name: "UpdateProfesor",
            component: EditarProfesorPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/profesores/details/:id",
            name: "ProfesorDetails",
            component: DetallesProfesorPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/profesores/crear",
            name: "CrearProfesor",
            component: CrearProfesorPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/alumnos",
            name: "AlumnosPage",
            component: AlumnosPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/alumnos/details/:id",
            name: "AlumnoDetails",
            component: DetallesAlumnoPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/alumnos/:id",
            name: "updateAlumnos",
            component: EditarAlumnoPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/alumnos/crear",
            name: "CrearAlumno",
            component: CrearAlumnoPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/ciclos",
            name: "CiclosPage",
            component: CiclosPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/ciclos/details/:id",
            name: "CicloDetails",
            component: DetallesCiclosPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/ciclos/:id",
            name: "updateCiclos",
            component: EditarCicloPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/ciclos/crear",
            name: "CrearCiclo",
            component: CrearCicloPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/modulos",
            name: "ModulosPage",
            component: ModulosPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/modulos/details/:id",
            name: "ModuloDetails",
            component: DetallesModuloPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/modulos/:id",
            name: "updateModulos",
            component: EditarModuloPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/modulos/crear",
            name: "CrearModulo",
            component: CrearModuloPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/matriculas",
            name: "MatriculasPage",
            component: MatriculasPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/matriculas/details/:id",
            name: "MatriculaDetails",
            component: DetalllesMatriculaPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/matriculas/crear",
            name: "CrearMatricula",
            component: CrearMatriculaPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/matriculas/:id",
            name:"updaterMatriculas",
            component: EditarMatriculaPage,
            meta: { requiresAuth: true, rol: "admin" }
        },
        {
            path: "/porcentajes",
            name: "PorcentajesPage",
            component: PorcentajesPage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        },
        {
            path: "/porcentajes/crear",
            name: "CrearPorcentaje",
            component: CrearPorcentajePage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        },
        {
            path: "/porcentajes/details/:id",
            name: "PorcentajeDetails",
            component: DetallesPorcentajesPage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        },
        {
            path: "/porcentajes/:id",
            name: "updatePorcentajes",
            component: EditarPorcentajePage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        },
        {
            path: "/calificaciones",
            name: "CalificacionesPage",
            component: CalificacionesPage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        },
        {
            path: "/calificaciones/crear",
            name: "CrearCalificacion",
            component: CrearCalificacionPage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        },
        {
            path: "/calificaciones/:id",
            name: "updateCalificaciones",
            component: EditarCalificacionPage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        },
        {
            path: "/calificaciones/details/:id",
            name: "CalificacionDetails",
            component: DetallesCalificacionesPage,
            meta: { requiresAuth: true, rol: ["admin", "profesor"] }
        }
    ]
})

router.beforeEach((to, from, next) => {
    const usuario = JSON.parse(sessionStorage.getItem("usuario"));

    if (to.meta.requiresAuth && !usuario){
        next({name: "Login"});
    }

    else if (to.meta.rol){
        const roles = Array.isArray(to.meta.rol) ? to.meta.rol : [to.meta.rol];
        if (usuario && roles.includes(usuario.rol)){
            next();
        }
        else{
            if (usuario && usuario.rol == "admin"){
                next({ name: "IndexAdmin" });
            }
            else if (usuario && usuario.rol == "profesor"){
                next({ name: "IndexProf" })
            }
            else{
                next({ name: "Login" })
            }
        }
    }
    else{
        next();
    }
});

export default router; 