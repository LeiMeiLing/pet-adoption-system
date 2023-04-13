<template>
    <div class="out">
        <img v-bind:src="route.query.goodsPicture" alt="" class="img1">
        <div>
            {{route.query }}

        </div>

    </div>

</template>

<script setup>
import {useRoute} from "vue-router";
import router from "../../../config/router.js";
const route = useRoute();

</script>

<style scoped>
.out{
    margin: 10px;
    .img1{

                 margin: 50px;
    }
}
</style>