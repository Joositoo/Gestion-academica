import { defineStore } from "pinia";
import { reactive } from "vue";

export const useUsuarioStore = defineStore("usuario", () => {
    const usuario = reactive(
        {
            id: null,
            nombre: "",
            apellidos: "",
            email: "",
            password: "",
            rol: ""
        }
    );

    const getUsuario = async (usuarioData) => {
        const API_URL = "http://localhost:8080/auth/login";

        const response = await fetch(API_URL, {
            method: "POST",
            headers:{
                "Content-type": "application/json",
            },
            body: JSON.stringify(usuarioData)
        })

        if (!response.ok){
            throw new Error("Credenciales incorrectas")
        }

        const data = await response.json();

        usuario.id = data.id;
        usuario.nombre = data.nombre;
        usuario.apellidos = data.apellidos;
        usuario.email = data.email;
        usuario.password = data.password;
        usuario.rol = data.rol;

        sessionStorage.setItem("usuario", JSON.stringify(usuario));

        return data;
    }


    return {
        usuario,
        getUsuario
    }
})