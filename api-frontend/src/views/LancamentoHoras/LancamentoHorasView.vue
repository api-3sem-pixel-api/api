<template>
  <div class="row ">
    <div class="col-3">
      <p class="bold">Modalidade</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model="lancamento.modalidade">
        <option v-for="item in listmodal" :key="item.idModalidade" :value="item.idModalidade">{{item.descricaoModalidade}}</option>
      </select>
    </div>
    
    <div class="col-3">
      <p class="bold">CR</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model="lancamento.cr">
        <option v-for="item in listcr" :key="item.id" :value="item.id">{{item.cr}}</option>
      </select>
    </div>
    <div class="col-3">
      <p class="bold">Cliente</p>
      <select class="form-select" aria-label=".form-select-lg example" v-model="lancamento.cliente">
        <option v-for="item in listcliente" :key="item.id" :value="item.id">{{item.cliente}}</option>
      </select>
    </div>
    <div class="col-3" >
      <p class="bold">Projeto</p>
      <input type="text" class="form-control" v-model="lancamento.projeto">
    </div>

    <div class="col-3" style="margin-top: 10px;">
      <p class="bold">Inicio</p>
      <input id="party" type="datetime-local" name="partydate" v-model="lancamento.inicio" />
    </div>

    <div class="col-3" style="margin-top:10px">
      <p class="bold">Fim</p>
      <input class="button-s" id="party" type="datetime-local" name="partydate" v-model="lancamento.fim" />
    </div>
    <div class="col-1" style="margin-top:40px;"><button v-on:click="lancar" type="button"
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
  modal: any = [{
  id: '',
  modalidade:'',

}]

listmodal: any =[];

listcr: any =[
  { id: '1', cr: 'ola' },
  { id: '2', cr: 'Opção 2' },
  { id: '3', cr: 'Opção 3' },

];
listcliente: any =[
  { id: '1', cliente: 'ola' },
  { id: '2', cliente: 'Opção 2' },
  { id: '3', cliente: 'Opção 3' },

];


  horasLancadas: ExtratoHoraLinha[] = [];
  nivelDePermissao: number = 0;

  created(): void {
    this.obterLancamentos();
    this.popularCombobox();
  }

  obterLancamentos() {
    const user = useAuth().getUser();

    this.nivelDePermissao = user.permissionLevel;
    http.get('/lancamentoHoras/' + user.id)
      .then(response => this.horasLancadas = response.data)
      .catch(_ => alert('Algo deu errado. Tente novamente mais tarde.'));
  }

  popularCombobox  () {
    http.get("/modalidade")
      .then(response => {
        this.listmodal = response.data
      })

  }

  async lancar() {
    try {
    await http.post("/lancamentoHoras", this.lancamento);
         alert(`Horas lançadas com sucesso!!!`)
  
  } catch (error) {
          alert(`Algo deu errado. Tente novamente mais tarde.`)
  
  }
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