<template>
  <div>
    <Cliente @update-table="loadAllClientes"></Cliente>
    <div class="row">
      <table class="table table-responsive no-wrap-table">
        <thead>
          <tr>
            <th scope="col" class="text-left">Razão Social</th>
            <th scope="col" class="text-left">CNPJ</th>
            <th scope="col" class="text-center">Status</th>
            <th scope="col" class="text-center">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(cr, index) in crs" :key="index">
            <td>{{ cr['razaoSocialCr'] }}</td>
            <td>{{ cr['cnpjCr'] }}</td>
            <td class="text-center d-flex" style="justify-content: center;">
              <div class="pill text-center text-wrap" :class="{ approved: cr.ativo, inativo: !cr.ativo }">
                {{ cr.ativo ? 'Ativo' : 'Inativo' }}
              </div>
            </td>
            <td class="text-center">
              <button class="btn btn-link" @click="editCr(cr.id)">
                <i class="fa fa-pencil" aria-hidden="true"></i>
              </button>
              <button class="btn btn-link" @click="inativarCr(cr.id)">
                <i class="fa fa-ban" aria-hidden="true"></i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script lang="ts">
import http from '@/services/http';
import { PropType, defineComponent } from 'vue';

export default defineComponent({
  name: "ControleCrView",
  data() {
    return {
      crs: Array as PropType<any>
    }
  },
  created() {
    this.loadAllCrs();
  },
  methods: {
    async loadAllCrs() {
      try {
        const response = await http.get('/cr');
        this.crs = response.data;
      } catch (err) {
        alert('Algo deu errado, tente novamente mais tarde.');
      }
    },
    async inativarCr(crId) {
      try {
        await http.put(`/cr/inativar/${crId}`);
        const cr = this.crs.find((c) => c.id === crId);
        if (cr) {
          cr.ativo = false;
        }
        alert('CR inativado com sucesso');
      } catch (error) {
        alert('Erro ao inativar o CR. Tente novamente mais tarde.');
      }
    },
    editCr(crId) {
      // Implemente a função para editar o CR, se necessário.
    }
  }
})
</script>

<style>
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

    &.inativo {
        background-color: gray;
    }
}
</style>
