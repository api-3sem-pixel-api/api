<template>
  <div>
    <template>
      <div id="reprovar-modal" class="r-modal">
        <!-- Modal content -->
        <div class="r-modal-content">
          <div class="modal-header d-flex align-items-baseline">
            <h4>Explique o motivo</h4>
            <span class="close">&times;</span>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col">
                <input placeholder="Nome" v-model="userInput.nome" style="width: 800px;" type="text" class="form-control" />
              </div>
              <div class="col">
                <input placeholder="Email" v-model="userInput.email" style="width: 800px;" type="text" class="form-control" />
              </div>
              <div class="col">
                <input placeholder="Funcao" v-model="userInput.funcao" style="width: 800px;" type="text" class="form-control" />
              </div>
              <div class="col">
                <input placeholder="CPF" v-model="userInput.CPF" style="width: 800px;" type="text" class="form-control" />
              </div>
            </div>
            <div class="row mt-4">
              <div class="col">
                <button type="button" class="btn btn-danger" @click="cancelar">Cancelar</button>
                <button type="button" class="btn btn-link r-ml-2" @click="cadastrar">Cadastrar</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>

    <div class="table-horas">
      <table class="table table-responsive no-wrap-table">
        <thead>
          <tr>
            <th scope="col" class="text-left">Nome</th>
            <th scope="col" class="text-left">CPF</th>
            <th scope="col" class="text-left">Email</th>
            <th scope="col" class="text-left">Função</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="usuario in usuarios">
            <td>{{ usuario.nome }}</td>
            <td>{{ usuario.CPF }}</td>
            <td>{{ usuario.email }}</td>
            <td>{{ usuario.funcao }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { cadastrousuario } from './Models/cadastrousuario';
import http from "@/services/http";

export default defineComponent({
  data() {
    return {
      userInput: {
        nome: '',
        email: '',
        funcao: '',
        telefone: '',
        CPF: '',
      } as cadastrousuario,
      usuarios: [] as cadastrousuario[], 
    };
  },
  methods: {
    async cadastrar() {
      try {
        const response = await http.post('/usuario', this.userInput);

        if (response.status === 200) {

          this.usuarios.push({ ...this.userInput });

          this.userInput = {
            nome: '',
            email: '',
            funcao: '',
            telefone: '',
            CPF: '',
          };
          console.log('Cadastro bem-sucedido');
        } else {
          console.error('Erro ao cadastrar usuário');
        }
      } catch (error) {
        console.error('Erro ao cadastrar usuário:', error);
      }
    },cancelar(){
      
    }
  },
});

</script>
