import { defineStore } from "pinia";
import { ref } from "vue";

export const usePorcentajeStore = defineStore("porcentaje", () => {
    const porcentajes = ref([]);

    const getPorcentajes = async () => {
        try {
            const response = await fetch("http://localhost:8080/porcentajes");
            if (!response.ok) {
                throw new Error("Ha ocurrido un error");
            }
            const data = await response.json();
            porcentajes.value = data;

            return data;
        } catch (error) {
            console.error("Error al obtener los porcentajes:", error);
        }
    };

    const getPorcentajeById = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/porcentajes/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener el porcentaje");
            }
            const porcentaje = await response.json();
            return porcentaje;
        } catch (error) {
            console.error("Error al obtener el porcentaje:", error);
        }
    };

    const savePorcentaje = async (porcentaje) => {
        try {
            const response = await fetch("http://localhost:8080/porcentajes", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(porcentaje),
            });

            if (!response.ok) {
                throw new Error("Error al guardar el porcentaje");
            }

            await getPorcentajes();

        } catch (error) {
            console.error("Error al guardar el porcentaje:", error);
        }
    };

    const updatePorcentaje = async (porcentaje, id) => {
        try {
            const response = await fetch(`http://localhost:8080/porcentajes/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(porcentaje),
            });
    
            if (!response.ok) {
                throw new Error("Error al actualizar el porcentaje");
            }
    
            await getPorcentajes(); 
            console.log(`Porcentaje actualizado correctamente.`);
    
        } catch (error) {
            console.error("Error al actualizar el porcentaje:", error);
        }
    };

    const deletePorcentaje = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/porcentajes/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar el porcentaje");
            }

            await getPorcentajes();
    
            console.log(`Porcentjae con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error("Error al eliminar el porcentaje:", error);
        }
    };

    return {
        porcentajes,
        getPorcentajes,
        getPorcentajeById,
        savePorcentaje,
        updatePorcentaje,
        deletePorcentaje
    }
});