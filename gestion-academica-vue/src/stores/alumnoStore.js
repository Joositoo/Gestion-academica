import { defineStore } from "pinia";
import { ref } from "vue";

const url = "http://localhost:8080/";

export const useAlumnoStore = defineStore("alumno", () => {
    const alumnos = ref([]);

    const getAlumnos = async () => {
        try {
            const response = await fetch(url+ "alumnos");
            if (!response.ok) {
                throw new Error("Ha ocurrido un error");
            }
            const data = await response.json();
            alumnos.value = data;

            return data;
        } catch (error) {
            console.error("Error al obtener alumnos:", error);
        }
    };

    const getAlumnoById = async (id) => {
        try {
            const response = await fetch(url+ `alumnos/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener el alumno");
            }
            const alumno = await response.json();
            return alumno;
        } catch (error) {
            console.error("Error al obtener al alumno:", error);
        }
    };

    const saveAlumno = async (file) => {
        try {
            const formData = new FormData();
            formData.append("file", file)

            const response = await fetch(url+ "alumnos", {
                method: "POST",
                body: formData,
            });

            if (!response.ok) {
                throw new Error("Error al guardar al alumno");
            }

            await getAlumnos();

        } catch (error) {
            console.error("Error al guardar al alumno:", error);
            throw error;
        }
    };

    const updateAlumno = async (alumno, id) => {
        try {
            const response = await fetch(url+ `alumnos/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(alumno),
            });
    
            if (!response.ok) {
                throw new Error("Error al actualizar al alumno");
            }
    
            await getAlumnos(); 
            console.log(`Alumno actualizado correctamente.`);
    
        } catch (error) {
            console.error("Error al actualizar al alumno:", error);
            throw error;
        }
    };

    const deleteAlumno = async (id) => {
        try {
            const response = await fetch(url+ `alumnos/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar al alumno");
            }

            await getAlumnos();
    
            console.log(`Alumno con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error("Error al eliminar al alumno:", error);
        }
    };

    return {
        alumnos,
        getAlumnos,
        getAlumnoById,
        saveAlumno,
        updateAlumno,
        deleteAlumno
    }
});