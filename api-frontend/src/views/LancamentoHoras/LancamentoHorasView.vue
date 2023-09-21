<template>
  <div class="row lancar-horas">
    <div class="col">
      <p class="bold">Modalidade</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model="lancamento.modalidade"></select>
    </div>
    <div class="col">
      <p class="bold">CR</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model="lancamento.cr"></select>
    </div>
    <div class="col">
      <p class="bold">Cliente</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model="lancamento.cliente"></select>
    </div>
    <div class="col">
      <p class="bold">Projeto</p>
      <input type="text" class="form-control" v-model="lancamento.projeto">
    </div>

    <div class="col">
      <p class="bold">Inicio</p>
      <input id="party" type="datetime-local" name="partydate" v-model="lancamento.inicio" />
    </div>

    <div class="col">
      <p class="bold">Fim</p>
      <input class="button-s" id="party" type="datetime-local" name="partydate" v-model="lancamento.fim" />
    </div>
    <div class="col" style="margin-top:30px;"><button v-on:click="lancar" type="button"
        class="btn btn-outline-primary">Lançar</button></div>
  </div>
  <TabelaHoras 
      style="margin-top: 100px;" 
      :horas="horasLancadas"
      :podeGerenciarLancamentos="nivelDePermissao > 1"
    ></TabelaHoras>
</template>

<script lang="ts">
import TabelaHoras from './TabelaHoras/TabelaHoras.vue';
import ModalMotivo from './ModalMotivo/ModalMotivo.vue';
import { Options, Vue } from 'vue-class-component';
import http from "@/services/http";
import { useAuth } from '@/stores/auth';
import { ExtratoHoraLinha } from './TabelaHoras/extrato-hora-linha';


@Options({
  props:['horasLancadas'],
  components: {
    TabelaHoras
  }
})
export default class LancamentoHorasView extends Vue {
  lancamento: any = {
    id: '',
    cr: '',
    cliente: '',
    projeto: '',
    inicio: '',
    fim: '',
    modalidade: '',
    solicitante: '',
    justificativa: '',
    status: '',
  }

  horasLancadas: ExtratoHoraLinha[] = [];
  nivelDePermissao: number = 0;

  created(): void {
    this.obterLancamentos();
  }

  obterLancamentos() {
    const user = useAuth().getUser();

    this.nivelDePermissao = user.permissionLevel;
    http.get('/lancamentoHoras/' + user.id)
      .then(response => this.horasLancadas = response.data)
      .catch(_ => alert('Algo deu errado. Tente novamente mais tarde.'));
  }

  async lancar() {
    await http.post("/lancamentoHoras", this.lancamento);
  }
};
</script>

<style scoped>
.bold {
  font-weight: 700;
  width: 100%;
}

.lancar-horas{
  margin-bottom: 75px;
}

.form-select {
  width: 0.75;
}

.button-s {
  boder: 1px solid #000;
}
</style>