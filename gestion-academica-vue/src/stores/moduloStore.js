import { defineStore } from "pinia";
import { ref } from "vue";

export const useModuloStore = defineStore("modulo", () =>{
    const modulos = ref([]);

    const getModulos = async () => {
        try {
            const response = await fetch("http://localhost:8080/modulos");
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
            const response = await fetch(`http://localhost:8080/modulos/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener el módulo");
            }
            const modulo = await response.json();
            return modulo;
        } catch (error) {
            console.error("Error al obtener el módulo:", error);
        }
    }

    //ESTE METODO TIENE QUE ENVIAR UN FICHERO
    const saveModulo = async (modulo) => {
        try {
            const response = await fetch("http://localhost:8080/modulos", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(modulo),
            });

            if (!response.ok) {
                throw new Error("Error al guardar el modulo");
            }

            await getModulos();

        } catch (error) {
            console.error("Error al guardar el ciclo:", error);
        }
    }

    const updateModulo = async (modulo, id) => {
        try {
            const response = await fetch(`http://localhost:8080/modulos/${id}`, {
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
        }
    };

    const deleteModulo = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/modulos/${id}`, {
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