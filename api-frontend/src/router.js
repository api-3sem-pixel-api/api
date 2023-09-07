import { createRouter, createWebHistory } from 'vue-router';
import Login from './views/LoginForm.vue';
import Home from './views/Home.vue';
import CadastroCr from './views/CadastroCr.vue';
import CadastroUsuarios from './views/CadastroUsuarios.vue';
import LancamentoHora from './views/LancamentoHora.vue';
import VisualizacaoCr from './views/VisualizacaoCr.vue';
import VisualizacaoUsuarios from './views/VisualizacaoUsuarios.vue';
import AprovacaoReprovacao from './views/AprovacaoReprovacao.vue'



const routes = [
  {
    path:'/',
    name:'Login',
    component:Login,

  },
  
  
  {
    path: '/home',
    name: 'Home',
    component: Home,
  },
  {
    path: '/lancamentohora',
    name: 'LancamentoHora',
    component: LancamentoHora,
  },
  {
    path: '/cadastrocr',
    name: 'CadastroCr',
    component: CadastroCr,
  },
  {
    path: '/cadastrousuarios',
    name: 'CadastroUsuarios',
    component: CadastroUsuarios,
  },
  {
    path: '/visualizacaocr',
    name: 'VizualizacaoCr',
    component: VisualizacaoCr,
  },
  {
    path: '/visualizacaousuarios',
    name: 'VisualizacaoUsuarios',
    component: VisualizacaoUsuarios,
  },
  {
    path: '/aprovacaoreprovacao',
    name: 'AprovacaoReprovacao',
    component: AprovacaoReprovacao,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
