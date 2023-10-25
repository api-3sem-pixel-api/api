<template>
    <div class="row relatorio">
        <div class="col-3">
            <p class="bold">p</p>
            <select class="form-select" aria-label=".form-select-lg example" > 
            </select>
       </div> 
       <div class="col-1" style="margin-top:40px;"><button @click="gerar()" type="button"
        class="btn btn-outline-primary"> Gerar relatório </button></div>
        
    </div>
     

</template>

<script lang="ts">
import http from '@/services/http';
import { Options, Vue } from 'vue-class-component';
export default class Relatorio extends Vue {

async gerar(){

      http.get('/relatorio')
            .catch(_ => alert('Tente Novamente '))
            .finally(()=>{              

            });
      

    }
  }



</script>

<style scoped>
.bold {
    font-weight: 700;
    width: 100%;
  }

  .relatorio {
    margin-bottom: 75px;
  }

</style>