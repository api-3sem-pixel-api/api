<template>
  <form @submit.prevent="submitForm">
    <div>
      <label for="username">Nome de Usuário ou E-mail:</label>
      <input type="text" id="username" v-model="login.username" required>
    </div>
    <div>
      <label for="password">Senha:</label>
      <input type="password" id="password" v-model="login.password" required>
    </div>
    <router-link to="/home">
    <button type="submit">Entrar</button>
  </router-link>

  </form>
</template>

<script>
export default {
  data() {
    return {
      login: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    submitForm() {
      // Aqui  adicionar lógica para autenticar o usuário com os dados do formulário
    
      console.log('Dados de login:', this.login);

      // Simule uma autenticação bem-sucedida (substitua por sua lógica real de autenticação)
      const authenticationSuccessful = true;

      if (authenticationSuccessful) {
        // Após a autenticação bem-sucedida, redirecione o usuário para a página principal
        this.$router.push('/home');
      } else {
        // Lógica para tratar autenticação mal-sucedida (por exemplo, exibir uma mensagem de erro)
        alert('Login falhou. Verifique suas credenciais.');
      }
    },
  },
};
</script>

<style>
/* Estilos do layout... */
</style>
