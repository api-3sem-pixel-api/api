<template>
  <div>
    <LoginForm v-if="showLoginForm"></LoginForm>
    <AprovacaoReprovacao v-if="showAprovacaoReprovacao"></AprovacaoReprovacao>
    <CadastroUsuarios v-if="showCadastroUsuarios"></CadastroUsuarios>
    <CadastroCr v-if="showCadastroCr"></CadastroCr>
    <LancamentoHora v-if="showLancamentoHora"></LancamentoHora>
    <VisualizacaoUsuarios v-if="showVisualizacaoUsuarios"></VisualizacaoUsuarios>
    <VisualizacaoCr v-if="showVisualizacaoCr"></VisualizacaoCr>
    <Home v-if="showHome"></Home>
  </div>
</template>

<script>
import Home from './views/Home.vue';
import LoginForm from './views/LoginForm.vue';
import AprovacaoReprovacao from './views/AprovacaoReprovacao.vue';
import CadastroUsuarios from './views/CadastroUsuarios.vue';
import CadastroCr from './views/CadastroCr.vue';
import LancamentoHora from './views/LancamentoHora.vue';
import VisualizacaoCr from './views/VisualizacaoCr.vue';
import VisualizacaoUsuarios from './views/VisualizacaoUsuarios.vue';

export default {
  name: 'App',
  components: {
    LoginForm,
    Home,
    AprovacaoReprovacao,
    CadastroCr,
    CadastroUsuarios,
    LancamentoHora,
    VisualizacaoUsuarios,
    VisualizacaoCr,
  },
  data() {
    return {
      showLoginForm: false,
      showHome: false,
      showAprovacaoReprovacao: false,
      showCadastroCr: false,
      showCadastroUsuarios: false,
      showLancamentoHora: false,
      showVisualizacaoUsuarios: false,
      showVisualizacaoCr: false,
    };
  },
  watch: {
    $route(to) {
      const routeStateMap = {
        Home: 'showHome',
        Login: 'showLoginForm',
        AprovacaoReprovacao: 'showAprovacaoReprovacao',
        CadastroUsuarios: 'showCadastroUsuarios',
        CadastroCr: 'showCadastroCr',
        LancamentoHora: 'showLancamentoHora',
        VisualizacaoUsuarios: 'showVisualizacaoUsuarios',
        VisualizacaoCr: 'showVisualizacaoCr',
      };

      for (const routeName in routeStateMap) {
        this[routeStateMap[routeName]] = to.name === routeName;
      }
    },
  },
};
</script>

<style scoped>
/* Estilos específicos para o layout... */
</style>
