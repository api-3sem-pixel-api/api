<template>
    <h1>Tela de Cadastro de usuarios</h1>
</template>
<script >
export default{
    name:"CadastroUsuarios",
    methods: {
    redirectToHome() {
      // Redirecionar o usuário para a página "home"
      this.$router.push('/');
    },
}
}

</script>