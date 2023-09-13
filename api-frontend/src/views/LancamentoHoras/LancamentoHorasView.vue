<template>
  <TabelaHoras></TabelaHoras>
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
  
<style scoped></style>
  