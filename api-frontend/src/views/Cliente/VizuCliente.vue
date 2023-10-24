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
              <td class="text-center d-flex" style="justify-content: center;">
                    <div class="pill text-center text-wrap" :class="{ approved: client.ativo, inativo: !client.ativo }">
                            {{ client.ativo ? 'Ativo' : 'Inativo' }}
                    </div>
              </td>

              <td class="text-center">
                <button class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i></button>
    
                 <button class="btn btn-link" @click="inativarCliente(client.id)">
                    <i class="fa fa-ban" aria-hidden="true"></i>
                  </button>

            
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      


</template>

<script lang="ts" >
import http from '@/services/http';
import { PropType, defineComponent } from 'vue';
import Cliente from '@/views/Cliente/Cliente.vue';

export default defineComponent({
  name: "VizuCliente",
    components:{
        Cliente

    },

  data() {
    return {
        clientes: Array as PropType<any>
    }},
    created() {
    this.loadAllcliente();
  },

    methods: {
      async loadAllcliente() {
      try {
        const response = await http.get('/cliente');
        this.clientes = response.data;
        console.log(this.clientes)

       
      } catch (err) {
        alert('Algo deu errado, tente novamente mais tarde.');
      }
    },
          methods: {
  async inativarCliente(clienteId) {
    const cliente = this.clientes.find(cliente => cliente.id === clienteId);
    
    if (cliente) {
      if (cliente.ativo) {
        try {
          await http.put(`/cliente/inativar/${clienteId}`);
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
}

    
    }
})

</script>
<style>
.pill {
    border-radius: 30px;
    width: 140px;
    color: white;

    &.approvedGestor{
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
