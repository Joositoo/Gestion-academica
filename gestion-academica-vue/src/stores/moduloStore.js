import { defineStore } from "pinia";
import { ref } from "vue";

const url = "http://localhost:8080/";

export const useModuloStore = defineStore("modulo", () =>{
    const modulos = ref([]);

    const getModulos = async () => {
        try {
            const response = await fetch(url+ "modulos");
            if (!response.ok) {
                throw new Error("Ha ocurrido un error");
            }
            const data = await response.json();
            modulos.value = data;

            return data;
        } catch (error) {
            console.error("Error al obtener módulos:", error);
        }
    };

    const getModuloById = async (id) => {
        try {
            const response = await fetch(url+ `modulos/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener el módulo");
            }
            const modulo = await response.json();
            return modulo;
        } catch (error) {
            console.error("Error al obtener el módulo:", error);
        }
    };

    const saveModulo = async (file) => {
        try {
            const formData = new FormData();
            formData.append("file", file)

            const response = await fetch(url+ "modulos", {
                method: "POST",
                body: formData,
            });

            if (!response.ok) {
                throw new Error("Error al guardar el módulo");
            }

            await getModulos();

        } catch (error) {
            console.error("Error al guardar el módulo:", error.message);
            throw error;
        }
    };

    const updateModulo = async (modulo, id) => {
        try {
            const response = await fetch(url+ `modulos/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(modulo),
            });
    
            if (!response.ok) {
                throw new Error("Error al actualizar el modulo");
            }
    
            await getModulos();    
            console.log(`Módulo actualizado correctamente.`);
        } catch (error) {
            console.error("Error al actualizar el módulo:", error);
            throw error;
        }
    };

    const deleteModulo = async (id) => {
        try {
            const response = await fetch(url+ `modulos/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar el módulo");
            }

            await getModulos();
    
            console.log(`Módulo eliminado correctamente.`);
        } catch (error) {
            console.error("Error al eliminar el módulo:", error);
        }
    };

    return {
        modulos,
        getModulos,
        getModuloById,
        saveModulo,
        updateModulo,
        deleteModulo
    }
})