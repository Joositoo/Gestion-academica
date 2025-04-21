import { defineStore } from "pinia";
import { reactive } from "vue";

export const useCalificacionStore = defineStore("calificacion", () => {
    const calificaciones = reactive([]);

    const calificacionStored = sessionStorage.getItem("calificaciones");
    if (calificacionStored){
        const calificacionJson = JSON.parse(calificacionStored);
        Object.assign(calificaciones, calificacionJson);
    }

    const getCalificaciones = async () => {
        const API_URL = "http://localhost:8080/calificaciones";

        const response = await fetch(API_URL, {
            method: "GET",
            headers:{
                "Content-type": "application/json",
            },
        })

        if (!response.ok){
            throw new Error("Error al obtener las calificaciones")
        }

        const data = await response.json();

        calificaciones.push(...data);

        return data;
    }

    return {
        calificaciones,
        getCalificaciones
    }

})