<!-- ClienteView.vue -->
<template>
    <div>
      <h1>Detalhes do Cliente</h1>
      <p>Nome: {{ cliente.nome }}</p>
      <p>Email: {{ cliente.email }}</p>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        cliente: {
          nome: "John Doe",
          email: "john@example.com"
        }
      };
    }
  };
  </script>
  