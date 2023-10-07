<template>
  <div class="d-flex justify-content-end mb-3">
    <button class="btn btn-outline-primary" @click="newUser()"> Cadastrar Usuario </button>
  </div>
  <div class="row">
    <table class="table table-responsive no-wrap-table">
      <thead>
        <tr>
          <th scope="col" class="text-left">Nome</th>
          <th scope="col" class="text-left">Email</th>
          <th scope="col" class="text-left ">Telefone</th>
          <th scope="col" class="text-left ">CPF</th> 
          <th scope="col" class="text-center">Função</th>
          <th scope="col" class="text-center">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(usuario, index) in usuarios" :key="index">
  <td>{{ usuario['nome'] }}</td>
  <td>{{ usuario['email'] }}</td>
  <td>{{ usuario['telefone'] }}</td>
  <td>{{ usuario['cpf'] }}</td>
  <td>{{ usuario['funcao'] }}</td>
  <td class="text-center">
    <button class="btn btn-link" @click="updateUser()">
      <i class="fa fa-pencil" aria-hidden="true"></i>
    </button>
    <button class="btn btn-link" @click="excluedUser(index)">
      <i class="fa fa-trash" aria-hidden="true"></i>
    </button>
  </td>
</tr>
      </tbody>
    </table>
  </div>

  <ModalUsuarioView @update-table="loadAllUser()"></ModalUsuarioView>
</template>
<script lang="ts">
import http from '@/services/http';
import { PropType, defineComponent } from 'vue';
import ModalUsuarioView from '@/views/Usuario/ModalCadastroUsuario/ModalCadastroUsuarioView.vue';
export default defineComponent({
  name: "ControleUsuarioView",
  components: {
    ModalUsuarioView
  },
  data() {
    return {
      usuarios: [] as Array<any>     }
  },
  created() {
    this.loadAllUser();
  },
  methods: {
    newUser() {
      var modal = document.getElementById("cadastro-user-modal")!;
      modal.style.display = "block";
    },
    loadAllUser() {
      http.get('/usuario')
        .then(x => this.usuarios = x.data)
        .catch(err => alert('Algo deu errado tente novamente mais tarde.'));
    },
    updateUser(){
      var modal = document.getElementById("cadastro-user-modal")!;
      modal.style.display = "block";
    },
    async excluedUser(index: number) {
      const usuario = this.usuarios[index];
      try {
        await http.delete(`/usuario/${usuario.id}`);
        this.usuarios.splice(index, 1); // Remove o usuário da lista após a exclusão bem-sucedida
      } catch (error) {
        alert('Algo deu errado, tente novamente mais tarde.');
      }
    }
  }
})
</script>