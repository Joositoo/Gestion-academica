import { defineStore } from "pinia";
import { reactive } from "vue";

const url = "http://localhost:8080/";

export const useCalificacionStore = defineStore("calificacion", () => {
    const calificaciones = reactive([]);

    const calificacionStored = sessionStorage.getItem("calificaciones");
    if (calificacionStored){
        const calificacionJson = JSON.parse(calificacionStored);
        Object.assign(calificaciones, calificacionJson);
    }

    const getCalificaciones = async () => {
        const API_URL = url+ "calificaciones";

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

    const getCalificacionById = async (id) => {
        try {
            const response = await fetch(url+ `calificaciones/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener la calificación");
            }
            const calificacion = await response.json();
            return calificacion;
        } catch (error) {
            console.error("Error al obtener la calificacion:", error);
        }
    };

    const saveCalificacion = async (file) => {
        try {
            const formData = new FormData();
            formData.append("file", file)

            const response = await fetch(url+ "calificaciones", {
                method: "POST",
                body: formData,
            });

            if (!response.ok) {
                throw new Error("Error al guardar las calificaciones");
            }

            await getCalificaciones();

        } catch (error) {
            console.error("Error al guardar las calificaciones:", error);
            throw error;
        }
    };

    const updateCalificacion = async (calificacion, id) => {
        try {
            const response = await fetch(url+ `calificaciones/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(calificacion),
            });
    
            if (!response.ok) {
                throw new Error("Error al actualizar la calificación");
            }
    
            await getCalificaciones(); 
            console.log(`Calificación actualizado correctamente.`);
    
        } catch (error) {
            console.error("Error al actualizar la calificación:", error);
            throw error;
        }
    };

    const deleteCalificacion = async (id) => {
        try {
            const response = await fetch(url+ `calificaciones/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar la calificación");
            }

            await getCalificaciones();
    
            console.log(`Calificación con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error("Error al eliminar la calificacióon:", error);
        }
    };

    return {
        calificaciones,
        getCalificaciones,
        getCalificacionById,
        saveCalificacion,
        updateCalificacion,
        deleteCalificacion
    }

})