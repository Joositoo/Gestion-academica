import { defineStore } from "pinia";
import { ref } from "vue";

export const useProfesroStore = defineStore("profesor", () => {
    const profesores = ref([]);

    const getProfesores = async () => {
        try {
            const response = await fetch("http://localhost:8080/profesores");
            if (!response.ok) {
                throw new Error("Ha ocurrido un error");
            }
            const data = await response.json();
            profesores.value = data;

            return data;
        } catch (error) {
            console.error("Error al obtener profesores:", error);
        }
    };

    const saveProfesor = async (profesor) => {
        try {
            const response = await fetch("http://localhost:8080/profesores", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(profesor),
            });

            if (!response.ok) {
                throw new Error("Error al guardar el profesor");
            }

            await getProfesores();

        } catch (error) {
            console.error("Error al guardar el profesor:", error);
        }
    }

    const deleteProfesor = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/profesores/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar el profesor");
            }

            await getProfesores();
    
            console.log(`Profesor con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error("Error al eliminar el profesor:", error);
        }
    };

    return {
        profesores,
        getProfesores,
        saveProfesor,
        deleteProfesor
    }
});