<template>
  <router-view/>
</template>

<script>

import error from '@/components/error.vue'
import databaseView from '@/components/database-view.vue';
import loginForm from "@/components/login-form.vue";
export default {
  name: 'App',
  components: {
    loginForm ,
    error,
    databaseView
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;

  
}
</style>
