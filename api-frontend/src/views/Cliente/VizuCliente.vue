<template>
      <div class="row">
        <table class="table table-responsive no-wrap-table">
          <thead>
            <tr>
              <th scope="col" class="text-left">Razão Social</th>
              <th scope="col" class="text-left">Cnpj</th>
            
              <th scope="col" class="text-center">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="client in cliente">
              <td>{{ client['razaoSocialCliente'] }}</td>
              <td>{{ client['cnpjCliente'] }}</td>
              <td class="text-center">Ativo</td>
              <td class="text-center">
                <button class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i></button>
            <button class="btn btn-link" ><i class="fas fa-id-card" aria-hidden="true"></i></button>
            <button class="btn btn-link"><i class="fa fa-trash" aria-hidden="true"></i></button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <Cliente @update-table="loadAllcliente"></Cliente>


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
        cliente: Array as PropType<any>
    }},
    created() {
    this.loadAllcliente();
  },

    methods: {
    loadAllcliente() {
        http.get('/cliente')
        .then(x => this.cliente = x.data)
        .catch(err => alert('Algo deu errado tente novamente mais tarde.'));
    }
    
    }
})

</script>