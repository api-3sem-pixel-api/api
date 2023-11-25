<template>
  <div>

    <div id="update-cr-modall" class="r-modal">
      <div class="r-modal-content">
        <div class="modal-header d-flex align-items-baseline">
          <h4>Edição de Cliente</h4>
          <span class="close" @click="close">&times;</span>
        </div>
       
        <div class="row">
          <div class="col-12" style="margin-top: 20px;">
            <div class="form-group">
              <label for="sigla">NOME</label>
              <input type="text" class="form-control" id="sigla" v-model="siglaNome">
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-12" style="margin-top: 20px;">
            <div class="form-group">
              <label for="sigla">SIGLA</label>
              <input type="text" class="form-control" id="sigla" v-model="sigla">
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-12" style="margin-top: 20px;">
            <div class="form-group">
              <label for="sigla">CODIGOCR</label>
              <input type="text" class="form-control" id="sigla" v-model="siglaCodigoCr">
            </div>
          </div>
        </div>

        <div class="row mt-4">
          <div class="col">
            <button type="button" @click="save()" class="btn btn-success">Salvar</button>
            <button type="button" @click="close()" class="btn btn-link r-ml-2">Cancelar</button>
          </div>
        </div>
      </div>
    </div>

    <div class="row mt-4">
      <div class="col-12">
        <h3>CONTROLE | CR</h3>
        <hr>
      </div>
    </div>

    <div class="d-flex mt-3 justify-content-end mb-3">
      <button class="btn btn-outline-primary" @click="newCr()"> Cadastrar CR </button>
    </div>

    <div class="row mt-4">
      <table class="table table-responsive no-wrap-table">
        <thead>
          <tr>
            <th scope="col" class="text-left">Cod</th>
            <th scope="col" class="text-left">Sigla</th>
            <th scope="col" class="text-left">Nome</th>
            <th scope="col" class="text-center">Status</th>
            <th scope="col" class="text-center">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cr in crs" :key="cr.idCr">
            <td>{{ cr.codigo }}</td>
            <td>{{ cr.sigla }}</td>
            <td>{{ cr.nome }}</td>
            <td style="width: 100px">
              <div
                class="pill text-center text-wrap"
                :class="{
                  approved: cr.ativo === true,
                  inativo: cr.ativo === false,
                }"
              >
                {{ cr.ativo ? 'Ativo' : 'Inativo' }}
              </div>
            </td>
            <td class="text-center">
              <button class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i></button>
              <button class="btn btn-link" @click="editUserCr"><i class="fas fa-id-card" aria-hidden="true"></i></button>
              <button class="btn btn-link" @click="inativarCr(cr.idCr)"><i class="fa fa-trash" aria-hidden="true"></i></button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <ModalCrUsuario :id-cr="idCr"></ModalCrUsuario>
    <ModalCadastroCrView @update-table="loadAllCr"></ModalCadastroCrView>
  </div>
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
      var modal = document.getElementById("update-cr-modall")!;
      modal.style.display = "block";
      console.log(modal)
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


<style scoped>
.pill {
    border-radius: 30px;
    width: 140px;
    color: white;

    &.approvedGestor {
      background-color: #fac02d;
    }

    &.approved {
      background-color: #26fc29;
    }

    &.waiting {
      background-color: gainsboro;
    }

    &.reproved,
    &.canceled {
      background-color: red;
    }
}
</style>