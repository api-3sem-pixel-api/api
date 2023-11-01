<template>
  <div>
    <Cliente @update-table="loadAllCliente"></Cliente>
    <div class="row">
      <table class="table table-responsive no-wrap-table">
        <thead>
          <tr>
            <th scope="col" class="text-left">Razão Social</th>
            <th scope="col" class="text-left">Cnpj</th>
            <th scope="col" class="text-center">Status</th>
            <th scope="col" class="text-center">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cliente in clientes" :key="cliente.id">
            <td>{{ cliente.razaoSocialCliente }}</td>
            <td>{{ cliente.cnpjCliente }}</td>
            <td class="d-flex justify-content-center">
              <div
                class="pill text-center text-wrap"
                :class="{ approved: cliente.ativo, inativo: !cliente.ativo }"
              >
                {{ cliente.ativo ? 'Ativo' : 'Inativo' }}
              </div>
            </td>
            <td class="text-center">
              <button class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i></button>
              <button class="btn btn-link" @click="inativarCliente(cliente['idCliente'])"><i class="fa fa-trash" aria-hidden="true"></i></button>
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
import Cliente from '@/views/Cliente/Cliente.vue';


export default defineComponent({
  name: "VizuCliente",
  components: {
    Cliente
  },

  data() {
    return {
      clientes: [] as  Array<any>
    };
  },

  created() {
    this.loadAllCliente();
  },

  methods: {
    async loadAllCliente() {
      try {
        const response = await http.get('/cliente');
        this.clientes = response.data;
        console.log(this.clientes);
      } catch (err) {
        alert('Algo deu errado, tente novamente mais tarde.');
      }
    },

    async inativarCliente(idCliente:number) {
      console.log(idCliente)
      const cliente = this.clientes.find((c) => c.idCliente === idCliente);

      if (cliente) {
        if (cliente.ativo) {
          try {
            await http.delete(`/cliente/${idCliente}`);
            cliente.ativo = false;
            alert('Cliente inativado com sucesso');
          } catch (error) {
            alert('Erro ao inativar o cliente. Tente novamente mais tarde.');
          }
        } else {
          alert('O cliente já está inativo');
        }
      } else {
        alert('Cliente não encontrado');
      }
    }
  },
});
</script>

<style>
.pill {
  border-radius: 30px;
  width: 140px;
  color: white;
}

.approved {
  background-color: #26fc29;
}

.inativo {
  background-color: red; /* Defina a cor desejada para clientes inativos */
}
</style>
