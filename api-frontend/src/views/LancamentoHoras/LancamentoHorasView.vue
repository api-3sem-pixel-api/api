<template>

  <div class="row ">
    <div class="col">
      <p class="italic">Modalidade</p>
      <select class="form-select" aria-label=".form-select-lg example"></select>
    </div>
    <div class="col">
      <p class="italic">CR</p>
      <select class="form-select" aria-label=".form-select-lg example"></select>
    </div>
    <div class="col">
      <p class="italic">Cliente</p>
      <select class="form-select" aria-label=".form-select-lg example"></select>
    </div>
    <div class="col">
      <p class="italic">Projeto</p>
      <input type="text" class="form-control">      
    </div>
    <div class="col">
      <p class="italic">Inicio</p>
      <input id="party" type="datetime-local" name="partydate" />
    </div>
    <div class="col">
      <p class="italic">Fim</p>
      <input id="party" type="datetime-local" name="partydate" />   
    </div>
    <TabelaHoras></TabelaHoras>
    

  </div>


</template>

<script lang="ts">
import TabelaHoras from './TabelaHoras/TabelaHoras.vue';
import { Options, Vue } from 'vue-class-component';


@Options({

  components: {
    TabelaHoras
  }
  
})
export default class LancamentoHorasView extends Vue {
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

</style>