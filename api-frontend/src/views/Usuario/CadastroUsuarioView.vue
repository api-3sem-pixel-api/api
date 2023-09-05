<template>
  <h1>Tela de Cadastro de usuarios</h1>
</template>
<script lang="ts">
export default {
  name: "CadastroUsuariosView",
  methods: {
    redirectToHome() {
      this.$router.push('/');
    },
  }
}

</script>