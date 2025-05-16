import { defineStore } from "pinia";
import { ref } from "vue";

export const useMatriculaStore = defineStore("matricula", () => {
    const matriculas = ref([]);

    const getMatriculas = async () => {
        try {
            const response = await fetch("http://localhost:8080/matriculas");
            if (!response.ok) {
                throw new Error("Ha ocurrido un error");
            }
            const data = await response.json();
            matriculas.value = data;

            return data;
        } catch (error) {
            console.error("Error al obtener matrículas:", error);
        }
    };

    const getMatriculaById = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/matriculas/${id}`);
            if (!response.ok) {
                throw new Error("Error al obtener la matrícula");
            }
            const matricula = await response.json();
            return matricula;
        } catch (error) {
            console.error("Error al obtener el matrícula:", error);
        }
    };

    //ESTE METODO TIENE QUE ENVIAR UN FICHERO
    /*const saveMatrícula = async (matricula) => {
        try {
            const response = await fetch("http://localhost:8080/matrículas", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(matricula),
            });

            if (!response.ok) {
                throw new Error("Error al guardar la matrícula");
            }

            await getMatriculas();

        } catch (error) {
            console.error("Error al guardar la matrícula:", error);
        }
    };*/

    const saveMatricula = async (file) => {
        try {
            const formData = new FormData();
            formData.append("file", file)

            const response = await fetch("http://localhost:8080/matriculas", {
                method: "POST",
                body: formData,
            });

            if (!response.ok) {
                throw new Error("Error al guardar las matrículas");
            }

            await getMatriculas();

        } catch (error) {
            console.error("Error al guardar las matrículas:", error);
            throw error;
        }
    };

    const updateMatricula = async (matricula, id) => {
        try {
            const response = await fetch(`http://localhost:8080/matriculas/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(matricula),
            });
    
            if (!response.ok) {
                throw new Error("Error al actualizar la matricula");
            }
    
            await getMatriculas();    
            console.log(`Matrícula actualizada correctamente.`);
        } catch (error) {
            console.error("Error al actualizar la matrícula:", error);
            throw error;
        }
    };

    const deleteMatricula = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/matriculas/${id}`, {
                method: 'DELETE',
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar la matrícula");
            }

            await getMatriculas();
    
            console.log(`Matrícula eliminada correctamente.`);
        } catch (error) {
            console.error("Error al eliminar la matrícula:", error);
        }
    };

    return {
        matriculas,
        getMatriculas,
        getMatriculaById,
        saveMatricula,
        updateMatricula,
        deleteMatricula
    }
})