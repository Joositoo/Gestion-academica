import { defineStore } from "pinia";
import { reactive, ref } from "vue";

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
    const deleteProfesor = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/profesores/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar el profesor");
            }

            
    
            console.log(`Profesor con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error("Error al eliminar el profesor:", error);
        }
    };

    return {
        profesores,
        getProfesores,
        deleteProfesor
    }
});