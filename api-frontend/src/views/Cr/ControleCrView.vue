<template>
  <div class="d-flex justify-content-end mb-3">
    <button class="btn btn-outline-primary" @click="newCr()"> Cadastrar CR </button>
  </div>
  <div class="row">
    <table class="table table-responsive no-wrap-table">
      <thead>
        <tr>
          <th scope="col" class="text-left">Cod</th>
          <th scope="col" class="text-left">Sigla</th>
          <th scope="col" class="text-left ">Nome</th>
          <th scope="col" class="text-center">Status</th>
          <th scope="col" class="text-center">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cr in crs" :key="cr.id">
          <td>{{ cr['codigo'] }}</td>
          <td>{{ cr['sigla'] }}</td>
          <td>{{ cr['nome'] }}</td>
          <td class="d-flex justify-content-center">  
            <div 
              class="pill text-center text-wrap" 
              :class="{
                approved: cr['ativo'] === true,
                inativo: cr['ativo'] === false,
              }"> 
                {{ cr.ativo ? 'Ativo' : 'Inativo' }}
            </div>
          </td>
          <td class="text-center">
            <button class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i></button>
            <button class="btn btn-link" @click="editUserCr(cr['id'])"><i class="fas fa-id-card" aria-hidden="true"></i></button>
            <button class="btn btn-link" @click="inativarCr(cr['id'])"><i class="fa fa-trash" aria-hidden="true"></i></button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <ModalCrUsuario :id-cr="idCr"></ModalCrUsuario>
  <ModalCadastroCrView @update-table="loadAllCr"></ModalCadastroCrView>
</template>

<script lang="ts">
import http from '@/services/http';
import { PropType, defineComponent, ref } from 'vue';
import ModalCadastroCrView from '@/views/Cr/ModalCadastroCr/ModalCadastroCrView.vue';
import ModalCrUsuario from '@/views/Cr/ModalCrUsuario/ModalCrUsuario.vue';

export default defineComponent({
  name: "ControleCrView",
  components: {
    ModalCadastroCrView,
    ModalCrUsuario
  },
  data() {
    return {
      crs: [] as Array<any>,
      idCr: 0
    }
  },
  created() {
    this.loadAllCr();
  },
  methods: {
    newCr() {
      var modal = document.getElementById("cadastro-cr-modal")!;
      modal.style.display = "block";
    },
    editUserCr(id: number) {
      this.idCr = id;
      var modal = document.getElementById("cr-usuario-modal")!;
      modal.style.display = "block";
    },
    loadAllCr() {
      http.get('/cr')
        .then((x: any) => this.crs = x.data)
        .catch(err => alert('Algo deu errado, tente novamente mais tarde.'));
    },
    async inativarCr(id: number) {
      const cr = this.crs.find((cr) => cr.id === id);
      console.log('ID a ser excluído:', id);

      if (cr) {
        console.log('CR encontrado:', cr);

        try {
          if (cr.ativo) {
            console.log('CR ativo, marcando como inativo:', cr);
            await http.delete(`/cr/${cr.id}`);
            cr.ativo = false;
            alert('CR marcado como Inativo');
          } else {
            console.log('CR já está Inativo:', cr);
            alert('CR já está Inativo');
          }
        } catch (error) {
          console.error('Erro ao excluir o CR:', error);
          alert('Algo deu errado, tente novamente mais tarde.');
        }
      } else {
        console.log('CR não encontrado:', id);
        alert('CR não encontrado');
      }
    }
  }
})
</script>

<style>
.pill {
    border-radius: 30px;
    width: 140px;
    color: white;
  }

  .approved {
    background-color: #26fc29; /* Verde para CRs ativos */
  }

  .inativo {
    background-color: red; /* Vermelho para CRs inativos */
  }
</style>
