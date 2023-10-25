<template>
  <Cliente @update-table="loadAllcliente"></Cliente>
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
        <tr v-for="(client, index) in clientes" :key="index">
      
         
          <td>{{ client['razaoSocialCliente'] }}</td>
          <td>{{ client['cnpjCliente'] }}</td>
          <td class="text-center d-flex" style="justify-content: center;"  > <div 
          class="pill approved text-center text-wrap" 
          :class="{
            approved: client['ativo'] == true,
        }" > 
            Ativo 
        </div></td>
          <td class="text-center">
            <button class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i></button>

        <button class="btn btn-link"><i class="fa fa-trash" @click="inativarCliente(client['id'])" aria-hidden="true" ></i></button>

        
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  


</template>



<script lang="ts">
import http from '@/services/http';
import { PropType, defineComponent } from 'vue';
import Cliente from '@/views/Cliente/Cliente.vue';

interface ClienteData {
  id: number;
  razaoSocialCliente: string;
  cnpjCliente: string;
  ativo: boolean;
  // Outros campos de dados, se houver
}

export default defineComponent({
  name: "VizuCliente",
  components: {
    Cliente
  },

  data() {
    return {
      clientes: [] as ClienteData[]
    };
  },

  created() {
    this.loadAllcliente();
  },

  methods: {
    async loadAllcliente() {
      try {
        const response = await http.get('/cliente');
        this.clientes = response.data;
        console.log(this.clientes);
      } catch (err) {
        alert('Algo deu errado, tente novamente mais tarde.');
      }
    },

    async inativarCliente(clienteId: number) {
      const cliente = this.clientes.find((cliente) => cliente.id === clienteId);
      console.log(cliente)
      if (cliente) {
        if (cliente.ativo) {
          try {
            await http.delete(`/cliente/${clienteId}`);
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
    },
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

.waiting {
  background-color: gainsboro;
}

.reproved,
.canceled {
  background-color: red;
}
</style>
