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

    const getProfesorById = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/profesores/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener el profesor");
            }
            const profesor = await response.json();
            return profesor;
        } catch (error) {
            console.error("Error al obtener el profesor:", error);
        }
    }

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
            throw error;
        }
    }

    const updateProfesor = async (profesor, id) => {
        try {
            const response = await fetch(`http://localhost:8080/profesores/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(profesor),
            });
    
            if (!response.ok) {
                throw new Error("Error al actualizar el profesor");
            }
    
            await getProfesores(); 
            console.log(`Profesor con ID ${profesor.id} actualizado correctamente.`);
    
        } catch (error) {
            console.error("Error al actualizar el profesor:", error);
            throw error;
        }
    };

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
        getProfesorById,
        updateProfesor,
        saveProfesor,
        deleteProfesor
    }
});