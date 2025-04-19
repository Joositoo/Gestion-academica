import { createRouter, createWebHistory } from "vue-router";
import LoginPage from "../pages/LoginPage.vue";
import IndexProfPage from "../pages/IndexProfPage.vue";
import IndexAdminPage from "../pages/IndexAdminPage.vue";

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
        }
    ]
})

export default router; 