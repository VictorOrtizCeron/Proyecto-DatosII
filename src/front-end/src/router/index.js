import {createRouter, createWebHistory} from "vue-router";


import error from "@/components/error.vue";
import databaseView from "@/components/database-view.vue";
import loginForm from "@/components/login-form.vue";

const routes=[
    {
        path:"/",
        name: "loginForm",
        component: loginForm
    },
    {
        path:"/error",
        name: "error",
        component: error
    },
    {
        path:"/database",
        name: "database-view",
        component: databaseView
    },
]

const router = createRouter({
    history:createWebHistory(process.env.BASE_URL),
    routes,
})

 export default router