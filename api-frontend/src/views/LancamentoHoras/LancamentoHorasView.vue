<template>

  <div class="row ">
    <div class="col">
      <p class="italic">Modalidade</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model="lancamento.modalidade"></select>
    </div>
    <div class="col">
      <p class="italic">CR</p>
      <select class="form-select" aria-label=".form-select-lg example"  v-model = "lancamento.cr"></select>
    </div>
    <div class="col">
      <p class="italic">Cliente</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model = "lancamento.cliente"></select>
    </div>
    <div class="col">
      <p class="italic">Projeto</p>
      <input type="text" class="form-control" v-model = "lancamento.projeto">       
    </div>



    <div class="col">
      <p class="italic">Inicio</p>
      <input id="party" type="datetime-local" name="partydate" v-model ="lancamento.inicio" />
    </div>

    

    
    <div class="col">
      <p class="italic">Fim</p>
      <input class="button-s" id="party" type="datetime-local"  name="partydate" v-model = "lancamento.fim" /> 
    </div>
    <div class="col" style="margin-top:30px;"><button v-on:click ="lancar" type="button" class="btn btn-outline-primary">Lançar</button></div>




    <TabelaHoras style="margin-top: 100px;"></TabelaHoras>
    

  </div>


</template>

<script lang="ts">
import TabelaHoras from './TabelaHoras/TabelaHoras.vue';
import { Options, Vue } from 'vue-class-component';
import http from "@/services/http";


@Options({
  components: {
    TabelaHoras
  }
})
export default class LancamentoHorasView extends Vue {
  lancamento:any = {
      id: '',
      cr: '',
      cliente: '',
      projeto:'',
      inicio:'',
      fim:'',
      modalidade:'',
      solicitante:'',
      justificativa:'',
      status:'',
  }

 async lancar(){

  console.log(this.lancamento)
    const {data} = await http.post("/lancamentoHoras", this.lancamento);
    
  }
};
</script>

<style scoped>
.italic {
  font-weight: 700;
  width: 100%;
}
.form-select{
  width: 0.75;
}
.button-s{
  boder:1px solid #000;
}


</style>