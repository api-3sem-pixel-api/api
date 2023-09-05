<template>
  <h1>Tela de de Visualizacao de usuario</h1>
</template>
<script lang="ts">
export default {
  name: "ControleUsuariosView",
  methods: {
    redirectToHome() {
      this.$router.push('/');
    },
  }
}

</script>