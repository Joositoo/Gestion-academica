import { defineStore } from "pinia";
import { reactive } from "vue";

const url = "http://localhost:8080/";

export const useUsuarioStore = defineStore("usuario", () => {
    const usuario = reactive(
        {
            id: null,
            nombre: "",
            apellidos: "",
            email: "",
            password: "",
            rol: "",
            img: ""
        }
    );

    const usuarioStored = sessionStorage.getItem("usuario");
    if (usuarioStored) {
        const usuarioJson = JSON.parse(usuarioStored);
        Object.assign(usuario, usuarioJson);
    }

    const getUsuario = async (usuarioData) => {
        const API_URL = url+ "auth/login";

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
        usuario.img = data.img || ""; 

        sessionStorage.setItem("usuario", JSON.stringify(usuario));

        return data;
    }

    const logout = () => {
        usuario.id = null;
        usuario.nombre = "";
        usuario.apellidos = "";
        usuario.email = "";
        usuario.password = "";
        usuario.rol  = "";
        sessionStorage.removeItem("usuario");
    }


    return {
        usuario,
        getUsuario,
        logout
    }
})