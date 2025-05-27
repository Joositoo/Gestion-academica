<script setup>
import { defineProps } from 'vue';
const props = defineProps({
    path: String
})

const fullPath = '/' + props.path;
</script>

<template>
    <span>
        <router-link :to="fullPath" class="arrow-container">
            <i class="bi bi-arrow-left"></i>
        </router-link>
    </span>
</template>

<style scoped>
.bi-arrow-left,
.bi {
    font-size: 45px;
    color: black
}

.arrow-container {
    display: inline-block;
    background-color: #a9d4ff;
    border: 1px solid black;
    border-radius: 10%;
    padding: 20px;
    box-shadow: 8px 8px 8px #74777c;
    margin-left: 10em;
    margin-top: 2em;
    transition: all 0.3s ease-in-out;
}

.arrow-container:hover{
    transform: scale(1.05);
    background-color: #59c1ff;;
}
</style>