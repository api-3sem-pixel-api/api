<template>
  <div>
    <h2>Conteúdo da página inicial aqui</h2>
  </div>
  <div>
    <button @click="redirectToHome">Redirecionar para Home</button>
  </div>
</template>

<script>
export default {
  name: 'HomePage', // Nome do componente
  methods: {
    redirectToHome() {
      // Redirecionar o usuário para a página "home"
      this.$router.push('/');
    },
  },
};
</script>

<style scoped>
/* Estilos específicos para a página inicial */
</style>
