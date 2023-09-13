<template>
  <TabelaHoras></TabelaHoras>
</template>
  
  <script lang="ts">
import TabelaHoras from '@/components/tabela-horas/TabelaHoras.vue';
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
  
  </style>
  