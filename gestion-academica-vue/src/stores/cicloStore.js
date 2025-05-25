import { defineStore } from "pinia";
import { ref } from "vue";

const url = "http://localhost:8080/";

export const useCicloStore = defineStore("ciclo", () => {
    const ciclos = ref([]);

    const getCiclos = async () => {
        try {
            const response = await fetch(url+ "ciclos");
            if (!response.ok) {
                throw new Error("Ha ocurrido un error");
            }
            const data = await response.json();
            ciclos.value = data;

            return data;
        } catch (error) {
            console.error("Error al obtener ciclos:", error);
        }
    };

    const getCicloById = async (id) => {
        try {
            const response = await fetch(url+ `ciclos/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener el ciclo");
            }
            const ciclo = await response.json();
            return ciclo;
        } catch (error) {
            console.error("Error al obtener el ciclo:", error);
        }
    }

    const saveCiclo = async (ciclo) => {
        try {
            const response = await fetch(url+ "ciclos", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(ciclo),
            });

            if (!response.ok) {
                throw new Error("Error al guardar el ciclo");
            }

            await getCiclos();

        } catch (error) {
            console.error("Error al guardar el ciclo:", error);
            throw error;
        }
    }

    const updateCiclo = async (ciclo, id) => {
        try {
            const response = await fetch(url+ `ciclos/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(ciclo),
            });
    
            if (!response.ok) {
                throw new Error("Error al actualizar el ciclo");
            }
    
            await getCiclos();    
            console.log(`Ciclo actualizado correctamente.`);
        } catch (error) {
            console.error("Error al actualizar el ciclo:", error);
            throw error;
        }
    };

    const deleteCiclo = async (id) => {
        try {
            const response = await fetch(url+ `ciclos/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar el ciclo");
            }

            await getCiclos();
    
            console.log(`Ciclo eliminado correctamente.`);
        } catch (error) {
            console.error("Error al eliminar el profesor:", error);
        }
    };

    return {
        ciclos,
        getCiclos,
        getCicloById,
        saveCiclo,
        updateCiclo,
        deleteCiclo
    }
})